plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "vn.thailam.data"
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

    flavorDimensions += "default"

    productFlavors {
        create("dev") {
        }
        create("prod") {
        }
    }
}

dependencies {
    // Internal
    implementation(project(":domain"))

    implementation(libs.androidx.ktx)
    implementation(libs.coroutine.core)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    testImplementation(libs.room.testing)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // Testing
    testImplementation(libs.junit)
}

kapt {
    correctErrorTypes = true
}