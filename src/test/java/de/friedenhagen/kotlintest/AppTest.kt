package de.friedenhagen.kotlintest

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldEqual
import org.jetbrains.spek.api.shouldNotBeNull
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
                    shouldNotBeNull(message)
                }

            }
        }
        given("the App gets a file with one document") {
            val sut = App("src/test/resources/single.yml")
            on("parsing the file") {
                val message = sut.create()
                it("should not create an exception") {
                    shouldNotBeNull(message)
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