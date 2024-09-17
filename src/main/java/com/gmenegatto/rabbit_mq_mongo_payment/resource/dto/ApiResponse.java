package com.gmenegatto.rabbit_mq_mongo_payment.resource.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record ApiResponse<T>(Map<String, BigDecimal> summary, List<T> data, PaginationResponse pagination) {

}
