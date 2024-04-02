package com.learning.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponResponse {
    private String couponCode;
    private Double discount;
}
