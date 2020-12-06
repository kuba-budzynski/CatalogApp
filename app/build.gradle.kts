
plugins {
    id("com.android.application")
    kotlin("android")
    id("com.apollographql.apollo").version("2.4.5")
    id("kotlin-android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-android-extensions")
    kotlin("plugin.serialization")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.example.katalog"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode(1)
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }


    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    apollo {
        // instruct the compiler to generate Kotlin models
        generateKotlinModels.set(true)

    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.2")
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation("com.apollographql.apollo:apollo-runtime:2.4.5")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.4.5")
    implementation("io.coil-kt:coil:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.activity:activity-ktx:1.1.0")
    implementation("com.google.android.material:material:1.2.1")


}

