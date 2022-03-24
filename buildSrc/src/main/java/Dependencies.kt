object Dependencies {
    object Plugins {
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val kotlinJvm = "org.jetbrains.kotlin.jvm"
        const val javaLibrary = "java-library"
        const val kotlinKapt = "kotlin-kapt"
        const val hiltAndroidPlugin = "dagger.hilt.android.plugin"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintlayout}"
    }

    object Material {
        const val googleMaterial =
            "com.google.android.material:material:${Versions.Material.googleMaterial}"
    }

    object Hilt {
        const val hiltVersion =
            "com.google.dagger:hilt-android:${Versions.Hilt.hiltVersion}"
        const val hiltCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hiltCompiler}"
    }

    object Retrofit {
        const val retrofit2Version =
            "com.squareup.retrofit2:retrofit:${Versions.Retrofit.retrofit2Version}"
        const val gsonVersion = "com.google.code.gson:gson:${Versions.Retrofit.gsonVersion}"
        const val converterVersion =
            "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.converterVersion}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:${Versions.AndroidTest.junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espresso}"
    }
}