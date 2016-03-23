package de.friedenhagen.kotlintest

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldNotEqual
import org.slf4j.LoggerFactory
import java.net.InetSocketAddress
import java.net.URL
import java.util.concurrent.atomic.AtomicInteger

class MyHandler : HttpHandler {

    private val logger = LoggerFactory.getLogger(MyHandler::class.java)
    private val charset = charset("UTF-8")
    private val count = AtomicInteger()

    override fun handle(exchange: HttpExchange) {
        val method = exchange.requestMethod
        logger.info("${method} ${exchange.requestURI}")
        fun get() {
            val response = "OK, method=${method}".toByteArray(charset)
            // consume inputstream
            exchange.requestBody.close()
            exchange.sendResponseHeaders(200, response.size.toLong())
            exchange.responseBody.use { it.write(response) }
        }

        fun post() {
            exchange.requestBody.use {
                it.reader()
            }
            val response = "OK, method=${method}".toByteArray(charset)
            exchange.responseHeaders.add("Location", "/foo/${count.incrementAndGet()}")
            exchange.sendResponseHeaders(302, response.size.toLong())
            exchange.responseBody.use { it.write(response) }
        }

        fun invalidRequest() {
            exchange.requestBody.close()
            exchange.sendResponseHeaders(400, -1)
            exchange.responseBody.close()
        }

        when(method) {
            "GET" -> get()
            "POST" -> post()
            else -> invalidRequest()
        }
        exchange.close()
    }
}
class HttpServerTest : Spek() {
    init {
        given("The HttpServer did start") {
            val sut = HttpServer.create(InetSocketAddress(8080), 0)
            sut.createContext("/foo", MyHandler())
            sut.executor = null
            sut.start()
//            while(true) Thread.sleep(1000)
            on("querying the /foo context") {
                val text = URL("http://127.0.0.1:8080/foo").readText(charset("UTF-8"))
                it("should not be empty") {
                    shouldNotEqual("", text)
                }
            }
        }
    }
}