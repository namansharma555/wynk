package com.wynk.test.entity;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.moel.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DeliveryPerson {

	Integer id;
	String name;
	String host;
	String port;
	String number;
	Address address;
	DeliveryPersonStatus status;

}
