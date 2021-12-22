plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Config.compile_sdk
    buildToolsVersion = Config.build_tool_version

    defaultConfig {
        compileSdk
        applicationId = Config.application_id
        minSdk = Config.min_sdk
        targetSdk = Config.target_sdk
        versionCode = Releases.version_code
        versionName = Releases.version_name

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
        sourceCompatibility(Config.java_version)
        targetCompatibility(Config.java_version)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.model))
    implementation(project(Modules.repository))
    implementation(project(Modules.utils))
    implementation(project(Modules.historyScreen))

    // AndroidX
    implementation(Design.appcompat)

    // Design
    // не следует указывать com.android.support и
    // com.google.android.material зависимости вместе
    implementation(Design.material)
    implementation(Design.swiperefreshlayout)

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
    kapt(Room.sqlite_jdbc)
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.room_ktx)

    // Test
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}


