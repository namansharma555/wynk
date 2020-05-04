package com.wynk.test.request;

import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.commons.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStatusRequest {

	private String orderId;
	private String userId;
	private Integer deliveryPersonId;
	private OrderDeliveryStatus orderDeliveryStatus;
}
