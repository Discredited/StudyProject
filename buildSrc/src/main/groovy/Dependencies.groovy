/**
 * 版本信息
 */
interface Versions {

    def compileSDK = 30 // 编译SDK版本
    def buildTools = "30.0.3" // Gradle编译项目工具版本

    def minSDK = 17 // 最低兼容Android版本
    def targetSDK = 28 // 最高兼容Android版本
}

interface Dependencies {

    def androidGradle = "com.android.tools.build:gradle:3.4.1"
    def kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32"

    def kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.32"
    def core_ktx = "androidx.core:core-ktx:1.3.2"
    def appcompat = "androidx.appcompat:appcompat:1.2.0"
    def material = "com.google.android.material:material:1.2.1"
    def constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"

    def junit = "junit:junit:4.13.2"
    def junitExt = "androidx.test:runner:1.3.0"
    def espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
}