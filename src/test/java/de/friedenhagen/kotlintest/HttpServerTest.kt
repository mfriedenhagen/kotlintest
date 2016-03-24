package de.friedenhagen.kotlintest

import com.sun.net.httpserver.HttpServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.shouldNotEqual
import java.net.InetSocketAddress
import java.net.URL

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