package de.friedenhagen.kotlintest

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import org.slf4j.LoggerFactory
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
            exchange.responseHeaders.add("Location", "${exchange.requestURI.toASCIIString()}/${count.incrementAndGet()}")
            exchange.sendResponseHeaders(302, response.size.toLong())
            exchange.responseBody.use { it.write(response) }
        }

        fun invalidRequest() {
            val response = "INVALID REQUEST, method=${method}".toByteArray(charset)
            exchange.requestBody.close()
            exchange.sendResponseHeaders(400, response.size.toLong())
            exchange.responseBody.use { it.write(response) }
        }

        when(method) {
            "GET" -> get()
            "POST" -> post()
            else -> invalidRequest()
        }
        exchange.close()
    }
}