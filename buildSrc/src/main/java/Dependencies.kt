object Dependencies {
    object Plugins {
        val androidApplication by lazy { "com.android.application" }
        val androidLibrary by lazy { "com.android.library" }
        val kotlinAndroid by lazy { "org.jetbrains.kotlin.android" }
        val kotlinJvm by lazy { "org.jetbrains.kotlin.jvm" }
        val javaLibrary by lazy { "java-library" }
        val kotlinKapt by lazy { "kotlin-kapt" }
        val hiltAndroidPlugin by lazy { "dagger.hilt.android.plugin" }
    }

    object AndroidX {
        val coreKtx by lazy { "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}" }
        val constraintlayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintlayout}" }
        val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}" }
        val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}" }
        val lifecycleRuntime by lazy {"androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycleRuntime}"}

    }

    object Material {
        val googleMaterial by lazy { "com.google.android.material:material:${Versions.Material.googleMaterial}" }
    }

    object Compose {
        val activity by lazy { "androidx.activity:activity-compose:${Versions.Compose.activity}" }
        val material by lazy { "androidx.compose.material:material:${Versions.Compose.generalVersion}" }
        val animation by lazy { "androidx.compose.animation:animation:${Versions.Compose.generalVersion}" }
        val uiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.Compose.generalVersion}" }
    }

    object Hilt {
        val hiltVersion by lazy { "com.google.dagger:hilt-android:${Versions.Hilt.hiltVersion}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hiltCompiler}" }
    }

    object Retrofit {
        val retrofit2Version by lazy { "com.squareup.retrofit2:retrofit:${Versions.Retrofit.retrofit2Version}" }
        val gsonVersion by lazy { "com.google.code.gson:gson:${Versions.Retrofit.gsonVersion}" }
        val converterVersion by lazy { "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.converterVersion}" }
    }

    object Test {
        val junit by lazy { "junit:junit:${Versions.Test.junit}" }
    }

    object AndroidTest {
        val junit by lazy { "androidx.test.ext:junit:${Versions.AndroidTest.junit}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espresso}" }
    }

    object UI {
        val coil by lazy { "io.coil-kt:coil-compose:${Versions.UI.coil}" }
        val lottie by lazy {"com.airbnb.android:lottie-compose:${Versions.UI.lottie}"}
    }
}