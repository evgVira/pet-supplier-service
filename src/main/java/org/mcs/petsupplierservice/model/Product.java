package org.mcs.petsupplierservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "created_dt", nullable = false)
    private Instant createdDt;

    @Column(name = "updated_dt")
    private Instant updatedDt;

    @Column(name = "is_in_stock", nullable = false)
    private Boolean isInStock;
}
