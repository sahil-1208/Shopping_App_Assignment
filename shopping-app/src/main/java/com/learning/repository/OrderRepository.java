package com.learning.repository;

import com.learning.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT * FROM order_entity o WHERE o.user_id = :userId AND o.coupon_code = :couponCode", nativeQuery = true)
    Optional<OrderEntity> findByUserIdAndCouponCode(@Param("userId") Long userId, @Param("couponCode") String couponCode);

    @Query(value = "SELECT SUM(quantity) FROM order_entity", nativeQuery = true)
    Integer getTotalOrderedQuantity();

    @Query(value = "SELECT * FROM order_entity WHERE user_id = :userId", nativeQuery = true)
    List<OrderEntity> findByUserId(Long userId);

    @Query(value = "SELECT * FROM order_entity WHERE user_id = ?1 AND order_id = ?2", nativeQuery = true)
    Optional<OrderEntity> findByUserIdAndOrderId(Long userId, Long orderId);
}
