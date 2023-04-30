package com.example.kafka.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class CheckoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkoutId;

    private Long memberId;

    private Long productId;

    private Long amount;

    private String shippingAddress;

    @CreationTimestamp
    private Date createdAt;
}
