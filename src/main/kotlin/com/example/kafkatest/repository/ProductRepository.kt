package com.example.kafkatest.repository

import com.example.kafkatest.requests.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String> {
    // TODO
}
