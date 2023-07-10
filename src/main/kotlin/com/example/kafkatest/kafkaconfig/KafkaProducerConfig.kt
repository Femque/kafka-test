package com.example.kafkatest.kafkaconfig

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import java.io.Serializable

@Configuration
class KafkaProducerConfig {

    @Value("\${kafka.bootstrapAddress}")
    private lateinit var bootstrapAddress: String

    @Bean
    fun producerFactory(): ProducerFactory<String, Serializable> {
        val configProps = HashMap<String, Any>()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        configProps[ProducerConfig.MAX_REQUEST_SIZE_CONFIG] = "20971520"
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun jsonKafkaTemplate(jsonProducerFactory: ProducerFactory<String, Serializable>): KafkaTemplate<String, Serializable> {
        return KafkaTemplate(jsonProducerFactory)
    }
}
