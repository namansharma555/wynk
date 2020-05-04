package com.wynk.test.deliveryPerson.response;

import com.wynk.test.commons.enums.DeliveryPersonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryPersonStatusResponse {

	
	private String orderId;
	private Integer estimatedTime;
	private DeliveryPersonStatus deliveryPersonStatus;
}
