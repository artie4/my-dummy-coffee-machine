package machine.util


import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ResourceHandlerTest {

    @Test
    fun loadResources_successful() {
        val properties = ResourceHandler.getProperties("coffee-machine-init-state.properties")
        assertNotNull(properties)
        assertEquals("999", properties["water"])
        assertEquals("999", properties["milk"])
        assertEquals("999", properties["coffeeBeans"])
        assertEquals("9", properties["disposableCups"])
        assertEquals("9", properties["disposableCups"])
    }

    @Test
    fun loadResources_fileNotFound() {
        Assertions.assertThrows(Exception::class.java)
        { ResourceHandler.getProperties("non-existing-file.properties") }
    }
}