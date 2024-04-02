package com.learning.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderResponse {
    private Long userId, orderId;
    private Integer quantity;
    private Double amount;
    private String couponCode;
    private LocalDate date;

}
