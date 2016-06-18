package de.friedenhagen.kotlintest

import io.kotlintest.specs.FlatSpec

class Person2Test : FlatSpec() {
    init {
        "John Doe" should "not be an adult" {
            val sut = Person("John", "Doe")
            sut.isAdult() shouldBe false
            sut.age shouldEqual 0
        }
        "Jane Doe" should "be an adult" {
            val sut = Person("Jane", "Doe", age = 18)
            sut.isAdult() shouldBe true
            sut.age shouldEqual 18
        }
    }
}