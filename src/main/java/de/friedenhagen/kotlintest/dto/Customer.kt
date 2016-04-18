package de.friedenhagen.kotlintest.dto

data class Customer(
        var given: String = "",
        var family: String = "",
        var address: Address = Address()
)