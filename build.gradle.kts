plugins {
    id(Dependencies.Plugins.androidApplication)
        .version(Versions.Plugins.androidApplication)
        .apply(false)
    
    id(Dependencies.Plugins.androidLibrary)
        .version(Versions.Plugins.androidLibrary)
        .apply(false)

    id(Dependencies.Plugins.kotlinAndroid)
        .version(Versions.Plugins.kotlinAndroid)
        .apply(false)
    id(Dependencies.Plugins.kotlinJvm)
        .version(Versions.Plugins.kotlinJvm)
        .apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}