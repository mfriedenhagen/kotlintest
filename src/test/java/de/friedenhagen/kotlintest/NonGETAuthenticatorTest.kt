package de.friedenhagen.kotlintest

import com.sun.net.httpserver.Authenticator
import com.sun.net.httpserver.Headers
import com.sun.net.httpserver.HttpExchange
import org.junit.Test
import org.mockito.Mockito.`when` as mwhen
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NonGETAuthenticatorTest {

    @Test
    fun authenticateGET() {
        val mockExchange = `mock`(HttpExchange::class.java)
        mwhen(mockExchange.requestMethod).thenReturn("GET")
        val sut = NonGETAuthenticator()
        assertEquals(Authenticator.Success::class.simpleName, sut.authenticate(mockExchange).javaClass.simpleName)
    }

    @Test
    fun authenticatePOSTWithCorrectAuth() {
        val mockExchange = `mock`(HttpExchange::class.java)
        mwhen(mockExchange.requestMethod).thenReturn("POST")
        val requestHeaders = Headers()
        requestHeaders.set("Authorization", "Basic bWU6cA==")
        mwhen(mockExchange.requestHeaders).thenReturn(requestHeaders)
        mwhen(mockExchange.responseHeaders).thenReturn(Headers())
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