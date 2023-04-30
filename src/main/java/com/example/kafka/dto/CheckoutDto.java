package com.example.kafka.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
@NoArgsConstructor
public class CheckoutDto {

    private Long checkoutId;
    private Long memberId;
    private Long productId;
    private Long amount;
    private String shippingAddress;
    private Date createdAt;

    public CheckoutDto(Long checkoutId, Long memberId, Long productId, Long amount, String shippingAddress, Date createdAt) {
        this.checkoutId = checkoutId;
        this.memberId = memberId;
        this.productId = productId;
        this.amount = amount;
        this.shippingAddress = shippingAddress;
        this.createdAt = createdAt;
    }
}
