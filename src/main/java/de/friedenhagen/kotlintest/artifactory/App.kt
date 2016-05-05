package de.friedenhagen.kotlintest.artifactory

import com.fasterxml.jackson.databind.ObjectMapper
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
        return url.openStream().buffered().use { mapper.readValue(it, Array<Repository>::class.java) }

    }

    companion object {
        val mapper = ObjectMapper()
        val logger = LoggerFactory.getLogger(App::class.java)
    }
}

fun main(args: Array<String>) {
    val app = App(URI("http://docker-default:8081/artifactory/"))
    val repositories = app.getRepositories()
    repositories.groupBy { it.type }.forEach {
        println(it.key)
        it.value.forEach { print(it) }
        println()
    }
    App.logger.info("Size {}", repositories.size)
}