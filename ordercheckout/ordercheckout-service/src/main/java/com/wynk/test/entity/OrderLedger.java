package com.wynk.test.entity;

import java.time.LocalDateTime;

import com.wynk.test.commons.enums.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class OrderLedger {

	private String orderId;
	private String orderMetadata;// {“order_details”:{[{item_id,quantity},{item_id,quanity}],},”delivery_details”:{“person_id”:123,”delivery_address”:”12122112”}}
	private OrderStatus orderStatus;
	private Integer version;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

}
