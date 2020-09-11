package io.template.app.common.security

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

interface SecurityProvider {
    val defaultMasterKeyAlias: String
    val defaultMasterKey: MasterKey
    fun createDefaultSharedPrefs(fileName: String): SharedPreferences
}

class SecurityProviderImpl(private val context: Context) : SecurityProvider {

    override val defaultMasterKeyAlias = DEFAULT_KEYSTORE_ALIAS

    override val defaultMasterKey by lazy {
        val advancedSpec = KeyGenParameterSpec.Builder(
            DEFAULT_KEYSTORE_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            @Suppress("MagicNumber")
            setKeySize(256)
        }.build()
        MasterKey.Builder(context, DEFAULT_KEYSTORE_ALIAS)
            .setKeyGenParameterSpec(advancedSpec)
            .setUserAuthenticationRequired(false)
            .setRequestStrongBoxBacked(true)
            .build()
    }

    override fun createDefaultSharedPrefs(fileName: String): SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            fileName,
            defaultMasterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    companion object {
        private const val DEFAULT_KEYSTORE_ALIAS = "template_master_key_alias"
    }
}
