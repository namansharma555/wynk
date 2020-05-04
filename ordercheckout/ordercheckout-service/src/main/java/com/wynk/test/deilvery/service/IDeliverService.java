package com.wynk.test.deilvery.service;

import java.util.List;

import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.commons.moel.Address;
import com.wynk.test.entity.DeliveryPerson;

public interface IDeliverService {

	public List<DeliveryPerson> getIdlePersonsList();

	public Integer getOrderDeliveryTransitTime(Address userdeliverAddress);

	public OrderDeliveryStatus assignDeliveryExecutive(DeliveryPerson person, String orderId, String userId,
			Address userAddress);
	
	public void updateDeliveryPersonStatus(DeliveryPerson deliveryPerson);
}
