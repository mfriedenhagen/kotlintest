package de.friedenhagen.kotlintest

import org.jetbrains.spek.api.Spek
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AppTest : Spek({
    given("the App gets a file with multiple documents") {
        val sut = App("src/test/resources/multiple.yml")
        on("checking the filename") {
            it("should have a filename") {
                assertEquals("src/test/resources/multiple.yml", sut.filename)
            }
        }
        on("parsing the file") {
            val message = sut.create()
            it("should return an ArrayList") {
                assertEquals(2, (message as ArrayList<*>).size)
            }

        }
    }
    given("the App gets a file with one document") {
        val sut = App("src/test/resources/single.yml")
        on("parsing the file") {
            val message = sut.create()
            it("should return a LinkedHashMap") {
                val linkedHashMap = message as LinkedHashMap<*, *>
                assertEquals(8, linkedHashMap.size)
                assertEquals(34843, linkedHashMap["invoice"])
            }

        }
    }
    given("the App gets a non YAML file") {
        val sut = App("pom.xml")
        on("parsing the file") {
            it("should throw an IllegalArgumentException") {
                assertFailsWith(IllegalArgumentException::class) { sut.create() }
            }
        }

    }
}
)