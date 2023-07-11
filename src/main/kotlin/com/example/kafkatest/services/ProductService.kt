package com.example.kafkatest.services

import com.example.kafkatest.kafkaconfig.producers.ProductProducer
import com.example.kafkatest.repository.ProductRepository
import com.example.kafkatest.requests.Product
import com.example.kafkatest.requests.ProductMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProductService(private val productProducer: ProductProducer, private val productRepository: ProductRepository) {

    private val log = LoggerFactory.getLogger(ProductService::class.java)

    fun sendMessage(message: ProductMessage) {
        log.info("[ProductService] send product to topic")
        productProducer.send(message)
    }

    fun saveProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun deleteProduct(id: String) {
        productRepository.deleteById(id)
    }

    fun getProductById(id: String): Product {
        return productRepository.findById(id).orElse(null)
    }
}
