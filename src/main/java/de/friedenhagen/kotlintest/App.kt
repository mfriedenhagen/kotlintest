package de.friedenhagen.kotlintest

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.composer.ComposerException
import java.io.FileInputStream
import java.util.*

fun main(args:Array<String>) {
    val message = parse()
    when(message) {
        is LinkedHashMap<*,*> -> println(message)
        is Iterable<*> -> message.forEach { println(it) }
        else -> println(message.javaClass)
    }
}

private fun parse() : Any {
    val yamlParser = Yaml()
    try {
        return FileInputStream("foo.yml").use { yamlParser.load(it) }
    } catch (e: ComposerException) {
        return FileInputStream("foo.yml").use { yamlParser.loadAll(it).toList() }
    }
}

