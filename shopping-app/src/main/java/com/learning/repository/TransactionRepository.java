package com.learning.repository;

import com.learning.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "SELECT * FROM transaction WHERE user_id = :userId AND order_id = :orderId", nativeQuery = true)
    TransactionEntity findByUserIdAndOrderId(@Param("userId") Long userId, @Param("orderId") Long orderId);

}
