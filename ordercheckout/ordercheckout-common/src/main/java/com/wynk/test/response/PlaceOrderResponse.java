package com.wynk.test.response;

import com.wynk.test.commons.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderResponse {
	
	private String orderID;
	private OrderStatus orderStatus;

}
