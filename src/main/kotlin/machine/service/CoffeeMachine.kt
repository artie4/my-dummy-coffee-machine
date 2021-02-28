package machine.service

import java.util.Scanner
import machine.model.CoffeeMachineState
import machine.model.CoffeeMachineActions
import machine.model.CoffeeTypes
import machine.util.ResourceHandler


class CoffeeMachine {

    private val properties = ResourceHandler.getProperties("coffee-machine-init-state.properties")
    private var coffeeMachineState: CoffeeMachineState
    private val scanner = Scanner(System.`in`)
    private var machineEnabled: Boolean = false

    init {
        coffeeMachineState = CoffeeMachineState(
            properties.getProperty("water").toInt(),
            properties.getProperty("milk").toInt(),
            properties.getProperty("coffeeBeans").toInt(),
            properties.getProperty("disposableCups").toInt(),
            properties.getProperty("money").toInt()
        )
    }

    fun start() {
        machineEnabled = true
        while (machineEnabled) {
            chooseAction()
        }
    }

    private fun chooseAction() {
        println("Write action (${CoffeeMachineActions.values().joinToString(", ")}):")
        try {
            when (CoffeeMachineActions.valueOf(scanner.nextLine().toUpperCase())) {
                CoffeeMachineActions.BUY -> buy()
                CoffeeMachineActions.FILL -> fill()
                CoffeeMachineActions.TAKE -> take()
                CoffeeMachineActions.REMAIN -> remaining()
                CoffeeMachineActions.EXIT -> exit()
            }
        } catch (ex: IllegalArgumentException) {
            println("Seems action is not exist. Please choose another one")
        }
        println()
    }

    private fun buy() {
        println("What do you want to buy? ${CoffeeTypes.values().joinToString(", ")}):")
        val coffeeType: CoffeeTypes = when (scanner.nextLine()) {
            "1" -> CoffeeTypes.ESPRESSO
            "2" -> CoffeeTypes.LATTE
            "3" -> CoffeeTypes.CAPPUCCINO
            else -> return
        }
        try {
            coffeeMachineState = coffeeMachineState.copy(
                water = coffeeMachineState.water - coffeeType.water,
                milk = coffeeMachineState.milk - coffeeType.milk,
                coffeeBeans = coffeeMachineState.coffeeBeans - coffeeType.beans,
                disposableCups = coffeeMachineState.disposableCups - 1,
                money = coffeeMachineState.money + coffeeType.cost
            )
            println("I have enough resources, making you a coffee!")
        } catch (ex: Exception) {
            println("Sorry, ${ex.message}")
        }
    }

    private fun fill() {
        val fillScanner = Scanner(System.`in`)
        fun createMessage(text: String) = "Write how many $text do you want to add:"
        println(createMessage("ml of water"))
        val water = fillScanner.nextInt()
        println(createMessage("ml of milk"))
        val milk = fillScanner.nextInt()
        println(createMessage("grams of coffee beans"))
        val beans = fillScanner.nextInt()
        println(createMessage("disposable cups of coffee"))
        val cups = fillScanner.nextInt()
        coffeeMachineState = coffeeMachineState.copy(
            water = coffeeMachineState.water + water,
            milk = coffeeMachineState.milk + milk,
            coffeeBeans = coffeeMachineState.coffeeBeans + beans,
            disposableCups = coffeeMachineState.disposableCups + cups
        )
    }

    private fun take() {
        println("I gave you $${coffeeMachineState.money}")
        coffeeMachineState = coffeeMachineState.copy(money = 0)
    }

    private fun remaining() {
        println(
            """
            The coffee machine has:
            ${coffeeMachineState.water} of water
            ${coffeeMachineState.milk} of milk
            ${coffeeMachineState.coffeeBeans} of coffee beans
            ${coffeeMachineState.disposableCups} of disposable cups
            $${coffeeMachineState.money} of money
        """.trimIndent()
        )
    }

    private fun exit() {
        machineEnabled = false
    }

}