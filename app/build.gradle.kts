plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.example.dictionary"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.model))
    implementation(project(Modules.repository))
    implementation(project(Modules.utils))
    implementation(project(Modules.historyScreen) )

    // AndroidX
    implementation(Design.appcompat)

    // Design
    // You should not use the com.android.support and
    // com.google.android.material dependencies in your app at the same time
    implementation(Design.material)

    // Kotlin
    implementation(Kotlin.core)
    implementation(Kotlin.stdlib)
    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    // Koin for Android
    implementation(Koin.koin_android)
    implementation(Koin.koin_view_model)

    //Coil
    implementation(Coil.coil)

    // Room
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.room_ktx)

    // Test
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)
//    implementation(fileTree(org.gradle.internal.impldep.bsh.commands.dir:
//        'libs',
//        include:['*.jar']))
}


