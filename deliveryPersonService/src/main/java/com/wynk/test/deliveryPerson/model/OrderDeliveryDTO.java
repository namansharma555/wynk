package com.wynk.test.deliveryPerson.model;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.commons.moel.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderDeliveryDTO {

	
	private String orderId;
	private OrderDeliveryStatus orderDeliveryStatus;
	private DeliveryPersonStatus deliveryPersonStatus;
	private String userId;
	private Address userAddress;
	
}
