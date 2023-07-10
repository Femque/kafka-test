package com.example.kafkatest.producers

import com.example.kafkatest.requests.ProductMessage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback
import java.io.Serializable

@Component
class ProductProducer @Autowired constructor(private val kafkaTemplate: KafkaTemplate<String, Serializable>){

    private val productTopic: String = "product"
    private val log = LoggerFactory.getLogger(ProductProducer::class.java)

    fun send(message: ProductMessage) {
        val future = kafkaTemplate.send(productTopic, message)

        future.addCallback(object: ListenableFutureCallback<SendResult<String, Serializable>> {
            override fun onFailure(ex: Throwable) {
                log.error("Unable to send message = {} due to: {}", message.toString(), ex.message)
            }

            override fun onSuccess(result: SendResult<String, Serializable>?) {
                if (result != null) {
                    log.info("Message send successfully with offset = {}", result.recordMetadata.offset())
                }
            }
        })
    }
}
