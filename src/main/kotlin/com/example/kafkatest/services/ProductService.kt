package com.example.kafkatest.services

import com.example.kafkatest.producers.ProductProducer
import com.example.kafkatest.requests.ProductMessage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService @Autowired constructor(private val productProducer: ProductProducer) {

    private val log = LoggerFactory.getLogger(ProductService::class.java)

    fun sendMessage(message: ProductMessage) {
        log.info("[ProductService] send product to topic")
        productProducer.send(message)
    }
}