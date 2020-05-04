package com.wynk.test.entity;

import java.time.LocalDateTime;

import com.wynk.test.commons.enums.OrderStatus;

import lombok.Data;

@Data

public class OrderEntity {
	private String orderId;
	private OrderStatus orderStatus;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
