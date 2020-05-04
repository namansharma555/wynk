package com.wynk.test.deliveryPerson.service;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.deliveryPerson.model.OrderDeliveryDTO;

public interface IOrderDeliveryService {

	public void assignOrder(OrderDeliveryDTO orderDeliveryDTO);

	public OrderDeliveryDTO getCurrentOrder();
	
	public void updateOrderStatus(String orderId,OrderDeliveryStatus status,DeliveryPersonStatus active);

	public Integer getCurrentOrderTime(OrderDeliveryDTO orderDTO);
}
