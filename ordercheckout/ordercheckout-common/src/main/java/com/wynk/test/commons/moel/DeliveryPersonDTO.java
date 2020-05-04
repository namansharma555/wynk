package com.wynk.test.commons.moel;

import com.wynk.test.commons.enums.DeliveryPersonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class DeliveryPersonDTO {
	
	Integer id;
	String name;
	String host;
	String port;
	String number;
	Address address;
	DeliveryPersonStatus status;

}
