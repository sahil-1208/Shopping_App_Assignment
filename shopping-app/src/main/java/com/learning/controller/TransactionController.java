package com.learning.controller;

import com.learning.request.TransactionRequest;
import com.learning.response.TransactionResponse;
import com.learning.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/pay")
    public ResponseEntity<TransactionResponse> orderPayment(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse response = transactionService.orderPayment(transactionRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
