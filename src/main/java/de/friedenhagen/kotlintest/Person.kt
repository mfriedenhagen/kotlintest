package de.friedenhagen.kotlintest

data class Person(val firstName: String, val name: String, val age:Int) {
    constructor (firstName: String, name: String) : this(firstName, name, 0)

    fun isOfAge():Boolean = age >= 18
}