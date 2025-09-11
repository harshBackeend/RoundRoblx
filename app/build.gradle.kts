plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.demo.roundRoblx"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.demo.roundRoblx"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Project
    implementation(libs.androidscratchcard)

    //must by add
    implementation(libs.androidx.lifecycle.process)
    implementation(platform(libs.firebase.bom.v3281))
    implementation(libs.firebase.database)

    implementation(libs.gson)
    implementation(libs.glide)

    //flexbox for chip layout
    implementation(libs.flexbox)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.browser)
}