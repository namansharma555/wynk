package com.wynk.test.deliveryPerson.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryPersonStatusRequest {

	private String orderId;
}
