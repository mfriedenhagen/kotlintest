package de.friedenhagen.kotlintest

class App(val firstName: String, val name: String)

fun main(args:Array<String>) {
    val person = Person("Mirko", "Hierlebizz")
    val app: App = App("Mirko", "Hierlebizz")
    println("Hallo Person, ${person.firstName} ${person.name}")
    println("Hallo App, ${app.firstName} ${app.name}")
    println(person)
}

