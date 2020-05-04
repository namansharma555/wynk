package com.wynk.test.deilvery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.commons.moel.Address;
import com.wynk.test.dao.IDeliveryPersonDao;
import com.wynk.test.deilvery.service.IDeliverService;
import com.wynk.test.entity.DeliveryPerson;
import com.wynk.test.request.AssignOrderRequest;
import com.wynk.test.response.AssignOrderResponse;

public class DeliveryServiceImpl implements IDeliverService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IDeliveryPersonDao deliveryPersonDao;

	public List<DeliveryPerson> getIdlePersonsList() {
		return deliveryPersonDao.getAllIdleDeliveryPersons(DeliveryPersonStatus.IDLE);

	}

	@Override
	public Integer getOrderDeliveryTransitTime(Address userdeliverAddress) {

		int transitTime = (int) (30 * Math.random());
		return transitTime;
	}

	@Override
	public OrderDeliveryStatus assignDeliveryExecutive(DeliveryPerson person, String orderId, String userId,
			Address userAddress) {

		AssignOrderResponse assignOrderResponse = null;

		String deliveryPersonURL = "http://" + person.getHost() + ":" + person.getPort()
				+ "/deliveryService/api/assignOrder";

		AssignOrderRequest orderRequest = AssignOrderRequest.builder().deliveryPersonId(person.getId()).orderId(orderId)
				.userAddress(userAddress).userId(userId).build();

		ResponseEntity<AssignOrderResponse> response = restTemplate.postForEntity(deliveryPersonURL, orderRequest,
				AssignOrderResponse.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			assignOrderResponse = response.getBody();
		}

		return assignOrderResponse.getOrderDeliveryStatus();
	}

	@Override
	public void updateDeliveryPersonStatus(DeliveryPerson deliveryPerson) {

		deliveryPersonDao.save(deliveryPerson);
		;

	}

}
