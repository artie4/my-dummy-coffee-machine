package machine.util

import java.util.Properties


object ResourceHandler {

    fun getProperties(resourcePath: String): Properties {
        val inputStream = javaClass.classLoader.getResourceAsStream(resourcePath)
        inputStream.use {
            return Properties().apply { load(inputStream) }
        }
    }
}