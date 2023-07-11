package com.example.kafkatest.kafkaconfig

import com.example.kafkatest.requests.ProductMessage
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    private val log = LoggerFactory.getLogger(KafkaConsumerConfig::class.java)

    fun consumerFactory(): ConsumerFactory<String, ProductMessage> {
        val config = HashMap<String, Any>()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        config[ConsumerConfig.GROUP_ID_CONFIG] = "consuming"

        val typeMapper = DefaultJackson2JavaTypeMapper()
        val classMap = HashMap<String, Class<*>>()
        typeMapper.idClassMapping = classMap
        typeMapper.addTrustedPackages("*")

        val jsonDeserializer = JsonDeserializer(ProductMessage::class.java)
        jsonDeserializer.typeMapper = typeMapper
        jsonDeserializer.setUseTypeMapperForKey(true)

        return DefaultKafkaConsumerFactory(config, StringDeserializer(), jsonDeserializer)
    }

    fun consumerConfigs(): Map<String, Any> {
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.GROUP_ID_CONFIG] = "consuming"

        return props
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ProductMessage> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, ProductMessage> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        log.info("Configure concurrent consumer Kafka")

        return factory
    }
}

