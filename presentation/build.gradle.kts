plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "vn.thailam.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Internal
    implementation(project(":domain"))

    implementation(libs.androidx.ktx)
    implementation(libs.coroutine.core)

    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidxLifecycle.runtime)

    // Compose
    implementation(libs.androidxLifecycle.vm.compose)
    implementation(libs.androidxActivity.compose)
    implementation(platform(libs.androidxCompose.bom))
    implementation(libs.androidxCompose.ui)
    implementation(libs.androidxCompose.ui.graphics)
    implementation(libs.androidxCompose.ui.tooling.preview)
    implementation(libs.androidxCompose.ui.material3)
    debugImplementation(libs.androidxCompose.ui.tooling)
    debugImplementation(libs.androidxCompose.ui.test.manifest)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Testing
    testImplementation(libs.junit)
}
