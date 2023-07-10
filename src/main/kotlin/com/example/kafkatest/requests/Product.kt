package com.example.kafkatest.requests

data class Product(var id: String, var name: String, var price: Double) {
    override fun toString(): String {
        return "{" +
                "\"id\":\"$id\"" +
                ", \"name\":\"$name\"" +
                ", \"price\":\"$price\"" +
                "}"
    }
}
