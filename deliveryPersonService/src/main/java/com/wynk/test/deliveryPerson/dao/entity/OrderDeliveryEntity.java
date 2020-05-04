package com.wynk.test.deliveryPerson.dao.entity;

import java.time.LocalDateTime;

import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.commons.moel.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeliveryEntity {

	private Integer id;
	private String orderId;
	private OrderDeliveryStatus status;
	private String userId;
	private Address userAddresss;
	private LocalDateTime created_on;
	private LocalDateTime updated_on;

}
