package io.schinzel.jstranspiler

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.URLClassLoader

@SpringBootApplication
class JsTranspilerApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Hello Kotlin")
        JsTranspiler(
            sourceJarFile = "core.jar",
            sourcePackageName = "io.schinzel.jstranspiler.example",
            destinationFile = "src/main/resources/classes.js"
        )
    }
}

fun main(args: Array<String>) {
    runApplication<JsTranspilerApplication>(*args)
}
