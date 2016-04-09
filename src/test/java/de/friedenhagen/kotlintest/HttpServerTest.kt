package de.friedenhagen.kotlintest

import com.sun.net.httpserver.HttpServer
import org.jetbrains.spek.api.Spek
import java.net.InetSocketAddress
import java.net.URL
import kotlin.test.assertNotEquals

class HttpServerTest : Spek({
    given("The HttpServer did start") {
        val sut = HttpServer.create(InetSocketAddress(8090), 0)
        sut.createContext("/foo", MyHandler())
        sut.executor = null
        sut.start()
        on("querying the /foo context") {
            val text = URL("http://127.0.0.1:8090/foo").readText(charset("UTF-8"))
            it("should not be empty") {
                assertNotEquals("", text)
            }
            sut.stop(0)
        }
    }
})