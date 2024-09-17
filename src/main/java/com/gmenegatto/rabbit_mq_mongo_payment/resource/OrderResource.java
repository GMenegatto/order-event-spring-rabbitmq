package com.gmenegatto.rabbit_mq_mongo_payment.resource;

import com.gmenegatto.rabbit_mq_mongo_payment.OrderService.OrderService;
import com.gmenegatto.rabbit_mq_mongo_payment.resource.dto.ApiResponse;
import com.gmenegatto.rabbit_mq_mongo_payment.resource.dto.OrderResponse;
import com.gmenegatto.rabbit_mq_mongo_payment.resource.dto.PaginationResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                                 @PathVariable Long customerId
    ) {
        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, size));
        var totalOnOrders = this.orderService.findAllInOrdersByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(Map.of("totalOnOrders", totalOnOrders), pageResponse.getContent(), PaginationResponse.from(pageResponse)));
    }
}
