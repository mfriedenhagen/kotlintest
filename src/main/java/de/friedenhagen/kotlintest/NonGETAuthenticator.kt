package de.friedenhagen.kotlintest

import com.sun.net.httpserver.BasicAuthenticator
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpPrincipal

class NonGETAuthenticator : BasicAuthenticator("REALM") {

    override fun authenticate(exchange: HttpExchange) : Result {
        if (exchange.requestMethod.equals("GET")) {
            return Success(HttpPrincipal("ANONYMOUS", realm))
        } else {
            return super.authenticate(exchange)
        }
    }

    override fun checkCredentials(user: String?, password: String?): Boolean {
        return "me".equals(user) && "p".equals(password)
    }

}