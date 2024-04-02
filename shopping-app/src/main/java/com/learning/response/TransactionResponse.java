package com.learning.response;


import com.learning.enums.Status;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransactionResponse {

    private Long userId, orderId;
    private Status status;
    private String transactionId, description;
}
