package com.example.kafkatest.requests

import java.io.Serializable

data class ProductMessage(var action: String? = null) : Serializable {
    constructor(product: Product, action: String) : this(action) {
        this.id = product.id
        this.name = product.name
        this.price = product.price
    }

    var id: String? = null
    var name: String? = null
    var price: Double? = null
}
