package com.wynk.test.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderDeliveryEntity {

	
	private String orderId;
	private Integer deliveryPersonId;
	private LocalDateTime estimatedArrivalTime;
	private LocalDateTime requestReceivedTime;
	
}
