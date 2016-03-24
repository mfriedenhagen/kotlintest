package de.friedenhagen.kotlintest

import com.sun.net.httpserver.Headers
import com.sun.net.httpserver.HttpExchange
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldEqual
import org.mockito.Mockito.`mock`
import org.mockito.Mockito.`when`
import java.net.URI

class MyHandlerTest : Spek() {
    init {
        given("We recieve a GET request") {
            val exchange = createHttpExchange(ByteInputStream())
            `when`(exchange.requestMethod).thenReturn("GET")
            val sut = MyHandler()
            on("handling the request") {
                sut.handle(exchange)
                it("should return something") {
                    shouldEqual("OK, method=GET", exchange.responseBody.toString())
                }
            }
        }
        given("We recieve a POST request") {
            val exchange = createHttpExchange(ByteInputStream())
            `when`(exchange.requestMethod).thenReturn("POST")
            val sut = MyHandler()
            on("handling the request") {
                sut.handle(exchange)
                it("should return something") {
                    shouldEqual("OK, method=POST", exchange.responseBody.toString())
                    shouldEqual("/foo/1", exchange.responseHeaders.getFirst("Location"))
                }
            }
        }
        given("We recieve an invalid request") {
            val exchange = createHttpExchange(ByteInputStream())
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

    private fun createHttpExchange(inputStream: ByteInputStream): HttpExchange {
        val exchange = `mock`(HttpExchange::class.java)
        `when`(exchange.requestURI).thenReturn(URI.create("/foo"))
        `when`(exchange.responseBody).thenReturn(ByteOutputStream())
        `when`(exchange.requestBody).thenReturn(inputStream)
        `when`(exchange.responseHeaders).thenReturn(Headers())
        return exchange
    }
}