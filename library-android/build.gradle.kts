version = LibraryAndroidCoordinates.LIBRARY_VERSION

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    compileSdk = AppCoordinates.Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AppCoordinates.Sdk.MIN_SDK_VERSION
        targetSdk = AppCoordinates.Sdk.TARGET_SDK_VERSION

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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    lint {
        warningsAsErrors = true
        abortOnError = true
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
    androidTestImplementation(Deps.Testing.Androidx.TEST_EXT_JUNIT)
    androidTestImplementation(Deps.Testing.Androidx.TEST_RULES)
    androidTestImplementation(Deps.Testing.Androidx.TEST_RUNNER)
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
