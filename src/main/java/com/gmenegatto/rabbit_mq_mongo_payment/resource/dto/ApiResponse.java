package com.gmenegatto.rabbit_mq_mongo_payment.resource.dto;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse pagination) {

}
