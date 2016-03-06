package de.friedenhagen.kotlintest

import org.junit.Assert
import org.junit.Test

class PersonTest {

    @Test
    fun testIt() {
        val person = Person("John", "Doe")
        Assert.assertEquals(0, person.age)
    }

    @Test
    fun testItOldEnough() {
        val person = Person("John", "Doe", age = 18)
        Assert.assertTrue("Should be at least ${person.AGE_LIMIT}, is ${person.age}", person.isOfAge())
    }

    @Test
    fun testToString() {
        Assert.assertEquals("Person(firstName=John, name=Doe, age=18)", Person("John", "Doe", age = 18).toString())
    }

    @Test
    fun testEquals() {
        val person = Person("John", "Doe", age = 18)
        Assert.assertEquals(person, Person("John", "Doe", age = 18))
    }
}