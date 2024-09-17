package com.gmenegatto.rabbit_mq_mongo_payment.resource.dto;

import org.springframework.data.domain.Page;

public record PaginationResponse(Integer page, Integer pageSize, Integer totalElements, Integer totalPages) {
    public static PaginationResponse from(Page<?> page) {

        return new PaginationResponse(page.getNumber(), page.getSize(), page.getNumberOfElements(), page.getTotalPages());

    }
}
