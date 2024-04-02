package com.learning.service.impl;

import com.learning.dto.CouponDto;
import com.learning.entity.CouponEntity;
import com.learning.entity.OrderEntity;
import com.learning.repository.CouponRepository;
import com.learning.repository.OrderRepository;
import com.learning.response.CouponResponse;
import com.learning.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponDto couponDto;

    private final OrderRepository orderRepository;

    private final CouponRepository couponRepository;

    @Override
    public List<CouponResponse> getAllCoupons(CouponEntity couponEntity) {
        List<CouponEntity> availableCoupons = couponRepository.findAll();
        return availableCoupons.stream().map(couponDto::entityToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public boolean isCouponValid(Long userId, String couponCode) {
        Optional<CouponEntity> optionalCoupon = couponRepository.findByCouponCode(couponCode);

        if (optionalCoupon.isPresent()) {
            Optional<OrderEntity> order = orderRepository.findByUserIdAndCouponCode(userId, couponCode);
            return order.isEmpty();
        }
        return false;
    }

    @Override
    public double getCouponDiscount(String couponCode) {
        return switch (couponCode) {
            case "OFF10" -> 10.0;
            case "OFF5" -> 5.0;
            default -> 0.0;
        };
    }
}
