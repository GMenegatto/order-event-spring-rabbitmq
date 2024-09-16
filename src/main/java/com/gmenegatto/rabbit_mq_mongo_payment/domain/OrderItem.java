package com.gmenegatto.rabbit_mq_mongo_payment.domain;

import com.gmenegatto.rabbit_mq_mongo_payment.listener.domain.OrderCreatedEvent;
import com.gmenegatto.rabbit_mq_mongo_payment.listener.domain.OrderItemEvent;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderItem {

    private String product;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal quantity;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    public OrderItem() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static OrderItem from(final OrderItemEvent item) {
        return Optional.of(new OrderItem()).map(orderItem -> {
            orderItem.setPrice(item.preco());
            orderItem.setProduct(item.produto());
            orderItem.setQuantity(item.quantidade());
            return orderItem;
        }).orElseThrow();
    }
}
