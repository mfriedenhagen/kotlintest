package de.friedenhagen.kotlintest

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.composer.ComposerException
import java.io.FileInputStream
import java.util.*

fun main(args: Array<String>) {
    val filename: String = if (args.size > 0) args[0] else "foo.yml"
    val message = parse(filename)
    when (message) {
        is LinkedHashMap<*, *> -> println(message)
        is Iterable<*> -> message.forEach { println(it) }
        else -> println(message.javaClass)
    }
}

private fun parse(filename: String = "foo.yml"): Any {
    val yamlParser = Yaml()
    try {
        return FileInputStream(filename).use { yamlParser.load(it) }
    } catch (e: ComposerException) {
        return FileInputStream(filename).use { yamlParser.loadAll(it).toList() }
    }
}

