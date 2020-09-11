version = LibraryAndroidCoordinates.LIBRARY_VERSION

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    compileSdkVersion(AppCoordinates.Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AppCoordinates.Sdk.MIN_SDK_VERSION)
        targetSdkVersion(AppCoordinates.Sdk.TARGET_SDK_VERSION)

        versionCode = LibraryAndroidCoordinates.LIBRARY_VERSION_CODE
        versionName = LibraryAndroidCoordinates.LIBRARY_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {
    implementation(Deps.Core.Coroutine.CORE)
    implementation(Deps.Core.Coroutine.ANDROID)

    implementation(Deps.Presentation.Core.ANDROIDX_APPCOMPAT)
    implementation(Deps.Presentation.Core.ANDROIDX_CORE_KTX)

    // Unit Testing
    testImplementation(Deps.Testing.Kotest.RUNNER)
    testImplementation(Deps.Testing.Kotest.ASSERTIONS)
    testImplementation(Deps.Testing.Kotest.PROPERTY)
    // Android Testing
    androidTestImplementation(Deps.Testing.Androidx.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Deps.Testing.Androidx.ANDROIDX_TEST_RULES)
    androidTestImplementation(Deps.Testing.Androidx.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(Deps.Testing.Androidx.ESPRESSO_CORE)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
            }
        }
    }
}
