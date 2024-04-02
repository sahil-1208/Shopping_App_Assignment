package com.learning.service;

import com.learning.entity.TransactionEntity;
import com.learning.request.TransactionRequest;
import com.learning.response.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    TransactionResponse orderPayment(TransactionRequest transactionRequest);
}
