package util

import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*

fun Project.cmd(vararg command: String) = try {
    val stdOut = ByteArrayOutputStream()
    exec {
        commandLine(*command)
        standardOutput = stdOut
    }
    stdOut.toString(Charsets.UTF_8.name()).trim()
} catch (e: Throwable) {
    e.printStackTrace()
    null
}

fun Project.properties(file: File): Properties? {
    if (!file.exists())
        return null
    val props = Properties()
    props.load(file.inputStream())
    return props
}

data class SigningData(
    val storeFile: String,
    val storePassword: String,
    val keyAlias: String,
    val keyPassword: String
) {
    companion object {
        fun of(properties: Properties?): SigningData? {
            if (properties == null) return null

            val storeFile = properties.getProperty("storeFile") ?: return null
            val storePassword = properties.getProperty("storePassword") ?: return null
            val keyAlias = properties.getProperty("keyAlias") ?: return null
            val keyPassword = properties.getProperty("keyPassword") ?: return null

            return SigningData(storeFile, storePassword, keyAlias, keyPassword)
        }
    }
}

