package com.gmenegatto.rabbit_mq_mongo_payment.OrderService;

import com.gmenegatto.rabbit_mq_mongo_payment.domain.Order;
import com.gmenegatto.rabbit_mq_mongo_payment.listener.domain.OrderCreatedEvent;
import com.gmenegatto.rabbit_mq_mongo_payment.repository.OrderRepository;
import com.gmenegatto.rabbit_mq_mongo_payment.resource.dto.OrderResponse;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<OrderResponse> findAllByCustomerId(final Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::from);
    }


    public BigDecimal findAllInOrdersByCustomerId(final Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").in(customerId)),
                group().sum("total").as("total")

        );

        var response = mongoTemplate.aggregate(aggregations, "orders", Document.class);
        return new BigDecimal(response.getUniqueMappedResult().getOrDefault("total", BigDecimal.ZERO).toString()) ;
    }

    public void createFromEvent(OrderCreatedEvent event) {

        orderRepository.save(Order.from(event));
    }
}
