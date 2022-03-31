plugins {
    id(Dependencies.Plugins.javaLibrary)
    id(Dependencies.Plugins.kotlinJvm)
}

java {
    sourceCompatibility = Configuration.javaVersion
    targetCompatibility = Configuration.javaVersion
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    api(project(":kotlinHelpers"))

    //Test
    testImplementation(Dependencies.Test.jupiter)
    testImplementation(Dependencies.Test.coroutinesCore)
    testImplementation(Dependencies.Test.mockk)

    // Coroutines
    implementation(Dependencies.Test.coroutinesCore)
}