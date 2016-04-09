package de.friedenhagen.kotlintest

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PersonTest : Spek({
    describe("Checking wether John Doe is an adult") {
        val sut = Person("John", "Doe")
        it("should be false") {
            assertFalse(sut.isAdult())
            assertEquals(0, sut.age)
        }
    }
    describe("Checking Jane Doe is old enough") {
        val sut = Person("Jane", "Doe", age = 18)
        it("should be true") {
            assertTrue(sut.isAdult())
            assertEquals(18, sut.age)
        }
    }
    describe("Checking Jane Doe has required firstName and name") {
        val sut = Person("Jane", "Doe", age = 18)
        it("should return a sensible string representation") {
            assertEquals("Person(firstName=Jane, name=Doe, age=18)", sut.toString())
        }
    }
    describe("Checking data class constraints") {
        val sut = Person("Jane", "Doe", age = 18)
        val other = Person("Jane", "Doe", age = 18)
        it("sut should equal other and have the same hashcode") {
            assertEquals(other, sut)
            assertEquals(other.hashCode(), sut.hashCode())
        }
    }
})