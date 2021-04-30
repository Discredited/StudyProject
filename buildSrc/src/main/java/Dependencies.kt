object Versions {

    // Plugins
    const val androidGradlePlugin = "4.1.3"
    const val kotlin = "1.4.32"

    // BuildConfig
    const val compileSDK = 30 // 编译SDK版本
    const val buildTools = "30.0.3"  // Gradle编译项目工具版本
    const val minSDK = 21  // 最低兼容Android版本
    const val targetSDK = 30  // 最高兼容Android版本

    // App Version
    const val appVersionCode = 1
    const val appVersionName = "1.0.0"

    // 基础库
    const val core = "1.3.2"
    const val appcompat = "1.2.0"
    const val material = "1.2.1"
    const val constraintlayout = "2.0.4"

    const val junit = "4.13.2"
    const val junitExt = "1.1.2"
    const val espressoCore = "3.3.0"
}

object Deps {
    // Gradle
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core}"
    const val appcompat = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"

    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}