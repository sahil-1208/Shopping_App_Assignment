package com.learning.dto;

import com.learning.entity.OrderEntity;
import com.learning.entity.UserEntity;
import com.learning.request.OrderRequest;
import com.learning.response.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderDto {

    public OrderEntity requestToEntity(OrderRequest orderRequest) {
        UserEntity user = userIdFromRequest(orderRequest);
        return OrderEntity.builder().userId(user).
                quantity(orderRequest.getQuantity()).
                couponCode(orderRequest.getCouponCode())
                .build();
    }

    public OrderResponse entityToResponse(OrderEntity orderEntity){
        return OrderResponse.builder().orderId(orderEntity.getOrderId())
                .userId(orderEntity.getUserId().getUserId())
                .amount(orderEntity.getAmount())
                .quantity(orderEntity.getQuantity())
                .couponCode(orderEntity.getCouponCode())
                .date(orderEntity.getDate())
                .build();
    }

    public UserEntity userIdFromRequest(OrderRequest orderRequest) {
        UserEntity user = new UserEntity();
        user.setUserId(orderRequest.getUserId());
        return user;
    }
}
