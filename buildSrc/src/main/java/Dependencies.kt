object BuildPluginsVersions {

    const val AGP = "4.0.1"
    const val KOTLIN = "1.4.0"

    const val DOKKA = "1.4.0"
    const val DETEKT = "1.12.0"

    object KTLINT {
        const val PLUGIN = "9.3.0"
        const val CONFIG = "0.38.1"
    }

    const val DEPENDENCY_UPDATES = "0.29.0"
}

object Dependencies {

    object Core {
        const val DESUGARING = "com.android.tools:desugar_jdk_libs:1.0.9"
        const val KOTLIN_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9"
    }

    object IO {

    }

    object Presentation {

        object Core {
            const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:1.3.1"
            const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
            const val ANDROID_MATERIAL = "com.google.android.material:material:1.2.1"
        }

        object Widget {
            const val ANDROIDX_CONSTRAINT_LAYOUT =
                    "androidx.constraintlayout:constraintlayout:2.0.1"
        }
    }

    object Testing {
        const val JUNIT = "junit:junit:4.13"
        const val ANDROIDX_TEST_RULES = "androidx.test:rules:1.3.0"
        const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:1.3.0"
        const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:1.1.2"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.3.0"
    }
}
