package machine.model

enum class CoffeeTypes(val water: Int = 0, val milk: Int = 0, val beans: Int = 0, val cost: Int = 0) {
    ESPRESSO(water = 250, beans = 16, cost = 4),
    LATTE(water = 350, milk = 75, beans = 20, cost = 7),
    CAPPUCCINO(water = 200, milk = 100, beans = 12, cost = 6)
}