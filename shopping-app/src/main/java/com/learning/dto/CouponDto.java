package com.learning.dto;

import com.learning.entity.CouponEntity;
import com.learning.response.CouponResponse;
import org.springframework.stereotype.Component;

@Component
public class CouponDto {

    public CouponResponse entityToResponse(CouponEntity couponEntity) {
        return CouponResponse.builder().couponCode(couponEntity.getCouponCode())
                .discount(couponEntity.getDiscount()).build();
    }

}
