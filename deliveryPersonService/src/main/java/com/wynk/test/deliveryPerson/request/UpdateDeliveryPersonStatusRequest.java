package com.wynk.test.deliveryPerson.request;

import com.wynk.test.commons.enums.OrderDeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateDeliveryPersonStatusRequest {

	private String orderId;
	private String userId;
	private OrderDeliveryStatus orderDeliveryStatus;
	
}
