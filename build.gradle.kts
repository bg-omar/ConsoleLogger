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
import kotlin.script.experimental.api.ScriptDiagnostic

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




val pluginIdeaVersion = detectBestIdeVersion()

plugins {
    id("java") // Java support
    id("groovy")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"     // Kotlin support
    id("org.jetbrains.intellij") version "1.17.1"    // Gradle IntelliJ Plugin
    id("org.jetbrains.changelog") version "2.2.0"    // Gradle Changelog Plugin "com.intellij.clion"
    id("org.jetbrains.qodana") version "0.1.13"    // Gradle Qodana Plugin
    id("org.jetbrains.kotlinx.kover") version "0.7.4"    // Gradle Kover Plugin
    kotlin("plugin.serialization") version "1.9.22"
}


group = pluginGroup
version = pluginVersion
logger.log(LogLevel.INFO, "Will use IDEA $pluginIdeaVersion and Java $pluginJavaVersion. Plugin version set to $version")

group = "com.github.bgomar.consolelogger"

repositories {
    mavenCentral()
}

val junitVersion = "5.10.1"
val junitPlatformLauncher = "1.10.1"


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
    implementation("org.jetbrains:marketplace-zip-signer:0.1.24")
    implementation("org.jetbrains:annotations:24.1.0")
    implementation("org.apache.commons:commons-lang3:3.14.0") // because no longer bundled with IDE
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    implementation("commons-codec:commons-codec:1.16.0") // for Hash
    implementation("com.thedeanda:lorem:2.2") // for Lorem Ipsum
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1") // for JSON <> YAML
    implementation("com.dampcake:bencode:1.4.1") // for JSON <> BENCODE
    implementation("com.cronutils:cron-utils:9.2.1") // for cron expression parser https://github.com/jmrozanec/cron-utils
    implementation("net.datafaker:datafaker:2.1.0") // for Data Faker
    implementation("org.yaml:snakeyaml:2.2") // for JSON <> YAML
    implementation("org.apache.commons:commons-text:1.11.0") // for JSON (un)escape
    implementation("com.nulab-inc:zxcvbn:1.8.2") // for password strength evaluation
    implementation("fr.marcwrobel:jbanking:4.2.0") // for IBAN generation
    implementation("at.favre.lib:bcrypt:0.10.2") // for Bcrypt hash

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformLauncher")
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

    pluginName.set(pluginName)
    version.set(platformVersion)
    type.set(platformType)
    downloadSources.set(!System.getenv().containsKey("CI"))
    updateSinceUntilBuild.set(true)
    plugins.set(listOf("JavaScript", "com.intellij.java", "com.intellij.database"))
    sandboxDir.set(project.rootDir.canonicalPath + "/.sandbox")
    downloadSources.set(pluginDownloadIdeaSources.toBoolean() && !System.getenv().containsKey("CI"))
    instrumentCode.set(true)
    pluginName.set("ConsoleLogger")
    sandboxDir.set("${rootProject.projectDir}/.idea-sandbox/${shortenIdeVersion(pluginIdeaVersion)}")
    updateSinceUntilBuild.set(false)
    version.set(pluginIdeaVersion)
}
// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    groups.set(emptyList())
    repositoryUrl.set(pluginRepositoryUrl)
    headerParserRegex.set("(.*)".toRegex())
    itemPrefix.set("*")
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

tasks {
    register("clearSandboxedIDESystemLogs") {
        doFirst {
            if (pluginClearSandboxedIDESystemLogsBeforeRun.toBoolean()) {
                val sandboxLogDir = File("${rootProject.projectDir}/.idea-sandbox/${shortenIdeVersion(pluginIdeaVersion)}/system/log/")
                if (sandboxLogDir.exists() && sandboxLogDir.isDirectory) {
                    FileUtils.deleteDirectory(sandboxLogDir)
                    logger.quiet("Deleted sandboxed IDE's log folder $sandboxLogDir")
                }
            }
        }
    }

    register("updatePluginXml") {
        doFirst {
            val generatedActionsXml = generateConsoleLoggerActionsXml()
            val pluginXmlFile = File("src/main/resources/META-INF/plugin.xml")
            var pluginXmlContent = pluginXmlFile.readText()
            pluginXmlContent = pluginXmlContent.replace("\${generatedActionsXml}", generatedActionsXml)
            pluginXmlFile.writeText(pluginXmlContent)
        }
    }



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
    withType<Test> {
        useJUnitPlatform()

        // avoid JBUIScale "Must be precomputed" error, because IDE is not started (LoadingState.APP_STARTED.isOccurred is false)
        jvmArgs("-Djava.awt.headless=true")
    }
    withType<Test> {
        useJUnitPlatform()

        // avoid JBUIScale "Must be precomputed" error, because IDE is not started (LoadingState.APP_STARTED.isOccurred is false)
        jvmArgs("-Djava.awt.headless=true")
    }

    runIde {
        dependsOn("clearSandboxedIDESystemLogs")

        maxHeapSize = "1g" // https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html

        // force detection of slow operations in EDT when playing with sandboxed IDE (SlowOperations.assertSlowOperationsAreAllowed)
        jvmArgs("-Dide.slow.operations.assertion=true")

        if (pluginEnableDebugLogs.toBoolean()) {
            systemProperties(
                "idea.log.debug.categories" to "#com.github.bgomar.bgdevtoys"
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
        gradleVersion = pluginGradleVersion
    }

    patchPluginXml {
        version.set(pluginVersion)
        sinceBuild.set(pluginSinceBuild)
        untilBuild.set(pluginUntilBuild)
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
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget  = jvmTarget
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
       /* channels.set(listOf(pluginVersion.split('-').getOrElse(1) { "default" }.split('.').first()))*/
    }

    patchPluginXml {
        changeNotes.set(
            """<br>

            """
        )
    }
}


fun isNonStable(version: String): Boolean {
    if (listOf("RELEASE", "FINAL", "GA").any { version.uppercase().endsWith(it) }) {
        return false
    }
    return listOf("alpha", "Alpha", "ALPHA", "b", "beta", "Beta", "BETA", "rc", "RC", "M", "EA", "pr", "atlassian").any {
        "(?i).*[.-]${it}[.\\d-]*$".toRegex().matches(version)
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

/** Find latest IntelliJ stable version from JetBrains website. Result is cached locally for 24h. */
fun findLatestStableIdeVersion(): String {
    val t1 = System.currentTimeMillis()
    val definitionsUrl = URL("https://www.jetbrains.com/updates/updates.xml")
    val cachedLatestVersionFile = File(System.getProperty("java.io.tmpdir") + "/jle-ij-latest-version.txt")
    var latestVersion: String
    try {
        if (cachedLatestVersionFile.exists()) {

            val cacheDurationMs = Integer.parseInt(project.findProperty("pluginIdeaVersionCacheDurationInHours") as String) * 60 * 60_000
            if (cachedLatestVersionFile.exists() && cachedLatestVersionFile.lastModified() < (System.currentTimeMillis() - cacheDurationMs)) {
                logger.quiet("Cache expired, find latest stable IDE version from $definitionsUrl then update cached file $cachedLatestVersionFile")
                latestVersion = getOnlineLatestStableIdeVersion(definitionsUrl)
                cachedLatestVersionFile.delete()
                Files.writeString(cachedLatestVersionFile.toPath(), latestVersion, Charsets.UTF_8)

            } else {
                logger.quiet("Find latest stable IDE version from cached file $cachedLatestVersionFile")
                latestVersion = Files.readString(cachedLatestVersionFile.toPath())!!
            }

        } else {
            logger.quiet("Find latest stable IDE version from $definitionsUrl")
            latestVersion = getOnlineLatestStableIdeVersion(definitionsUrl)
            Files.writeString(cachedLatestVersionFile.toPath(), latestVersion, Charsets.UTF_8)
        }

    } catch (e: Exception) {
        if (cachedLatestVersionFile.exists()) {
            logger.warn("Error: ${e.message}. Will find latest stable IDE version from cached file $cachedLatestVersionFile")
            latestVersion = Files.readString(cachedLatestVersionFile.toPath())!!
        } else {
            throw RuntimeException(e)
        }
    }
    if (logger.isDebugEnabled) {
        val t2 = System.currentTimeMillis()
        logger.debug("Operation took ${t2 - t1} ms")
    }
    return latestVersion
}

/** Find latest IntelliJ stable version from given url. */
fun getOnlineLatestStableIdeVersion(definitionsUrl: URL): String {
    val definitionsStr = readRemoteContent(definitionsUrl)
    val builderFactory = DocumentBuilderFactory.newInstance()
    val builder = builderFactory.newDocumentBuilder()
    val xmlDocument: Document = builder.parse(ByteArrayInputStream(definitionsStr.toByteArray()))
    val xPath = XPathFactory.newInstance().newXPath()
    val expression = "/products/product[@name='IntelliJ IDEA']/channel[@id='IC-IU-RELEASE-licensing-RELEASE']/build[1]/@version"
    return xPath.compile(expression).evaluate(xmlDocument, XPathConstants.STRING) as String
}

/** Read a remote file as String. */
fun readRemoteContent(url: URL): String {
    val t1 = System.currentTimeMillis()
    val content = StringBuilder()
    val conn = url.openConnection() as HttpURLConnection
    conn.requestMethod = "GET"
    BufferedReader(InputStreamReader(conn.inputStream)).use { rd ->
        var line: String? = rd.readLine()
        while (line != null) {
            content.append(line)
            line = rd.readLine()
        }
    }
    val t2 = System.currentTimeMillis()
    logger.quiet("Download $url, took ${t2 - t1} ms (${content.length} B)")
    return content.toString()
}

/** Get IDE version from gradle.properties or, of wanted, find latest stable IDE version from JetBrains website. */
fun detectBestIdeVersion(): String {
    val pluginIdeaVersionFromProps = project.findProperty("pluginIdeaVersion")
    if (pluginIdeaVersionFromProps.toString() == "IC-LATEST-STABLE") {
        return "IC-${findLatestStableIdeVersion()}"
    }
    if (pluginIdeaVersionFromProps.toString() == "IU-LATEST-STABLE") {
        return "IU-${findLatestStableIdeVersion()}"
    }
    return pluginIdeaVersionFromProps.toString()
}

operator fun Any.get(key: String): Any {
    return key
}

fun generateConsoleLoggerActionsXml(): String {
    val actionsXml: StringBuilder = StringBuilder()
    actionsXml.append("            <!-- Include actions XML -->\n")
    for (i in 1..9) {
        actionsXml.append(createActionXml(i))
    }

    actionsXml.append("            <action id=\"com.github.bgomar.consolelogger.removeLogs\" class=\"com.github.bgomar.consolelogger.ConsoleLoggerRemove\" text=\"Remove ConsoleLogger's Console.Log\"\n")
    actionsXml.append("                    description=\"Remove console.log() generate by ConsoleLogger plugin\">\n")
    actionsXml.append("                 <keyboard-shortcut keymap=\"\$default\" first-keystroke=\"ctrl alt 0\"/>\n")
    actionsXml.append("                 <keyboard-shortcut keymap=\"Mac OS X\" first-keystroke=\"ctrl alt 0\"/>\n")
    actionsXml.append("            </action>\n")
    actionsXml.append("            <!-- Include actions end -->")
    return actionsXml.toString()
}

fun createActionXml(i: Int): String {
    val actionXml: StringBuilder = StringBuilder()
    actionXml.append("             <action id=\"com.github.bgomar.consolelogger.add").append(i).append("\" class=\"com.github.bgomar.consolelogger.ConsoleLoggerAdd").append(i).append("\"\n")
    actionXml.append("                    text=\"Insert ConsoleLogger ").append(i).append("\"\n")
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
                "\n$generatedActionsXml\n" +
                pluginXmlContent.substring(endIndex)

        pluginXmlFile.writeText(updatedPluginXmlContent)
    } else {
        throw GradleException("Plugin description section not found in src/main/resources/META-INF/plugin.xml")
    }
}
