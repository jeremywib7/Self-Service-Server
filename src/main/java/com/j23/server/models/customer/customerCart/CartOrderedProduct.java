package com.j23.server.models.customer.customerCart;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.j23.server.models.customer.CustomerProfile;
import com.j23.server.models.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Entity
@Data
@Setter
@Getter
@ToString
public class CartOrderedProduct {

    @Id
    private String id;

    @JsonIncludeProperties(value = {"id", "name", "unitPrice", "discount", "discountedPrice", "images", "active", "deleted"})
    @OneToOne(targetEntity = Product.class)
    private Product product;

    @JsonIgnore
    @OneToOne
    private CustomerProfile customerProfile;

    @Min(0)
    @Max(100000)
    @Column(name = "quantity")
    private Integer quantity;

    @CreationTimestamp
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "date_created")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "date_updated")
    private LocalDateTime updatedOn;
}
