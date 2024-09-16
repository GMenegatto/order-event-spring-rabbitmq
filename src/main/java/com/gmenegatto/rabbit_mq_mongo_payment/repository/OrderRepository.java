package com.gmenegatto.rabbit_mq_mongo_payment.repository;

import com.gmenegatto.rabbit_mq_mongo_payment.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
