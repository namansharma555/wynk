package com.wynk.test.deliveryPerson.dao.entity;

import com.wynk.test.commons.enums.DeliveryPersonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPersonEntity {

	private Integer id;
	private String deliveryPersonName;
	private DeliveryPersonStatus status;
}
