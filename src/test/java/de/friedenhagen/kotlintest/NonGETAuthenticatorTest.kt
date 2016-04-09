package de.friedenhagen.kotlintest

import com.sun.net.httpserver.Authenticator
import com.sun.net.httpserver.Headers
import com.sun.net.httpserver.HttpExchange
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NonGETAuthenticatorTest {

    @Test
    fun authenticateGET() {
        val mockExchange = `mock`(HttpExchange::class.java)
        `when`(mockExchange.requestMethod).thenReturn("GET")
        val sut = NonGETAuthenticator()
        assertEquals(Authenticator.Success::class.simpleName, sut.authenticate(mockExchange).javaClass.simpleName)
    }

    @Test
    fun authenticatePOSTWithCorrectAuth() {
        val mockExchange = `mock`(HttpExchange::class.java)
        `when`(mockExchange.requestMethod).thenReturn("POST")
        val requestHeaders = Headers()
        requestHeaders.set("Authorization", "Basic bWU6cA==")
        `when`(mockExchange.requestHeaders).thenReturn(requestHeaders)
        `when`(mockExchange.responseHeaders).thenReturn(Headers())
        val sut = NonGETAuthenticator()
        assertEquals(Authenticator.Success::class.simpleName, sut.authenticate(mockExchange).javaClass.simpleName)
    }

    @Test
    fun checkCredentials() {
        val sut = NonGETAuthenticator()
        assertTrue(sut.checkCredentials("me", "p"))
        assertFalse(sut.checkCredentials("me", ""))
    }

}