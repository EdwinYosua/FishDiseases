// Top-level build file where you can add configuration options common to all sub-projects/modules.



buildscript {
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}