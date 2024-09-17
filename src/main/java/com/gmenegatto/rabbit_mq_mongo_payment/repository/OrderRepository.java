package com.gmenegatto.rabbit_mq_mongo_payment.repository;

import com.gmenegatto.rabbit_mq_mongo_payment.domain.Order;
import com.gmenegatto.rabbit_mq_mongo_payment.resource.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
    Page<Order> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
