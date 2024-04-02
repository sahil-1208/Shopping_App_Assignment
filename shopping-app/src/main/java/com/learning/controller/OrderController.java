package com.learning.controller;

import com.learning.entity.OrderEntity;
import com.learning.exception.InvalidQuantityException;
import com.learning.exception.OrderNotFoundException;
import com.learning.request.OrderRequest;
import com.learning.response.ErrorResponse;
import com.learning.response.OrderResponse;
import com.learning.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            OrderResponse createdOrder = orderService.createOrder(orderRequest);
            return ResponseEntity.ok().body(createdOrder);
        } catch (InvalidQuantityException invalidQuantityException) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Invalid Quantity");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DataIntegrityViolationException dataIntegrityException) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Coupon");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderResponse> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<?> getOrderByIdForUser(@PathVariable Long userId, @PathVariable Long orderId) {
        try {
            OrderResponse orderResponse = orderService.getOrderByIdForUser(userId, orderId);
            return ResponseEntity.ok(orderResponse);
        } catch (OrderNotFoundException orderNotFoundException) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Order not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
