plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kotlinKapt)
    id(Dependencies.Plugins.hiltAndroidPlugin)
}

tasks.register("runUnitTests") {
    dependsOn(":androidHelpers:test", ":app:test", ":core:test", ":kotlinHelpers:test",
        ":movies:test", ":movies:data:test", ":movies:domain:test", ":movies:presentation:test",
    )
    group = "CI"
    description = "$ ./gradlew runUnitTests # runs on GitHub Action"
}

android {
    compileSdk = Configuration.compileSDK

    defaultConfig {
        minSdk = Configuration.minSDdk
        targetSdk = Configuration.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configuration.javaVersion
        targetCompatibility = Configuration.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configuration.jvmTarget
    }
}

dependencies {

    //AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constraintlayout)

    //Material
    implementation(Dependencies.Material.googleMaterial)

    //Retrofit
    implementation(Dependencies.Retrofit.retrofit2Version)
    implementation(Dependencies.Retrofit.gsonVersion)
    implementation(Dependencies.Retrofit.converterVersion)

    //Hilt
    implementation(Dependencies.Hilt.hiltVersion)
    kapt(Dependencies.Hilt.hiltCompiler)

    //Room
    implementation(Dependencies.Room.roomRuntime)
    kapt(Dependencies.Room.roomCompiler)

    //Test
    testImplementation(Dependencies.Test.junit)

    //AndroidTest
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)

    //Modules
    api(project(":movies:presentation"))
    api(project(":movies:domain"))
    api(project(":movies:data"))
}