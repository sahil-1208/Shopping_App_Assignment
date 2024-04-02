package com.learning.service;

import com.learning.entity.CouponEntity;
import com.learning.response.CouponResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {

    List<CouponResponse> getAllCoupons(CouponEntity couponEntity);

    boolean isCouponValid(Long userId, String couponCode);

    double getCouponDiscount(String couponCode);
}
