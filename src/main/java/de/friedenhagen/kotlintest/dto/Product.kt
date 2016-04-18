package de.friedenhagen.kotlintest.dto

data class Product(
        var sku: String = "",
        var quantity: Int = 0,
        var description: String = "",
        var price: Double = 0.0
)