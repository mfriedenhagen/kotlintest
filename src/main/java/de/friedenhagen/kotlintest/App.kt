package de.friedenhagen.kotlintest

import com.sun.net.httpserver.HttpServer
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.composer.ComposerException
import java.io.FileInputStream
import java.net.InetSocketAddress
import java.util.*
import java.util.logging.LogManager


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
    val logger = LoggerFactory.getLogger(App::class.java)
    val logFile: String? = System.getProperty("java.util.logging.config.file")
    if (logFile == null) {
        LogManager.getLogManager().readConfiguration(
                App::class.java.classLoader.getResourceAsStream("logging.properties"));
        logger.info("Read internal logging.properties")
    }
    val httpServer = HttpServer.create(InetSocketAddress(8080), 0)
    val context = httpServer.createContext("/foo", MyHandler())
    context.authenticator = NonGETAuthenticator()
    httpServer.executor = null
    httpServer.start()
    logger.info("HttpServer started")
    try {
        while(true) Thread.sleep(1000)
    } finally {
        httpServer.stop(0)
        logger.info("HttpServer stopped")
    }
}


