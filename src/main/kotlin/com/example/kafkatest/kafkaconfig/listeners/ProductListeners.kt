package com.example.kafkatest.kafkaconfig.listeners

import com.example.kafkatest.requests.ProductMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ProductListener {

    private val log = LoggerFactory.getLogger(ProductListener::class.java)

    @KafkaListener(topics = ["product"], containerFactory = "kafkaListenerContainerFactory")
    fun newProductListener(product: ProductMessage) {
        log.info("Get request from product topic $product")
    }
}
