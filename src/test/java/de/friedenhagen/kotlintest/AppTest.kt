package de.friedenhagen.kotlintest

import org.hamcrest.core.Is
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldEqual
import org.jetbrains.spek.api.shouldNotBeNull
import org.jetbrains.spek.api.shouldThrow
import org.junit.Assert.assertThat
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class App1Test : Spek() {
    init {
        given("The App gets a filename with multiple documents") {
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
    }
}

class App2Test : Spek() {
    init {
        given("The App gets a filename with one document") {
            val sut = App("src/test/resources/single.yml")
            on("parsing the file") {
                val message = sut.create()
                it("should not create an exception") {
                    shouldNotBeNull(message)
                }

            }
        }
    }
}
class App3Test : Spek() {
    init {
        given("The App gets a wrong filename") {
            val sut = App("pom.xml")
            on("parsing the file") {
                it("should throw an Exception") {
                    val e = {sut.create()}
                    //assertFailsWith(IllegalArgumentException.class, e)
                    //shouldThrow(IllegalArgumentException.class, e.invoke())
                }
            }

        }
    }
}