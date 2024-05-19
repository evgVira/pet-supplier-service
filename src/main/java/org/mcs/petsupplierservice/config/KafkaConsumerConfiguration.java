package org.mcs.petsupplierservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.mcs.petsupplierservice.dto.ProductKafkaRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    @Value("${broker.bootstrap-server}")
    private String bootstrapServer;

    @Value("${application.kafka.topic}")
    private String topicName;

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(topicName)
                .replicas(1)
                .partitions(1)
                .build();
    }

    @Bean
    public Map<String, Object> consumerConfigs(){
        HashMap<String, Object> props = new HashMap<>();
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ProductKafkaRequestDto.class);
        return props;
    }

    @Bean("kafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, ProductKafkaRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ProductKafkaRequestDto> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
}
