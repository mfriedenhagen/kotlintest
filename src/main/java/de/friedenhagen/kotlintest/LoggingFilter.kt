package de.friedenhagen.kotlintest

import com.sun.net.httpserver.Filter
import com.sun.net.httpserver.HttpExchange
import org.slf4j.LoggerFactory

class LoggingFilter : Filter() {

    val logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun description(): String? = "Filter logging to JUL"

    override fun doFilter(exchange: HttpExchange, chain: Chain) {
        logger.info("""${exchange.requestMethod} ${exchange.requestURI}""")
        chain.doFilter(exchange)
    }

}