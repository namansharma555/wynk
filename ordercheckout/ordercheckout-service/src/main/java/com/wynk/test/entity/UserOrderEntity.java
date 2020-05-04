package com.wynk.test.entity;

import java.time.LocalDateTime;

import com.wynk.test.commons.enums.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOrderEntity {

	private String userId;
	private String orderId;
	private String orderMetadata;
	private OrderStatus orderStatus;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
