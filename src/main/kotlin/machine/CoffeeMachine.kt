package machine

import java.util.*

class CoffeeMachine(
    water: Int,
    milk: Int,
    coffeeBeans: Int,
    disposableCups: Int,
    money: Int
) {

    private val scanner = Scanner(System.`in`)
    private var resourcesState = ResourcesState(water, milk, coffeeBeans, disposableCups, money)
    private var machineEnabled: Boolean = true

    class ResourcesState(
        water: Int, milk: Int, coffeeBeans: Int,
        disposableCups: Int, money: Int
    ) {
        var water = water
            set(value) {
                field = value; if (field < 0) {
                    throw NegativeAmountOfResourceException("water")
                }
            }
        var milk = milk
            set(value) {
                field = value; if (field < 0) {
                    throw NegativeAmountOfResourceException("milk")
                }
            }
        var coffeeBeans = coffeeBeans
            set(value) {
                field = value; if (field < 0) {
                    throw NegativeAmountOfResourceException("coffeeBeans")
                }
            }
        var disposableCups = disposableCups
            set(value) {
                field = value; if (field < 0) {
                    throw NegativeAmountOfResourceException("disposableCups")
                }
            }
        var money = money
            set(value) {
                field = value; if (field < 0) {
                    throw NegativeAmountOfResourceException("money")
                }
            }
    }

    enum class Actions { buy, fill, take, remaining, exit }

    fun start() {
        while (machineEnabled) {
            chooseAction()
        }
    }

    private fun chooseAction() {
        println("Write action (${Actions.values().joinToString(", ")}):")
        try {
            when (Actions.valueOf(scanner.nextLine().toLowerCase())) {
                Actions.buy -> buy()
                Actions.fill -> fill()
                Actions.take -> take()
                Actions.remaining -> remaining()
                Actions.exit -> exit()
            }
        } catch (ex: IllegalArgumentException) {
            println(ex.message)
            return
        }
        println()
    }

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: > 3")
        val coffeeType: CoffeeType = when (scanner.nextLine()) {
            "1" -> CoffeeType.ESPRESSO
            "2" -> CoffeeType.LATTE
            "3" -> CoffeeType.CAPPUCCINO
            else -> return
        }
        val snapshotState = ResourcesState(
            resourcesState.water, resourcesState.milk, resourcesState.coffeeBeans,
            resourcesState.disposableCups, resourcesState.money
        )
        try {
            resourcesState.water -= coffeeType.water
            resourcesState.milk -= coffeeType.milk
            resourcesState.coffeeBeans -= coffeeType.beans
            resourcesState.disposableCups--
            resourcesState.money += coffeeType.cost
            println("I have enough resources, making you a coffee!")
        } catch (ex: NegativeAmountOfResourceException) {
            resourcesState = snapshotState
            println("Sorry, not enough ${ex.message}")
        }
    }

    private fun fill() {
        val fillScanner = Scanner(System.`in`)
        println("Write how many ml of water do you want to add:")
        resourcesState.water += fillScanner.nextInt()
        println("Write how many ml of milk do you want to add:")
        resourcesState.milk += fillScanner.nextInt()
        println("Write how many grams of coffee beans do you want to add:")
        resourcesState.coffeeBeans += fillScanner.nextInt()
        println("Write how many disposable cups of coffee do you want to add:")
        resourcesState.disposableCups += fillScanner.nextInt()
    }

    private fun take() {
        println("I gave you $${resourcesState.money}")
        resourcesState.money = 0
    }

    private fun remaining() {
        println(
            """
            The coffee machine has:
            ${resourcesState.water} of water
            ${resourcesState.milk} of milk
            ${resourcesState.coffeeBeans} of coffee beans
            ${resourcesState.disposableCups} of disposable cups
            $${resourcesState.money} of money
        """.trimIndent()
        )
    }

    private fun exit() {
        machineEnabled = false
    }

    enum class CoffeeType(val water: Int, val milk: Int, val beans: Int, val cost: Int) {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6)
    }

    class NegativeAmountOfResourceException(message: String) : RuntimeException(message)
}