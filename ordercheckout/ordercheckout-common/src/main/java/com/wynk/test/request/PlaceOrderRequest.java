package com.wynk.test.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.wynk.test.commons.moel.Address;
import com.wynk.test.commons.moel.ItemQuantity;

import lombok.Data;

@Data
public class PlaceOrderRequest {

	@NotNull(message = "User Id cannot be null")
	String userId;
	@NotNull(message = "Item list cannot be null")
	List<ItemQuantity> list;
	@NotNull(message = "delivery address cannot be null")
	Address deliveryAddress;

}
