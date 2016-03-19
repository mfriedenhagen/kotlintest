package de.friedenhagen.kotlintest

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldEqual
import java.util.LinkedHashMap
import java.util.ArrayList
import kotlin.test.assertFailsWith

class AppTest : Spek() {
    init {
        given("the App gets a file with multiple documents") {
            val sut = App("src/test/resources/multiple.yml")
            on("checking the filename") {
                it("should have a filename") {
                    shouldEqual("src/test/resources/multiple.yml", sut.filename)
                }
            }
            on("parsing the file") {
                val message = sut.create()
                it("should not create an exception") {
                    shouldEqual(2, (message as ArrayList<*>).size)
                }

            }
        }
        given("the App gets a file with one document") {
            val sut = App("src/test/resources/single.yml")
            on("parsing the file") {
                val message = sut.create()
                it("should not create an exception") {
                    val linkedHashMap = message as LinkedHashMap<*, *>
                    shouldEqual(8, linkedHashMap.size)
                    shouldEqual(34843, linkedHashMap["invoice"])
                }

            }
        }
        given("the App gets a non YAML file") {
            val sut = App("pom.xml")
            on("parsing the file") {
                it("should throw an IllegalArgumentException") {
                    assertFailsWith(IllegalArgumentException::class) {sut.create()}
                }
            }

        }
    }
}