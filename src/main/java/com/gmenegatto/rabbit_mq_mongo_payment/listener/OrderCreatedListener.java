package com.gmenegatto.rabbit_mq_mongo_payment.listener;

import com.gmenegatto.rabbit_mq_mongo_payment.OrderService.OrderService;
import com.gmenegatto.rabbit_mq_mongo_payment.listener.domain.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.gmenegatto.rabbit_mq_mongo_payment.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
       logger.info("Message consumed: {}", message.getPayload());

        orderService.createFromEvent(message.getPayload());
    }
}
