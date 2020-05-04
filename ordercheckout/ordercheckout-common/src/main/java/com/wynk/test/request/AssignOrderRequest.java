package com.wynk.test.request;

import com.wynk.test.commons.moel.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class AssignOrderRequest {

	String userId;
	Address userAddress;
	String orderId;
	Integer deliveryPersonId;
}
