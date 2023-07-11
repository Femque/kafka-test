package com.example.kafkatest.requests

import org.springframework.data.mongodb.core.mapping.Document

// TODO: Auto increment Unique id?
@Document(collection = "products")
data class Product(var id: String, var name: String, var price: Double) {
    override fun toString(): String {
        return "{" +
                "\"id\":\"$id\"" +
                ", \"name\":\"$name\"" +
                ", \"price\":\"$price\"" +
                "}"
    }
}
