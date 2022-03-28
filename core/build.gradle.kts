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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.generalVersion
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
    implementation(Dependencies.AndroidX.splashScreen)

    //Material
    implementation(Dependencies.Material.googleMaterial)

    //Test
    testImplementation(Dependencies.Test.junit)

    //Hilt
    implementation(Dependencies.Hilt.hiltVersion)
    kapt(Dependencies.Hilt.hiltCompiler)

    //Compose
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.uiTooling)

    // Pager
    implementation(Dependencies.Pager.pager)
    implementation(Dependencies.Pager.indicators)

    //AndroidTest
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)

    //Modules
    implementation(project(":movies"))
    implementation(project(":androidHelpers"))
}