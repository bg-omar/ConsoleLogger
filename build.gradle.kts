import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML

//abstract class SomeTask : DefaultTask() {
//
//    @get:Inject abstract val fs: FileSystemOperations
//
//    @TaskAction
//    fun properties(key: String)  {
//        project.findProperty(key).toString()
//    }
//}
fun properties(key: String) = providers.gradleProperty(key)
fun environment(key: String) = providers.environmentVariable(key)
fun project(key: String) = project.findProperty(key).toString()

plugins {
    id("java") // Java support
    id("groovy")
    id("org.jetbrains.kotlin.jvm") version "1.9.10"     // Kotlin support
    id("org.jetbrains.intellij") version "1.16.0"    // Gradle IntelliJ Plugin
    id("org.jetbrains.changelog") version "2.2.0"    // Gradle Changelog Plugin "com.intellij.clion"
    id("org.jetbrains.qodana") version "0.1.13"    // Gradle Qodana Plugin
    id("org.jetbrains.kotlinx.kover") version "0.6.1"    // Gradle Kover Plugin
    kotlin("plugin.serialization") version "1.9.10"
}

group = project("pluginGroup")
version = project("pluginVersion")

val service = project.extensions.getByType<JavaToolchainService>()
val customLauncher = service.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://www.jetbrains.com/intellij-repository/releases")
    maven("https://www.jetbrains.com/intellij-repository/snapshots")
    maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:marketplace-zip-signer:0.1.8")
    implementation("org.jetbrains:annotations:24.0.1")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {

    pluginName.set(project("pluginName"))
    version.set(project("platformVersion"))
    type.set(project("platformType"))
    downloadSources.set(!System.getenv().containsKey("CI"))
    updateSinceUntilBuild.set(true)
    plugins.set(listOf("JavaScript", "com.intellij.java", "com.intellij.database"))
    sandboxDir.set(project.rootDir.canonicalPath + "/.sandbox")
}
// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    groups.set(emptyList())
    repositoryUrl.set(project("pluginRepositoryUrl"))
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

kover.htmlReport {
    onCheck.set(true)
}
kover.xmlReport {
    onCheck.set(true)
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = project("sourceCompatibility")
        targetCompatibility = project("targetCompatibility")
    }
    withType<org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain>().configureEach {
        kotlinJavaToolchain.toolchain.use(customLauncher)
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = project("sourceCompatibility")
    }
    wrapper {
        gradleVersion = project("gradleVersion")
    }

    patchPluginXml {
        version.set(project("pluginVersion"))
        sinceBuild.set(project("pluginSinceBuild"))
        untilBuild.set(project("pluginUntilBuild"))

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
                    getOrNull(project("pluginVersion"))
                        ?: runCatching { getLatest() }.getOrElse { getUnreleased() },
                    Changelog.OutputType.HTML,
                )
            }
        })
    }
    buildSearchableOptions {
        enabled = true
    }
    compileKotlin {
        kotlinOptions.jvmTarget = project("jvmTarget")
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget  = project("jvmTarget")
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
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        channels.set(listOf(project("pluginVersion").split('-').getOrElse(1) { "default" }.split('.').first()))
    }
/*

    runIde {
        ideDir.set(file("/Users/mr/AppData/Roaming/JetBrains/"))
    }
*/

    patchPluginXml {
        changeNotes.set(
            """<br>

            """
        )
    }
}
