package org.mcs.petsupplierservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductKafkaRequestDto {
    private String uniqProductId;
    private Integer quantity;
}
