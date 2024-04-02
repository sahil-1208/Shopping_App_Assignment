package com.learning.entity;

import com.learning.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@Builder
@Entity
public class TransactionEntity {

    @Id
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String description;

}
