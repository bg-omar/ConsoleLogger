@file:Suppress("VulnerableLibrariesLocal")

import org.apache.commons.io.FileUtils
import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.w3c.dom.Document
import java.lang.StringBuilder
import java.io.File
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

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
val platformVersion: String by project


val pluginIdeaVersion = "2023.2"

plugins {
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "2.0.0-Beta3"
    // gradle-intellij-plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.changelog") version "2.2.0"    // Gradle Changelog Plugin "com.intellij.clion"
    id("org.jetbrains.intellij") version "1.17.4"
    id("org.jetbrains.kotlinx.kover") version "0.7.4"    // Gradle Kover Plugin
    kotlin("plugin.serialization") version "1.9.22"
}


group = properties("pluginGroup").get()
version = properties("pluginVersion").get()


val junitVersion = "5.11.0-M2"
val junitPlatformLauncher = "1.11.0-M2"


val service = project.extensions.getByType<JavaToolchainService>()
val customLauncher = service.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(17))
}

// Configure project's dependencies
repositories {
    maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
    maven("https://www.jetbrains.com/intellij-repository/releases")
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
// https://mvnrepository.com/artifact/commons-httpclient/commons-httpclient
    implementation("org.jetbrains:marketplace-zip-signer:0.1.24")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0-RC")
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

intellij {
    pluginName = properties("pluginName")
    version = properties("platformVersion")
    type = properties("platformType")

    downloadSources.set(!System.getenv().containsKey("CI"))
    updateSinceUntilBuild.set(true)

    sandboxDir.set("${rootProject.projectDir}/.idea-sandbox/${shortenIdeVersion(pluginIdeaVersion)}")

    downloadSources.set(!System.getenv().containsKey("CI"))
    downloadSources.set(pluginDownloadIdeaSources.toBoolean() && !System.getenv().containsKey("IU"))
    instrumentCode.set(true)
    plugins.set(listOf("JavaScript"))

    sandboxDir.set(project.rootDir.canonicalPath + "/.sandbox")

}
// Set the JVM language level used to build the project.
kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}


tasks {
    // Set the compatibility versions to 1.8
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = pluginJavaVersion
        targetCompatibility = pluginJavaVersion
        options.compilerArgs = listOf("-Xlint:deprecation")
        options.encoding = "UTF-8"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain>().configureEach {
        kotlinJavaToolchain.toolchain.use(customLauncher)
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = sourceCompatibility
    }
    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("233.*")
    }

    runIde {
        dependsOn("clearSandboxedIDESystemLogs")

        maxHeapSize = "1g" // https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html

        // force detection of slow operations in EDT when playing with sandboxed IDE (SlowOperations.assertSlowOperationsAreAllowed)
        jvmArgs("-Dide.slow.operations.assertion=true")

        if (pluginEnableDebugLogs.toBoolean()) {
            systemProperties(
                "idea.log.debug.categories" to "#com.github.bgomar.consolelogger"
            )
        }

        autoReloadPlugins.set(false)

        // If any warning or error with missing --add-opens, wait for the next gradle-intellij-plugin's update that should sync
        // with https://raw.githubusercontent.com/JetBrains/intellij-community/master/plugins/devkit/devkit-core/src/run/OpenedPackages.txt
        // or do it manually
    }
    buildSearchableOptions {
        enabled = false
    }
    wrapper {
        gradleVersion = properties("gradleVersion").get()
    }

    patchPluginXml {
        version = properties("pluginVersion")
        sinceBuild = properties("pluginSinceBuild")
        untilBuild = properties("pluginUntilBuild")
        updatePluginXml()
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

    buildSearchableOptions {
        enabled = false
    }
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = jvmTarget
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

    patchPluginXml {
        changeNotes.set(
            """<br>

        """
        )
    }
}



/** Return an IDE version string without the optional PATCH number.
 * In other words, replace IDE-MAJOR-MINOR(-PATCH) by IDE-MAJOR-MINOR. */
fun shortenIdeVersion(version: String): String {
    if (version.contains("SNAPSHOT", ignoreCase = true)) {
        return version
    }
    val matcher = Regex("[A-Za-z]+[\\-]?[0-9]+[\\.]{1}[0-9]+")
    return try {
        matcher.findAll(version).map { it.value }.toList()[0]
    } catch (e: Exception) {
        logger.warn("Failed to shorten IDE version $version: ${e.message}")
        version
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
    actionXml.append("\n             <action id=\"com.github.bgomar.consolelogger.add").append(i).append(
        "\" class=\"com.github.bgomar.consolelogger" +
                ".ConsoleLoggerAction"
    ).append("\"\n")
    actionXml.append("                    text=\"").append(i - 1).append("\"\n")
    actionXml.append("                    description=\"Generate a console.log() for that variable\">\n")
    actionXml.append("                 <keyboard-shortcut keymap=\"\$default\" first-keystroke=\"ctrl alt ")
        .append(i).append("\"/>\n")
    actionXml.append("                 <keyboard-shortcut keymap=\"Mac OS X\" first-keystroke=\"ctrl alt ")
        .append(i).append("\"/>\n")
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

