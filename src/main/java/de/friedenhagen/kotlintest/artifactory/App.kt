package de.friedenhagen.kotlintest.artifactory

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.ObjectMapper as UnirestObjectMapper
import com.mashape.unirest.http.Unirest
import org.slf4j.LoggerFactory
import java.net.URI

enum class RepositoryType {
    LOCAL,
    REMOTE,
    VIRTUAL
}

data class Repository(
        val key: String = "",
        val type: RepositoryType = RepositoryType.LOCAL,
        val url: URI = URI(""),
        val description: String = "")

class App(val artifactoryURI: URI) {

    fun getRepositories(): Array<Repository> {
        val url = artifactoryURI.resolve("api/repositories").toURL()
        Unirest.setObjectMapper(MyObjectMapper(mapper))
        return Unirest.get(url.toString()).asObject(Array<Repository>::class.java).body
    }

    companion object {
        val mapper = ObjectMapper()
        val logger = LoggerFactory.getLogger(App::class.java)
    }
}

class MyObjectMapper(val mapper : ObjectMapper) : UnirestObjectMapper {
    override fun <T : Any?> readValue(value: String?, valueType: Class<T>?): T = mapper.readValue(value, valueType)
    override fun writeValue(value: Any?): String? = mapper.writeValueAsString(value)

}

fun main(args: Array<String>) {
    val app = App(URI("http://repository.jetbrains.com/"))
    val repositories = app.getRepositories()
    repositories.groupBy { it.type }.forEach {
        println(it.key)
        it.value.forEach { print(it) }
        println()
    }
    App.logger.info("Size {}", repositories.size)
}