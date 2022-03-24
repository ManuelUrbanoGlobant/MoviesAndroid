plugins {
    id(Dependencies.Plugins.androidApplication)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kotlinKapt)
    id(Dependencies.Plugins.hiltAndroidPlugin)
}

android {
    compileSdk = Configuration.compileSDK

    defaultConfig {
        applicationId = Configuration.applicationId
        minSdk = Configuration.minSDdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("release") {
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

    //Hilt
    implementation(Dependencies.Hilt.hiltVersion)
    kapt(Dependencies.Hilt.hiltCompiler)

    //Retrofit
    implementation(Dependencies.Retrofit.retrofit2Version)
    implementation(Dependencies.Retrofit.gsonVersion)
    implementation(Dependencies.Retrofit.converterVersion)

    //Test
    testImplementation(Dependencies.Test.junit)

    //AndroidTest
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)

    //Modules
    implementation(project(":kotlinHelpers"))

}