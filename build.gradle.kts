// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.protobuf) apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
