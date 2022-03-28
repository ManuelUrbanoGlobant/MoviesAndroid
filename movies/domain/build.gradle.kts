plugins {
    id(Dependencies.Plugins.javaLibrary)
    id(Dependencies.Plugins.kotlinJvm)
}

java {
    sourceCompatibility = Configuration.javaVersion
    targetCompatibility = Configuration.javaVersion
}

dependencies {
    api(project(":kotlinHelpers"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}