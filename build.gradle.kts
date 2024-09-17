import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML

interface Injected {
    @get:Inject val fs: FileSystemOperations
}
val injected = project.objects.newInstance<Injected>()
fun properties(key: String) = providers.gradleProperty(key)
fun environment(key: String) = providers.environmentVariable(key)
fun project(key: String) = injected.fs[key].toString()

// Import variables from gradle.properties file
val pluginDownloadIdeaSources: String by project
val pluginVersion: String by project
val pluginJavaVersion: String by project
val pluginEnableDebugLogs: String by project
val pluginClearSandboxedIDESystemLogsBeforeRun: String by project

val pluginGroup: String by project
val jvmTarget: String by project
val pluginSinceBuild: String by project
val pluginUntilBuild: String by project
val pluginGradleVersion: String by project

val sourceCompatibility: String by project
val pluginRepositoryUrl: String by project
val platformType: String by project

val pluginIdeaVersion = pluginUntilBuild

plugins {
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.17.4"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "2.0.0"
    // Gradle Qodana Plugin
    id("org.jetbrains.qodana") version "0.1.13"
    // Gradle Kover Plugin
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
    maven("https://www.jetbrains.com/intellij-repository/releases")
    gradlePluginPortal()
    mavenCentral()
}

val service = project.extensions.getByType<JavaToolchainService>()
val customLauncher = service.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    implementation("org.jetbrains:marketplace-zip-signer:0.1.24")
}
abstract class UpdatePluginXml : DefaultTask() {

    @TaskAction
    fun action() {
        val generatedActionsXml = generateConsoleLoggerActionsXml()
        val pluginXmlFile = File("src/main/resources/META-INF/plugin.xml")

        doFirst {
            var pluginXmlContent = pluginXmlFile.readText()

            pluginXmlContent = pluginXmlContent.replace("\${generatedActionsXml}", generatedActionsXml)

            pluginXmlFile.writeText(pluginXmlContent)
        }
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    downloadSources.set(!System.getenv().containsKey("UI"))
    updateSinceUntilBuild.set(false)
    plugins = properties("platformPlugins").map { it.split(',').map(String::trim).filter(String::isNotEmpty) }

    sandboxDir.set(project.rootDir.canonicalPath + "/.sandbox")
}
// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    groups.set(emptyList())
    repositoryUrl.set(properties("pluginRepositoryUrl"))
}

// Configure Gradle Qodana Plugin - read more: https://github.com/JetBrains/gradle-qodana-plugin
qodana {
    cachePath.set(file(".qodana").canonicalPath)
    reportPath.set(file("build/reports/inspections").canonicalPath)
    saveReport.set(true)
    showReport.set(System.getenv("QODANA_SHOW_REPORT")?.toBoolean() ?: false)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kover.xmlReport {
    onCheck.set(true)
}
tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain>().configureEach {
        kotlinJavaToolchain.toolchain.use(customLauncher)
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    wrapper {
        gradleVersion= "7.6"
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        untilBuild.set(properties("pluginUntilBuild"))

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(
            file("README.md").readText().lines().run {
                val start = "<!-- Plugin description -->"
                val end = "<!-- Plugin description end -->"

                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end))
            }.joinToString("\n").let { markdownToHTML(it) }
        )

        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            with(changelog) {
                renderItem(
                    getOrNull(pluginVersion)
                        ?: runCatching { getLatest() }.getOrElse { getUnreleased() },
                    Changelog.OutputType.HTML,
                )
            }
        })
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    runIdeForUiTests {
        systemProperty("robot-server.port", "8082")
        systemProperty("ide.mac.message.dialogs.as.sheets", "false")
        systemProperty("jb.privacy.policy.text", "<!--999.999-->")
        systemProperty("jb.consents.confirmation.enabled", "false")
    }

    signPlugin {
        certificateChainFile.set(file("./secrets/chain.crt"))
        privateKeyFile.set(file("./secrets/private_encrypted.pem"))
        password = environment("PRIVATE_KEY_PASSWORD")
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token = environment("PUBLISH_TOKEN")
        channels = properties("pluginVersion").map { listOf(it.substringAfter('-', "").substringBefore('.').ifEmpty { "default" }) }
    }
}

operator fun Any.get(key: String): Any {
    return key
}

fun generateConsoleLoggerActionsXml(): String {
    val actionsXml: StringBuilder = StringBuilder()
    actionsXml.append("<!-- Include actions XML -->\n")
    for (i in 1..9) {
        actionsXml.append(createActionXml(i))
    }

    actionsXml.append("\n            <action id=\"com.github.bgomar.consolelogger.removeLogs\" class=\"com.github.bgomar.consolelogger.ConsoleLoggerRemove\" text=\"0\"\n")
    actionsXml.append("                    description=\"Remove console.log() generate by ConsoleLogger plugin\">\n")
    actionsXml.append("                 <keyboard-shortcut keymap=\"\$default\" first-keystroke=\"ctrl alt 0\"/>\n")
    actionsXml.append("                 <keyboard-shortcut keymap=\"Mac OS X\" first-keystroke=\"ctrl alt 0\"/>\n")
    actionsXml.append("            </action>\n")
    actionsXml.append("            <!-- Include actions end -->")
    return actionsXml.toString()
}

fun createActionXml(i: Int): String {
    val actionXml: StringBuilder = StringBuilder()
    actionXml.append("\n             <action id=\"com.github.bgomar.consolelogger.add").append(i).append("\" class=\"com.github.bgomar.consolelogger" +
            ".ConsoleLoggerAction").append("\"\n")
    actionXml.append("                    text=\"").append(i-1).append("\"\n")
    actionXml.append("                    description=\"Generate a console.log() for that variable\">\n")
    actionXml.append("                 <keyboard-shortcut keymap=\"\$default\" first-keystroke=\"ctrl alt ").append(i).append("\"/>\n")
    actionXml.append("                 <keyboard-shortcut keymap=\"Mac OS X\" first-keystroke=\"ctrl alt ").append(i).append("\"/>\n")
    actionXml.append("             </action>\n")

    return actionXml.toString()
}

fun updatePluginXml() {
    val generatedActionsXml = generateConsoleLoggerActionsXml()
    val pluginXmlFile = File("src/main/resources/META-INF/plugin.xml")

    val pluginXmlContent = pluginXmlFile.readText()
    val startTag = "<!-- Include actions XML -->"
    val endTag = "<!-- Include actions end -->"

    val startIndex = pluginXmlContent.indexOf(startTag)
    val endIndex = pluginXmlContent.indexOf(endTag) + endTag.length

    if (startIndex != -1 && endIndex != -1) {
        val updatedPluginXmlContent = pluginXmlContent.substring(0, startIndex) +
                generatedActionsXml +
                pluginXmlContent.substring(endIndex)

        pluginXmlFile.writeText(updatedPluginXmlContent)
    } else {
        throw GradleException("Plugin description section not found in src/main/resources/META-INF/plugin.xml")
    }
}