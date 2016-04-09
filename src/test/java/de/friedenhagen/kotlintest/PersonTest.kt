package de.friedenhagen.kotlintest

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PersonTest : Spek({
    given("John Doe is underage") {
        val sut = Person("John", "Doe")
        on("test if isOfAge") {
            it("should be false") {
                assertFalse(sut.isOfAge())
                assertEquals(0, sut.age)
            }
        }

    }
    given("Jane Doe is old enough") {
        val sut = Person("Jane", "Doe", age = 18)
        on("test if isOfAge") {
            it("should be true") {
                assertTrue(sut.isOfAge())
                assertEquals(18, sut.age)
            }
        }
    }
    given("Jane Doe has name and firstName") {
        val sut = Person("Jane", "Doe", age = 18)
        on("test toString") {
            it("should return sth.") {
                assertEquals("Person(firstName=Jane, name=Doe, age=18)", sut.toString())
            }
        }
    }
    given("Jane Doe is equal") {
        val sut = Person("Jane", "Doe", age = 18)
        on("test is equal") {
            it("should return sth.") {
                assertEquals(Person("Jane", "Doe", age = 18), sut)
            }
        }
    }
})