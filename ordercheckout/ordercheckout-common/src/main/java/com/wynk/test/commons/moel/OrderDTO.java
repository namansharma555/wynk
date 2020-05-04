package com.wynk.test.commons.moel;

import java.util.List;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private String orderId;
	private OrderStatus orderStatus;
	private OrderMetadata metadata;
	private Integer version;

}
