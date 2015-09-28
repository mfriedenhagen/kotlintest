package de.friedenhagen.kotlintest

import org.junit.Test

class PersonTest {

    @Test
    fun testIt() {
        val person = Person("John", "Doe")
        assert(person.age == 0, {"Should be 0 years old by default"})
    }

    @Test
    fun testItOldEnough() {
        val person = Person("John", "Doe", age = 18)
        assert(person.isOfAge(), {"Should be at least 18"})
    }
}