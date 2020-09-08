import util.properties

plugins {
    id("com.android.application")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AppCoordinates.Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AppCoordinates.Sdk.MIN_SDK_VERSION)
        targetSdkVersion(AppCoordinates.Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resConfigs("en", "de")
    }
    compileOptions {
        coreLibraryDesugaringEnabled=true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    signingConfigs {
        listOf("debug", "release").forEach { configName ->
            util.SigningData.of(project.properties(file("config/signing/$configName/signing.properties")))?.let {
                val action = Action<com.android.build.gradle.internal.dsl.SigningConfig> {
                    storeFile = file(it.storeFile)
                    storePassword = it.storePassword
                    keyAlias = it.keyAlias
                    keyPassword = it.keyPassword
                }
                try {
                    getByName(configName, action)
                } catch (e: Throwable) {
                    create(configName, action)
                }
            }
        }
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-dev"
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = try {
                signingConfigs.getByName("release")
            } catch (e: Exception) {
                signingConfigs.getByName("debug")
            }
        }
    }
    buildFeatures {
        viewBinding = true
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {
    implementation(project(":library-android"))
    implementation(project(":library-kotlin"))

    // Core
    coreLibraryDesugaring(Dependencies.Core.DESUGARING)
    implementation(Dependencies.Core.KOTLIN_COROUTINES)

    // UI
    implementation(Dependencies.Presentation.Core.ANDROIDX_CORE_KTX)
    implementation(Dependencies.Presentation.Core.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.Presentation.Core.ANDROID_MATERIAL)
    implementation(Dependencies.Presentation.Lifecycle.VIEWMODEL)
    implementation(Dependencies.Presentation.Lifecycle.COMMON)
    implementation(Dependencies.Presentation.Lifecycle.PROCESS)
    implementation(Dependencies.Presentation.Fragment.KTX)
    implementation(Dependencies.Presentation.Navigation.FRAGMENT)
    implementation(Dependencies.Presentation.Navigation.KTX)
    implementation(Dependencies.Presentation.Navigation.DYNAMIC_FEATURES)
    // Widget
    implementation(Dependencies.Presentation.Widget.ANDROIDX_CONSTRAINT_LAYOUT)

    // Testing
    testImplementation(Dependencies.Testing.JUNIT)
    androidTestImplementation(Dependencies.Testing.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Dependencies.Testing.ANDROIDX_TEST_RULES)
    androidTestImplementation(Dependencies.Testing.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.Presentation.Fragment.TESTING)
    androidTestImplementation(Dependencies.Presentation.Navigation.TESTING)
}
