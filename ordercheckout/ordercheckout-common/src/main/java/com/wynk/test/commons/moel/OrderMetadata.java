package com.wynk.test.commons.moel;

import java.util.List;

import javax.xml.ws.BindingType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMetadata {

	UserDetails userDetails;
	DeliveryPersonDetails deliveryPersonDetails;
	OrderDetails orderDetails;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UserDetails{
		
		String userId;
		Address userAddresss;
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeliveryPersonDetails{
		
		Integer deliveryPersonId;
		String deliveryPersonName;
		String deliveryPersonPort;
		String deliveryPersonHost;
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	
	public static class OrderDetails{
		
		List<ItemQuantity> list;
	}
}
