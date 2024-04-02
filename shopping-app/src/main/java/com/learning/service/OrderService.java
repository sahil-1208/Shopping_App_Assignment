package com.learning.service;

import com.learning.exception.InvalidQuantityException;
import com.learning.exception.OrderNotFoundException;
import com.learning.request.OrderRequest;
import com.learning.response.OrderResponse;

import java.util.List;

public interface OrderService {

    public OrderResponse createOrder(OrderRequest orderRequest) throws InvalidQuantityException;

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse getOrderByIdForUser(Long userId, Long orderId) throws OrderNotFoundException;
}
