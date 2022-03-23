import org.gradle.api.JavaVersion

object Configuration {
    private const val majorVersion = 1
    private const val minorVersion = 0
    private const val patchVersion = 0

    const val  compileSDK = 32
    const val applicationId = "com.murbano.moviesandroid"
    const val minSDdk = 24
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
    const val jvmTarget = "1.8"

    val javaVersion = JavaVersion.VERSION_1_8
}