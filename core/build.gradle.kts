plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "vn.thailam.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
}
