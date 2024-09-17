package com.gmenegatto.rabbit_mq_mongo_payment.OrderService;

import com.gmenegatto.rabbit_mq_mongo_payment.domain.Order;
import com.gmenegatto.rabbit_mq_mongo_payment.listener.domain.OrderCreatedEvent;
import com.gmenegatto.rabbit_mq_mongo_payment.repository.OrderRepository;
import com.gmenegatto.rabbit_mq_mongo_payment.resource.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<OrderResponse> findAllByCustomerId(final Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::from);
    }

    public void createFromEvent(OrderCreatedEvent event) {

        orderRepository.save(Order.from(event));
    }
}
