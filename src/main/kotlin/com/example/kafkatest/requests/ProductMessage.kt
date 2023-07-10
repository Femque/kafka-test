package com.example.kafkatest.requests

import java.io.Serializable

data class ProductMessage(var action: String? = null) : Serializable {
    constructor(p: Product, action: String) : this(action) {
        this.id = p.id
        this.name = p.name
        this.price = p.price
    }

    var id: String? = null
    var name: String? = null
    var price: Double? = null
}
