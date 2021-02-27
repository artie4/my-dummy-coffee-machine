package machine.model

data class CoffeeMachineState (
    val water: Int,
    val milk: Int,
    val coffeeBeans: Int,
    val disposableCups: Int,
    val money: Int
) {
    init {
        fun nonNegative(value: Int): Boolean = value >= 0
        require(nonNegative(water)) { "Water is out" }
        require(nonNegative(milk)) { "Milk is out" }
        require(nonNegative(coffeeBeans)) { "Beans is out" }
        require(nonNegative(disposableCups)) { "Cups is out" }
        require(nonNegative(money)) { "Money is out" }
    }
}