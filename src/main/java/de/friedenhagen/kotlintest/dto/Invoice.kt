package de.friedenhagen.kotlintest.dto

data class Invoice(
        var invoice: Int = 0,
        var date: String = "",
        var billTo: Customer = Customer(),
        var shipTo: Customer = Customer(),
        var product: Array<Product> = emptyArray(),
        var tax: Double = 0.0,
        var total: Double = 0.0,
        var comments: String = ""
)