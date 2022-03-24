plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kotlinKapt)
    id(Dependencies.Plugins.hiltAndroidPlugin)
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

    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    //AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constraintlayout)

    //Material
    implementation(Dependencies.Material.googleMaterial)

    //Test
    testImplementation(Dependencies.Test.junit)

    //Hilt
    implementation(Dependencies.Hilt.hiltVersion)
    kapt(Dependencies.Hilt.hiltCompiler)

    //AndroidTest
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)

    //Modules
    implementation(project(":movies"))
}