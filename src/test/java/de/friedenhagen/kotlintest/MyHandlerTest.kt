package de.friedenhagen.kotlintest

import com.sun.net.httpserver.Headers
import com.sun.net.httpserver.HttpExchange
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.givenData
import org.jetbrains.spek.api.shouldEqual
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.net.URI
import java.util.*

class MyHandlerTest : Spek() {

    val emptyInputStream = ByteArrayInputStream(ByteArray(0))

    init {
        given("we recieve a GET request") {
            val exchange = createHttpExchange(emptyInputStream)
            `when`(exchange.requestMethod).thenReturn("GET")
            val sut = MyHandler()
            on("handling the GET request") {
                sut.handle(exchange)
                it("should return something") {
                    shouldEqual("OK, method=GET", exchange.responseBody.toString())
                }
            }
        }
        given("we recieve a HEAD request") {
            val exchange = createHttpExchange(emptyInputStream)
            `when`(exchange.requestMethod).thenReturn("HEAD")
            val sut = MyHandler()
            on("handling the HEAD request") {
                sut.handle(exchange)
                it("should return nothing") {
                    shouldEqual("", exchange.responseBody.toString())
                }
            }
        }

        givenData(Arrays.asList(emptyInputStream, ByteArrayInputStream("Hello".toByteArray(charset("UTF-8"))))) {
            val exchange = createHttpExchange(it)
            `when`(exchange.requestMethod).thenReturn("POST")
            val sut = MyHandler()
            on("handling the POST request with body ${it}") {
                sut.handle(exchange)
                it("should return something") {
                    shouldEqual("OK, method=POST", exchange.responseBody.toString())
                    shouldEqual("/foo/1", exchange.responseHeaders.getFirst("Location"))
                }
            }
        }
        given("we recieve an invalid request") {
            val exchange = createHttpExchange(emptyInputStream)
            `when`(exchange.requestMethod).thenReturn("OPTIONS")
            val sut = MyHandler()
            on("handling the request") {
                sut.handle(exchange)
                it("should return an INVALID REQUEST") {
                    shouldEqual("INVALID REQUEST, method=OPTIONS", exchange.responseBody.toString())
                }
            }
        }
    }

    private fun createHttpExchange(inputStream: ByteArrayInputStream): HttpExchange {
        val exchange = `mock`(HttpExchange::class.java)
        `when`(exchange.requestURI).thenReturn(URI.create("/foo"))
        `when`(exchange.responseBody).thenReturn(ByteArrayOutputStream())
        `when`(exchange.requestBody).thenReturn(inputStream)
        `when`(exchange.responseHeaders).thenReturn(Headers())
        return exchange
    }
}