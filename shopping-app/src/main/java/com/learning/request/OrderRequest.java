package com.learning.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderRequest {

    private Long userId;
    private Integer quantity;
    private String couponCode;
}
