package com.wynk.test.response;

import com.wynk.test.commons.enums.OrderDeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AssignOrderResponse {

	OrderDeliveryStatus orderDeliveryStatus;
	Integer estimatedArrivalTime;
}
