package com.gmenegatto.rabbit_mq_mongo_payment.domain;

import com.gmenegatto.rabbit_mq_mongo_payment.listener.domain.OrderCreatedEvent;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Document(collection = "orders")
public class Order {

    @MongoId
    private Long id;

    @Indexed(name = "idx_orders__customer_id")
    private Long customerId;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;

    private List<OrderItem> items;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }


    public static Order from(final OrderCreatedEvent event) {
        return Optional.of(new Order()).map(order -> {
            order.setId(event.codigoPedido());
            order.setCustomerId(event.codigoCliente());
            order.setItems(event.itens().stream().map(OrderItem::from).collect(Collectors.toList()));

            Optional.ofNullable(order.getItems()).flatMap(items -> items.stream().map(item -> item.getPrice().multiply(item.getQuantity()))
                    .reduce(BigDecimal::add)).ifPresent(order::setTotal);

            return order;
        }).orElseThrow();
    }
}
