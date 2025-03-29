plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.bookxpert.assignment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bookxpert.assignment"
        minSdk = 27
        targetSdk = 35
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

    flavorDimensions += "enviroments"

    productFlavors {
        create("development") {
            buildConfigField("String","BASE_URL","\"https://api.restful-api.dev/\"")
        }
        create("qa") {
            buildConfigField("String","BASE_URL","\"https://api.restful-api.dev/\"")
        }
        create("preproduction") {
            buildConfigField("String","BASE_URL","\"https://api.restful-api.dev/\"")
        }
        create("production") {
            buildConfigField("String","BASE_URL","\"https://api.restful-api.dev/\"")
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
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // dagger-hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

    //preference datastore
    implementation(libs.androidx.datastore.preferences)

    //moshi
    implementation(libs.moshi.kotlin)

    //coil
    implementation(libs.coil.compose)

    //Room
    implementation(libs.androidx.room.runtime)
    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
    ksp(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}