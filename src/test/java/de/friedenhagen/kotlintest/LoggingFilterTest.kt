package de.friedenhagen.kotlintest

import com.sun.net.httpserver.Filter
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import org.jetbrains.spek.api.Spek

import org.mockito.Mockito.`when` as mwhen
import org.mockito.Mockito.mock
import java.net.URI
import java.util.*
import kotlin.test.assertEquals

class LoggingFilterTest : Spek({
    describe("Check description") {
        it("should have a description") {
            assertEquals("Filter logging to JUL", LoggingFilter().description())
        }
    }
    describe("Should filter") {
        val mockedHandler = `mock`(HttpHandler::class.java)
        val mockedExchange = `mock`(HttpExchange::class.java)
        mwhen(mockedExchange.requestMethod).thenReturn("GET")
        mwhen(mockedExchange.requestURI).thenReturn(URI.create("/foo"))
        it("") {
            LoggingFilter().doFilter(mockedExchange, Filter.Chain(ArrayList(), mockedHandler))
        }
    }
})
