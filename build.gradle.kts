
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease



plugins {
    id("java") // Java support
    id("groovy")
    id("application")
    id("org.jetbrains.kotlin.jvm") version "2.0.0-Beta3"     // Kotlin support
    id("org.jetbrains.intellij.platform") version "2.0.1"
    id("org.jetbrains.changelog") version "2.2.0"    // Gradle Changelog Plugin "com.intellij.clion"
    id("org.jetbrains.qodana") version "0.1.13"    // Gradle Qodana Plugin
    id("org.jetbrains.kotlinx.kover") version "0.9.0-RC"// Gradle Kover Plugin
    kotlin("plugin.serialization") version "1.9.22"
}

repositories {
    mavenCentral()

    google()
    maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
    maven("https://www.jetbrains.com/intellij-repository/releases")
    gradlePluginPortal()

    // IntelliJ Platform Gradle Plugin Repositories Extension - read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-repositories-extension.html
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation("org.jetbrains:marketplace-zip-signer:0.1.24")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0-RC")
    intellijPlatform {
        intellijIdeaUltimate("2024.2")
        bundledPlugin("JavaScript")
        instrumentationTools()
        pluginVerifier()
        testFramework(TestFrameworkType.Platform)
    }
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.opentest4j:opentest4j:1.3.0")
}


tasks.named<Test>("test") {
    useJUnitPlatform()
}
group = providers.gradleProperty("pluginGroup").get()
version = providers.gradleProperty("pluginVersion").get()

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

// Set the JVM language level used to build the project.
kotlin {
    jvmToolchain(17)
}


// Configure IntelliJ Platform Gradle Plugin - read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-extension.html
intellijPlatform {
    pluginConfiguration {
        version = providers.gradleProperty("pluginVersion")

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        description = providers.fileContents(layout.projectDirectory.file("README.md")).asText.map {
            val start = "<!-- Plugin description -->"
            val end = "<!-- Plugin description end -->"

            with(it.lines()) {
                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end)).joinToString("\n").let(::markdownToHTML)
            }
        }

        val changelog = project.changelog // local variable for configuration cache compatibility
        // Get the latest available change notes from the changelog file
        changeNotes = providers.gradleProperty("pluginVersion").map { pluginVersion ->
            with(changelog) {
                renderItem(
                    (getOrNull(pluginVersion) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),
                    Changelog.OutputType.HTML,
                )
            }
        }

        ideaVersion {
            sinceBuild = providers.gradleProperty("pluginSinceBuild")
            untilBuild = providers.gradleProperty("pluginUntilBuild")
        }
    }

    signing {
        certificateChain = providers.environmentVariable("CERTIFICATE_CHAIN")
        privateKey = providers.environmentVariable("PRIVATE_KEY")
        password = providers.environmentVariable("PRIVATE_KEY_PASSWORD")
    }

    publishing {
        token = providers.environmentVariable("PUBLISH_TOKEN")
        // The pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        channels = providers.gradleProperty("pluginVersion").map { listOf(it.substringAfter('-', "").substringBefore('.').ifEmpty { "default" }) }
    }

    pluginVerification {
        ides {
            ide(IntelliJPlatformType.WebStorm, "2024.2")
            recommended()
            select {
                types = listOf(IntelliJPlatformType.WebStorm)
                channels = listOf(ProductRelease.Channel.RELEASE)
                sinceBuild = "232"
                untilBuild = "242.*"
            }
        }
    }
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    groups.empty()
    repositoryUrl = providers.gradleProperty("pluginRepositoryUrl")
}

// Configure Gradle Kover Plugin - read more: https://github.com/Kotlin/kotlinx-kover#configuration
kover {
    reports {
        total {
            xml {
                onCheck = true
            }
        }
    }
}

tasks {
    register("updatePluginXml") {
        val generatedActionsXml = generateConsoleLoggerActionsXml()
        val pluginXmlFile = File("src/main/resources/META-INF/plugin.xml")
        var pluginXmlContent = pluginXmlFile.readText()
        doFirst {
            pluginXmlContent = pluginXmlContent.replace("\${generatedActionsXml}", generatedActionsXml)
            pluginXmlFile.writeText(pluginXmlContent)
        }
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
        enabled = true
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


    wrapper {
        gradleVersion = providers.gradleProperty("gradleVersion").get()
    }
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    publishPlugin {
        dependsOn(patchChangelog)
    }
}

intellijPlatformTesting {
    runIde {
        register("runIdeForUiTests") {
            task {
                jvmArgumentProviders += CommandLineArgumentProvider {
                    listOf(
                        "-Drobot-server.port=8082",
                        "-Dide.mac.message.dialogs.as.sheets=false",
                        "-Djb.privacy.policy.text=<!--999.999-->",
                        "-Djb.consents.confirmation.enabled=false",
                    )
                }
            }

            plugins {
                robotServerPlugin()
            }
        }
    }
}


operator fun Any.get(key: String): Any {
    return key
}

fun generateConsoleLoggerActionsXml(): String {
    val actionsXml: java.lang.StringBuilder = StringBuilder()
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
    val actionXml: java.lang.StringBuilder = StringBuilder()
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
