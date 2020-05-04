package com.wynk.test.entity;

import java.time.LocalDateTime;

import com.wynk.test.commons.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderTransactionManagementEntity {

	private String orderId;
	private OrderStatus orderStatus;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

}
