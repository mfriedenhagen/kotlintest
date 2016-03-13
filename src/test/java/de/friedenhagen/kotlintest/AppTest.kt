package de.friedenhagen.kotlintest

import org.hamcrest.core.Is
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldEqual
import org.jetbrains.spek.api.shouldNotBeNull
import org.jetbrains.spek.api.shouldThrow
import org.junit.Assert.assertThat
import kotlin.test.assertNotNull

class AppTest1 : Spek() {
    init {
        given("The App has a filename") {
            val sut = App("src/test/resources/foo.yml")
            on("checking the filename") {
                it("should have a filename") {
                    shouldEqual("src/test/resources/foo.yml", sut.filename)
                }
            }
            on("parsing the file") {
                val message = sut.create()
                it("should not be an exception") {
                    shouldNotBeNull(message)
                }

            }
        }
    }
}

class AppTest2 : Spek() {
    init {
        given("The App gets a wrong filename") {
            val sut = App("pom.xml")
            on("parsing the file") {
                it("should throw an Exception") {
                    val e = {sut.create()}
                    //shouldThrow(IllegalArgumentException.class, e.invoke())
                }
            }

        }
    }
}