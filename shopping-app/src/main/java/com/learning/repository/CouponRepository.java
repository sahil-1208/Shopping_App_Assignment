package com.learning.repository;

import com.learning.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<CouponEntity,Long> {

    Optional<CouponEntity> findByCouponCode(String couponCode);
}
