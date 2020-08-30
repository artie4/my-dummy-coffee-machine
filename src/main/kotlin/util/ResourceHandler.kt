package util

import java.io.File
import java.io.FileInputStream
import java.util.*

class ResourceHandler(resourcePath: String) {

    private val fis: FileInputStream = FileInputStream(File(resourcePath))
    private val properties: Properties = Properties()

    init {
        properties.load(fis)
    }

    fun getProperty(key: String): String {
        return properties.getProperty(key)
    }

}