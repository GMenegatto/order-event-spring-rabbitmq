package com.gmenegatto.rabbit_mq_mongo_payment.listener.domain;

import java.math.BigDecimal;

public record OrderItemEvent(String produto,
                             BigDecimal quantidade,
                             BigDecimal preco) {
}
