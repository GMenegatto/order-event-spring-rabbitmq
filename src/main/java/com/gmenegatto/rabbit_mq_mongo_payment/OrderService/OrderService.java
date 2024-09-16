package com.gmenegatto.rabbit_mq_mongo_payment.OrderService;

import com.gmenegatto.rabbit_mq_mongo_payment.domain.Order;
import com.gmenegatto.rabbit_mq_mongo_payment.listener.domain.OrderCreatedEvent;
import com.gmenegatto.rabbit_mq_mongo_payment.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createFromEvent(OrderCreatedEvent event) {

        orderRepository.save(Order.from(event));
    }
}
