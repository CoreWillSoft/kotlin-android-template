import util.properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
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
        coreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    signingConfigs {
        listOf("debug", "release").forEach { configName ->
            util.SigningData.of(properties(file("config/signing/$configName/signing.properties")))
                ?.let {
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
                "config/proguard/kotlinx-serialization-proguard-rules.pro"
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
    coreLibraryDesugaring(Deps.Core.DESUGARING)
    implementation(Deps.Core.KOTLIN_REFLECT)
    implementation(Deps.Core.Coroutine.CORE)
    implementation(Deps.Core.Coroutine.ANDROID)

    // DI
    implementation(Deps.Di.ANDROIDX_SCOPE)
    implementation(Deps.Di.ANDROIDX_VIEWMODEL)
    implementation(Deps.Di.ANDROIDX_FRAGMENET)
    implementation(Deps.Di.ANDROIDX_EXT)

    // Presentation
    implementation(Deps.Presentation.Core.ANDROIDX_CORE_KTX)
    implementation(Deps.Presentation.Core.ANDROIDX_APPCOMPAT)
    implementation(Deps.Presentation.Core.ANDROID_MATERIAL)
    implementation(Deps.Presentation.Lifecycle.VIEWMODEL)
    implementation(Deps.Presentation.Lifecycle.COMMON)
    implementation(Deps.Presentation.Lifecycle.PROCESS)
    implementation(Deps.Presentation.Fragment.KTX)
    implementation(Deps.Presentation.Navigation.FRAGMENT)
    implementation(Deps.Presentation.Navigation.KTX)
    implementation(Deps.Presentation.Navigation.DYNAMIC_FEATURES)
    implementation(Deps.Presentation.Mvi.CORE)
    implementation(Deps.Presentation.Mvi.COROUTINES)
    implementation(Deps.Presentation.Mvi.VIEWMODEL)
    implementation(Deps.Presentation.Mvi.LIVEDATA)
    // Widget
    implementation(Deps.Presentation.Widget.ANDROIDX_CONSTRAINT_LAYOUT)

    // Security
    implementation(Deps.Security.ANDROIDX)
    implementation(Deps.Security.KTX)

    // IO
    implementation(Deps.IO.KOTLINX_SERIALIZATION)

    // Util
    implementation(Deps.Util.TIMBER)

    // Unit Testing
    testImplementation(Deps.Testing.Kotest.RUNNER)
    testImplementation(Deps.Testing.Kotest.ASSERTIONS)
    testImplementation(Deps.Testing.Kotest.PROPERTY)
    testImplementation(Deps.Testing.Kotest.JUNIT_XML)
    testImplementation(Deps.Testing.Kotest.KOIN)
    testImplementation(Deps.Testing.Helpers.MOCKITO_KOTLIN)
    testImplementation(Deps.Testing.Helpers.MOCKITO_INLINE)
    testImplementation(Deps.Testing.Helpers.MOCKK)
    testImplementation(Deps.Testing.Helpers.FIXTURE)
    //
    testImplementation(Deps.Presentation.Mvi.TEST)
    // Android Testing
    androidTestImplementation(Deps.Di.CORE_TEST)
    androidTestImplementation(Deps.Testing.Helpers.JUNIT)
    androidTestImplementation(Deps.Testing.Androidx.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Deps.Testing.Androidx.ANDROIDX_TEST_RULES)
    androidTestImplementation(Deps.Testing.Androidx.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(Deps.Testing.Androidx.ESPRESSO_CORE)
    androidTestImplementation(Deps.Presentation.Fragment.TESTING)
    androidTestImplementation(Deps.Presentation.Navigation.TESTING)
}
