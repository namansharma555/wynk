package com.wynk.test.deliveryPerson.model;

import com.wynk.test.commons.enums.DeliveryPersonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DeliveryPerson {
	
	private String id;
	private String deliveryPersonName;
	private DeliveryPersonStatus status;
}
