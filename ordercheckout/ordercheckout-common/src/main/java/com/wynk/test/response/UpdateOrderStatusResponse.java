package com.wynk.test.response;

import com.wynk.test.commons.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateOrderStatusResponse {

	private OrderStatus orderStatus;
}
