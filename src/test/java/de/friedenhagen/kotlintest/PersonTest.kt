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
}