object BuildPluginsVersions {

    const val AGP = "4.0.1"
    const val KOTLIN = "1.4.10"

    const val DOKKA = "1.4.0"
    const val DETEKT = "1.12.0"

    object KTLINT {
        const val PLUGIN = "9.4.0"
        const val CONFIG = "0.38.1"
    }

    const val DEPENDENCY_UPDATES = "0.31.0"
}

object Deps {

    object Core {
        const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:1.4.10"
        const val DESUGARING = "com.android.tools:desugar_jdk_libs:1.0.9"

        object Coroutine {
            private const val version = "1.3.9"
            const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"

        }
    }

    object Di {
        private const val koin_version = "2.1.6"
        const val CORE = "org.koin:koin-core:$koin_version"
        const val CORE_EXT = "org.koin:koin-core-ext:$koin_version"
        const val CORE_TEST = "org.koin:koin-test:$koin_version"
        const val ANDROIDX_SCOPE = "org.koin:koin-androidx-scope:$koin_version"
        const val ANDROIDX_VIEWMODEL = "org.koin:koin-androidx-viewmodel:$koin_version"
        const val ANDROIDX_FRAGMENET = "org.koin:koin-androidx-fragment:$koin_version"
        const val ANDROIDX_EXT = "org.koin:koin-androidx-ext:$koin_version"
    }

    object IO {
        const val KOTLINX_SERIALIZATION =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC"
    }

    object Presentation {

        object Core {
            const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:1.3.1"
            const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
            const val ANDROID_MATERIAL = "com.google.android.material:material:1.2.1"
        }

        object Lifecycle {
            private const val lifecycle_version = "2.2.0"
            const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
            const val COMMON = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
            const val PROCESS = "androidx.lifecycle:lifecycle-process:$lifecycle_version"

        }

        object Fragment {
            private const val version = "1.2.5"
            const val KTX = "androidx.fragment:fragment-ktx:$version"
            const val TESTING = "androidx.fragment:fragment-testing:$version"
        }

        object Navigation {
            const val VERSION = "2.3.0"
            const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VERSION"
            const val KTX = "androidx.navigation:navigation-ui-ktx:$VERSION"
            const val DYNAMIC_FEATURES =
                "androidx.navigation:navigation-dynamic-features-fragment:$VERSION"
            const val TESTING = "androidx.navigation:navigation-testing:$VERSION"
        }

        object Widget {
            const val ANDROIDX_CONSTRAINT_LAYOUT =
                "androidx.constraintlayout:constraintlayout:2.0.1"
        }

        object Mvi {
            private const val version = "1.0.0"
            const val CORE = "com.babylon.orbit2:orbit-core:$version"
            const val COROUTINES = "com.babylon.orbit2:orbit-coroutines:$version"
            const val LIVEDATA = "com.babylon.orbit2:orbit-livedata:$version"
            const val VIEWMODEL = "com.babylon.orbit2:orbit-viewmodel:$version"

            const val TEST = "com.babylon.orbit2:orbit-test:$version"
        }
    }

    object Security {
        private const val version = "1.1.0-alpha02"
        const val ANDROIDX = "androidx.security:security-crypto:$version"
        const val KTX = "androidx.security:security-crypto-ktx:$version"
    }

    object Util {
        const val TIMBER = "com.jakewharton.timber:timber:4.7.1"
    }

    object Testing {
        object Kotest {
            private const val version = "4.2.4"
            const val RUNNER = "io.kotest:kotest-runner-junit5:$version"
            const val ASSERTIONS = "io.kotest:kotest-assertions-core:$version"
            const val PROPERTY = "io.kotest:kotest-property:$version"
            const val JUNIT_XML = "io.kotest:kotest-extensions-junitxml:$version"
            const val KOIN = "io.kotest:kotest-extensions-koin:$version"
        }

        object Util {
            const val JUNIT = "junit:junit:4.13"
            const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
            const val MOCKITO_INLINE = "org.mockito:mockito-inline:3.5.10"
            const val MOCKK = "io.mockk:mockk:1.10.0"
            const val FIXTURE = "com.appmattus.fixture:fixture:0.9.5"
        }

        object Androidx {
            const val TEST_CORE = "androidx.arch.core:core-testing:2.1.0"
            const val TEST_RULES = "androidx.test:rules:1.3.0"
            const val TEST_RUNNER = "androidx.test:runner:1.3.0"
            const val TEST_EXT_JUNIT = "androidx.test.ext:junit:1.1.2"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.3.0"
            const val TEST_MONITOR = "androidx.test:core:1.3.0"
        }

        object UI {
            const val KAKAO = "com.agoda.kakao:kakao:2.3.4"
            const val BARISTA = "com.schibsted.spain:barista:3.6.0"

            object TestButler {
                private const val version = "2.1.0"
                const val LIBRARY = "com.linkedin.testbutler:test-butler-library:$version"
                const val APP = "com.linkedin.testbutler:test-butler-app:$version"
            }
        }
    }
}
