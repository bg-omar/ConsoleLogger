rootProject.name = "ConsoleLogger"

pluginManagement {
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://www.jetbrains.com/intellij-repository/snapshots")
        maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
        gradlePluginPortal()
        mavenCentral()
    }
}
