package com.learning.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {

    private Long userId, orderId;
    private Double amount;
}
