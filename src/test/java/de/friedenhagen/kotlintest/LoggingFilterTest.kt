package de.friedenhagen.kotlintest

import com.sun.net.httpserver.Filter
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.net.URI
import java.util.*

class LoggingFilterTest {

    @Test
    fun description() {
        assertEquals("Filter logging to JUL", LoggingFilter().description())
    }

    @Test
    fun doFilter() {
        val mockedHandler = `mock`(HttpHandler::class.java)
        val mockedExchange = `mock`(HttpExchange::class.java)
        `when`(mockedExchange.requestMethod).thenReturn("GET")
        `when`(mockedExchange.requestURI).thenReturn(URI.create("/foo"))
        LoggingFilter().doFilter(mockedExchange, Filter.Chain(ArrayList(), mockedHandler))
    }

}