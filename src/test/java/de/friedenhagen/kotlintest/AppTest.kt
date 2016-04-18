package de.friedenhagen.kotlintest

import de.friedenhagen.kotlintest.dto.Invoice
import org.jetbrains.spek.api.Spek
import org.yaml.snakeyaml.error.YAMLException
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
                message.map { it as Invoice }.forEach { assertEquals(34843, it.invoice) }
            }

        }
    }
    given("the App gets a file with one document") {
        val sut = App("src/test/resources/single.yml")
        on("parsing the file") {
            val message = sut.create() as Invoice
            it("should return an Invoice") {
                assertEquals(34843, message.invoice)
            }

        }
    }
    given("the App gets a non YAML file") {
        val sut = App("pom.xml")
        on("parsing the file") {
            it("should throw an YAMLException") {
                assertFailsWith<YAMLException> { sut.create() }
            }
        }

    }
}
)