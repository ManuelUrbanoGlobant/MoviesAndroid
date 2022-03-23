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
}