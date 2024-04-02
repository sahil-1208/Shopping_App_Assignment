package com.learning.controller;

import com.learning.entity.CouponEntity;
import com.learning.response.CouponResponse;
import com.learning.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/fetchCoupons")
    public ResponseEntity<List<CouponResponse>> getAllCoupons(CouponEntity couponEntity){
            return ResponseEntity.ok(couponService.getAllCoupons(couponEntity));
    }
}
