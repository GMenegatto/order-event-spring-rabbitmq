package com.gmenegatto.rabbit_mq_mongo_payment.resource.dto;

import com.gmenegatto.rabbit_mq_mongo_payment.domain.Order;

import java.math.BigDecimal;

public record OrderResponse(Long orderId, Long customerId, BigDecimal total) {

    public static OrderResponse from(final Order order) {
        return new OrderResponse(order.getId(), order.getCustomerId(), order.getTotal());
    }
}
