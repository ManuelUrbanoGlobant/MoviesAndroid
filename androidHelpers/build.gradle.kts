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
        kotlinCompilerExtensionVersion = "1.2.0-alpha04"
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

    //Hilt
    implementation(Dependencies.Hilt.hiltVersion)
    kapt(Dependencies.Hilt.hiltCompiler)

    //Material
    implementation(Dependencies.Material.googleMaterial)

    //Compose
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.uiTooling)

    //Datastore
    implementation(Dependencies.DataStore.preferences)
    implementation(Dependencies.DataStore.core)

    //Lottie
    implementation(Dependencies.UI.lottie)

    //Test
    testImplementation(Dependencies.Test.junit)
}