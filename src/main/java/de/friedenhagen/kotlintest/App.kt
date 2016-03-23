package de.friedenhagen.kotlintest

import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.composer.ComposerException
import java.io.FileInputStream
import java.util.LinkedHashMap


class App(val filename: String) {
    fun create(): Any {
        val message = parse()
        when (message) {
            is LinkedHashMap<*, *> -> return message
            is Iterable<*> -> return message
            else -> throw IllegalArgumentException("Could not parse '$filename', not a YAML file.")
        }
    }
    private fun parse(): Any {
        val yamlParser = Yaml()
        try {
            return FileInputStream(filename).use { yamlParser.load(it) }
        } catch (e: ComposerException) {
            return FileInputStream(filename).use { yamlParser.loadAll(it).toList() }
        }
    }

}
fun main(args: Array<String>) {
    val filename: String = if (args.size > 0) args[0] else "src/test/resources/foo.yml"
    LoggerFactory.getLogger(App::class.java).info("{}", App(filename).create())
}


