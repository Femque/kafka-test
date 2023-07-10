package com.example.kafkatest.requests

import org.springframework.data.mongodb.core.mapping.Document

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
