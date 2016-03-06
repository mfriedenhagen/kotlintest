package de.friedenhagen.kotlintest

class App(val firstName: String, val name: String)

fun main(args:Array<String>) {
    val person = Person("Mirko", "Hierlebizz", 18)
    val app = App("Mirko", "Hierlebizz")
    println("Hallo Person, ${person.firstName} ${person.name}")
    println("Hallo App, ${app.firstName} ${app.name}")
    println(person)
}

