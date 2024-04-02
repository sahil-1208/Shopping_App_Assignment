package com.learning.service.impl;

import com.learning.dto.TransactionDTO;
import com.learning.entity.OrderEntity;
import com.learning.entity.TransactionEntity;
import com.learning.enums.Status;
import com.learning.repository.OrderRepository;
import com.learning.repository.TransactionRepository;
import com.learning.request.TransactionRequest;
import com.learning.response.ErrorResponse;
import com.learning.response.TransactionResponse;
import com.learning.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionDTO transactionDTO;

    @Override
    public TransactionResponse orderPayment(TransactionRequest transactionRequest) {
        TransactionEntity transactionEntity = transactionRepository.findByUserIdAndOrderId(transactionRequest.getUserId(), transactionRequest.getOrderId());
        if (transactionEntity == null) {
            transactionEntity = transactionDTO.requestToEntity(transactionRequest);
        }
        if (Status.SUCCESSFUL.equals(transactionEntity.getStatus())) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Order is already paid for" + transactionRequest.getOrderId());
            return transactionDTO.createErrorResponse(transactionRequest.getUserId(), transactionRequest.getOrderId(), generateTransactionId(), Status.FAILED, errorResponse.getDescription());
        }

        String transactionId = generateTransactionId();
        transactionEntity.setTransactionId(transactionId);

        Optional<OrderEntity> optionalOrder = orderRepository.findById(transactionRequest.getOrderId());
        if (optionalOrder.isEmpty()) {
            ErrorResponse orderNotFoundResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Order not found with ID: " + transactionRequest.getOrderId());
            return transactionDTO.createErrorResponse(transactionRequest.getUserId(), transactionRequest.getOrderId(), transactionId, Status.FAILED, orderNotFoundResponse.getDescription());
        }
        double totalAmount = optionalOrder.get().getAmount();
        if (transactionRequest.getAmount() < totalAmount) {
            ErrorResponse amountError = new ErrorResponse(HttpStatus.BAD_REQUEST, "Payment Amount Invalid");
            return transactionDTO.createErrorResponse(transactionRequest.getUserId(), transactionRequest.getOrderId(), transactionId, Status.FAILED, amountError.getDescription());
        } else if (transactionRequest.getAmount() > totalAmount) {
            ErrorResponse amountError = new ErrorResponse(HttpStatus.BAD_REQUEST, "Payment Amount Invalid");
            return transactionDTO.createErrorResponse(transactionRequest.getUserId(), transactionRequest.getOrderId(), transactionId, Status.FAILED, amountError.getDescription());
        }

        transactionEntity.setStatus(Status.SUCCESSFUL);
        transactionRepository.save(transactionEntity);
        return transactionDTO.entityToResponse(transactionEntity);
    }

    private String generateTransactionId() {
        return "tran" + (int) (Math.random() * 1000000);
    }

}
