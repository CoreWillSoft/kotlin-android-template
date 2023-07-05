package io.template

import android.content.res.Resources
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import io.template.app.R

@RunWith(RobolectricTestRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LocalizationTest {

    companion object {
        private val PLURAL_QUANTITIES = listOf(0, 1, 2, 3, 5, 11, 21, 42, 102)
        private val PLATFORM_PATTERN = Regex("%s|%d|%\\d[$]s|%\\d[$]d")
        private val PHRASE_PATTERN = Regex("[{].*?[}]")
        private val SKIP_KEYS = listOf("abc_shareactionprovider_share_with_application")

        private var platformPatternMap: Map<String, List<String>> = mapOf()
        private var phrasePatternMap: Map<String, List<String>> = mapOf()
    }

    private val resources: Resources
        get() = RuntimeEnvironment.getApplication().resources

    @Test
    @Config(sdk = [28])
    fun initPatterns() {
        platformPatternMap = getAllStringsAndPlurals()
                .associate { (key, string) -> key to PLATFORM_PATTERN.findAll(string).map { it.value }.toList() }
                .filter { (key) -> !SKIP_KEYS.contains(key) }
                .filter { (_, list) -> list.isNotEmpty() }
        println("platformPatternMap.size - ${platformPatternMap.size}")

        phrasePatternMap = getAllStringsAndPlurals()
                .associate { (key, string) -> key to PHRASE_PATTERN.findAll(string).map { it.value }.toList() }
                .filter { (key) -> !SKIP_KEYS.contains(key) }
                .filter { (_, list) -> list.isNotEmpty() }
        println("phrasePatternMap.size - ${phrasePatternMap.size}")
    }

    private fun getAllStringsAndPlurals(): List<Pair<String, String>> {
        val strings = R.string::class.java.fields.map { it.name to resources.getString(it.getInt(null)) }
        val quantities = R.plurals::class.java.fields.map { it.name to resources.getQuantityString(it.getInt(null), 1) }
        return strings + quantities
    }

    @Test
    @Config(sdk = [28], qualifiers = "en")
    fun testEnglish() = test()

    @Test
    @Config(sdk = [28], qualifiers = "de")
    fun testGerman() = test()


    private fun test() {
        testStrings()
        testPlurals()
    }

    private fun testStrings() {
        R.string::class.java.fields.forEach { field ->
            val key = field.name
            val resourceValue = field.getInt(null)
            val value = resources.getString(resourceValue)

            platformPatternMap[key].let { expected ->
                if (expected.isNullOrEmpty()) return@let

                val actual = PLATFORM_PATTERN.findAll(value).map { it.value }.toMutableList()

                assertEquals(expected.sorted(), actual.sorted(), "Format arguments are not equal for string with key - $key.")
                assertDoesNotThrow("Creating formatted string should be possible with key - $key.") {
                    resources.getString(
                            /* id = */ resourceValue,
                            /* ...formatArgs = */ *expected.map { if (it.contains('s')) "test" else 1 }.toTypedArray()
                    )
                }
            }

            phrasePatternMap[key]?.onEach { expectedKey ->
                assert(value.contains(expectedKey)) { "Key - $key, value - $value, should contain $expectedKey" }
            }
        }
    }

    private fun testPlurals() {
        R.plurals::class.java.fields.forEach { field ->
            PLURAL_QUANTITIES.onEach { quantity ->
                val key = field.name
                val resourceValue = field.getInt(null)
                val value = assertDoesNotThrow("Failed to crate plural with key - $key and quantity - $quantity") {
                    resources.getQuantityString(resourceValue, quantity)
                }
                assert(value.isNotBlank()) { "Value for key - $key and quantity - $quantity should no be blank" }

                platformPatternMap[key].let { expected ->
                    if (expected.isNullOrEmpty()) return@let

                    val actual = PLATFORM_PATTERN.findAll(value).map { it.value }.toMutableList()

                    assertEquals(expected.sorted(), actual.sorted(), "Format arguments are not equal for plural with key - $key " +
                            "and quantity - $quantity.")
                    assertDoesNotThrow("Creating formatted plural should be possible with key - $key and quantity - $quantity.") {
                        resources.getQuantityString(
                                /* id = */ resourceValue,
                                /* quantity = */ quantity,
                                /* ...formatArgs = */ *expected.map { if (it.contains('s')) "test" else 1 }.toTypedArray()
                        )
                    }
                }

                phrasePatternMap[key]?.onEach { phraseKey ->
                    assert(value.contains(phraseKey)) { "Key - $key, value - $value, should contain $phraseKey" }
                }
            }
        }
    }
}
