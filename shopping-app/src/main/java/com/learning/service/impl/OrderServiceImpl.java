package com.learning.service.impl;

import com.learning.config.ProductConfig;
import com.learning.dto.OrderDto;
import com.learning.entity.OrderEntity;
import com.learning.entity.TransactionEntity;
import com.learning.exception.InvalidQuantityException;
import com.learning.exception.OrderNotFoundException;
import com.learning.repository.OrderRepository;
import com.learning.request.OrderRequest;
import com.learning.response.OrderResponse;
import com.learning.service.CouponService;
import com.learning.service.OrderService;
import com.learning.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final ProductConfig productConfig;

    private final CouponService couponService;

    private final OrderRepository orderRepository;

    private final OrderDto orderDto;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) throws InvalidQuantityException   {

        if (orderRequest.getQuantity() <= 0 || orderRequest.getQuantity() > productConfig.getAvailable()) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        OrderEntity orderEntity = orderDto.requestToEntity(orderRequest);
        if (couponService.isCouponValid(orderRequest.getUserId(), orderRequest.getCouponCode())) {
            double finalAmount = calculateFinalAmount(orderEntity, orderRequest.getCouponCode());
            orderEntity.setAmount(finalAmount);
        } else {
            orderEntity.setAmount(orderEntity.getQuantity() * productConfig.getPrice());
        }
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        return orderDto.entityToResponse(savedOrder);
    }

    private double calculateFinalAmount(OrderEntity orderEntity, String couponCode) {
        double totalAmount = orderEntity.getQuantity() * productConfig.getPrice();
        double discount = couponService.getCouponDiscount(couponCode);
        double finalAmount = totalAmount - (totalAmount * discount / 100.0);
        return finalAmount;
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<OrderEntity> orderEntities = orderRepository.findByUserId(userId);
        return orderEntities.stream()
                .map(orderDto::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderByIdForUser(Long userId, Long orderId) throws OrderNotFoundException {
        OrderEntity orderEntity = orderRepository.findByUserIdAndOrderId(userId, orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not Found"));
        return orderDto.entityToResponse(orderEntity);
    }
}
