plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.quizie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quizie"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    with(rootProject.extra) {

        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
        implementation("androidx.activity:activity-compose:1.8.2")

        // compose
        implementation(platform("androidx.compose:compose-bom:2023.08.00"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        implementation("io.coil-kt:coil-compose:2.0.0-rc01")

        //dagger
        implementation("com.google.dagger:dagger:${this["version_dagger"]}")
        kapt("com.google.dagger:dagger-compiler:${this["version_dagger"]}")
        implementation("com.google.dagger:dagger-android-support:${this["version_dagger"]}")
        kapt("com.google.dagger:dagger-android-processor:${this["version_dagger"]}")

        //moshi
        implementation("com.squareup.moshi:moshi:${this["version_moshi"]}")
        implementation("com.squareup.moshi:moshi-kotlin:${this["version_moshi"]}")

        //retrofit
        implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
        implementation("com.squareup.retrofit2:converter-moshi:${this["version_retrofit"]}")
        implementation("com.squareup.retrofit2:retrofit:${this["version_retrofit"]}")
        implementation("com.squareup.retrofit2:converter-scalars:${this["version_retrofit"]}")
//        implementation("com.squareup.retrofit2:converter-moshi:${this["version_retrofit"]}")

        implementation("com.squareup.moshi:moshi:${this["version_moshi"]}")

        // test
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        //navigation
        implementation("androidx.navigation:navigation-fragment-ktx:${this["version_navigation"]}")
        implementation("androidx.navigation:navigation-ui-ktx:${this["version_navigation"]}")

        //Gson
        implementation("com.google.code.gson:gson:2.10.1")
    }
}