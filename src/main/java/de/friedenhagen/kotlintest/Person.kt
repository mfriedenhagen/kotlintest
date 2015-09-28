package de.friedenhagen.kotlintest

data class Person(val firstName: String, val name: String, val age:Int) {
    constructor (firstName: String, name: String) : this(firstName, name, 0)

    val AGE_LIMIT: Int = 18

    fun isOfAge():Boolean = age >= AGE_LIMIT
}