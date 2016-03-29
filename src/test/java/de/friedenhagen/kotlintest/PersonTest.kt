package de.friedenhagen.kotlintest

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldBeFalse
import org.jetbrains.spek.api.shouldBeTrue
import org.jetbrains.spek.api.shouldEqual

class PersonTest : Spek() {
    init {
        given("John Doe is underage") {
            val sut = Person("John", "Doe")
            on("test if isOfAge") {
                it("should be false") {
                    shouldBeFalse(sut.isOfAge())
                    shouldEqual(0, sut.age)
                }
            }

        }
        given("Jane Doe is old enough") {
            val sut = Person("Jane", "Doe", age = 18)
            on("test if isOfAge") {
                it("should be true") {
                    shouldBeTrue(sut.isOfAge())
                    shouldEqual(18, sut.age)
                }
            }
        }
        given("Jane Doe has name and firstName") {
            val sut = Person("Jane", "Doe", age = 18)
            on("test toString") {
                it("should return sth.") {
                    shouldEqual("Person(firstName=Jane, name=Doe, age=18)", sut.toString())
                }
            }
        }
        given("Jane Doe is equal") {
            val sut = Person("Jane", "Doe", age = 18)
            on("test is equal") {
                it("should return sth.") {
                    shouldEqual(Person("Jane", "Doe", age = 18), sut)
                }
            }
        }
    }
}