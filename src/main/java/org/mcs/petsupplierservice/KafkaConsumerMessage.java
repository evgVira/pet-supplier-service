package org.mcs.petsupplierservice;

import lombok.extern.slf4j.Slf4j;
import org.mcs.petsupplierservice.dto.ProductKafkaRequestDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerMessage {

    @KafkaListener(topics = "supplier-request-event", groupId = "supplier-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMessage(@Payload ProductKafkaRequestDto productKafkaResponseDto){
        log.info("message was consumed: %s, %s".formatted(productKafkaResponseDto.getQuantity(),
                productKafkaResponseDto.getUniqProductId()));
        System.out.println("message was consumed");
    }
}
