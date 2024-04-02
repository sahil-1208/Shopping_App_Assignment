package com.learning.dto;

import com.learning.entity.OrderEntity;
import com.learning.entity.TransactionEntity;
import com.learning.entity.UserEntity;
import com.learning.enums.Status;
import com.learning.request.TransactionRequest;
import com.learning.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionDTO {

    public TransactionEntity requestToEntity(TransactionRequest transactionRequest) {
        UserEntity user = userIdFromRequest(transactionRequest);
        OrderEntity order = orderIdFromRequest(transactionRequest);
        return TransactionEntity.builder().userId(user)
                .amount(transactionRequest.getAmount())
                .orderId(order).build();
    }

    public TransactionResponse entityToResponse(TransactionEntity transactionEntity) {
        return TransactionResponse.builder().orderId(transactionEntity.getOrderId().getOrderId())
                .userId(transactionEntity.getUserId().getUserId())
                .transactionId(transactionEntity.getTransactionId())
                .status(Status.SUCCESSFUL).build();
    }

    public TransactionResponse createErrorResponse(Long userId, Long orderId, String transactionId, Status status, String description) {
       return TransactionResponse.builder().userId(userId).orderId(orderId).transactionId(transactionId).status(Status.FAILED)
               .description(description).build();
    }

    public UserEntity userIdFromRequest(TransactionRequest transactionRequest) {
        UserEntity user = new UserEntity();
        user.setUserId(transactionRequest.getUserId());
        return user;
    }

    public OrderEntity orderIdFromRequest(TransactionRequest transactionRequest) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(transactionRequest.getOrderId());
        return orderEntity;
    }
}
