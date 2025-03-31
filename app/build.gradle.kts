plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.googleServices)
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
            buildConfigField("String","PDF_BASE_URL","\"https://docs.google.com/gview?embedded=true&url=\"")
            buildConfigField("String","PDF_URL","\"https://fssservices.bookxpert.co/GeneratedPDF/Companies/nadc/2024-2025/BalanceSheet.pdf\"")
        }
        create("qa") {
            buildConfigField("String","BASE_URL","\"https://api.restful-api.dev/\"")
            buildConfigField("String","PDF_BASE_URL","\"https://docs.google.com/gview?embedded=true&url=\"")
            buildConfigField("String","PDF_URL","\"https://fssservices.bookxpert.co/GeneratedPDF/Companies/nadc/2024-2025/BalanceSheet.pdf\"")
        }
        create("preproduction") {
            buildConfigField("String","BASE_URL","\"https://api.restful-api.dev/\"")
            buildConfigField("String","PDF_BASE_URL","\"https://docs.google.com/gview?embedded=true&url=\"")
            buildConfigField("String","PDF_URL","\"https://fssservices.bookxpert.co/GeneratedPDF/Companies/nadc/2024-2025/BalanceSheet.pdf\"")
        }
        create("production") {
            buildConfigField("String","BASE_URL","\"https://api.restful-api.dev/\"")
            buildConfigField("String","PDF_BASE_URL","\"https://docs.google.com/gview?embedded=true&url=\"")
            buildConfigField("String","PDF_URL","\"https://fssservices.bookxpert.co/GeneratedPDF/Companies/nadc/2024-2025/BalanceSheet.pdf\"")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.0"
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

    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.firebase.auth)

    // Also add the dependencies for the Credential Manager libraries and specify their versions
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.auth.ktx)

    // navigation
    implementation(libs.androidx.navigation.compose)

    //CameraX
    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.lifecycle)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}