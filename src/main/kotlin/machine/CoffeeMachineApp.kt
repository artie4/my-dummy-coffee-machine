package machine

import util.ResourceHandler

fun main() {
    val resources = ResourceHandler("src/main/resources/coffee-machine-init-state.properties")
    val coffeeMachine = CoffeeMachine(
        resources.getProperty("water").toInt(),
        resources.getProperty("milk").toInt(),
        resources.getProperty("coffeeBeans").toInt(),
        resources.getProperty("disposableCups").toInt(),
        resources.getProperty("money").toInt()
    )
    coffeeMachine.start()
}