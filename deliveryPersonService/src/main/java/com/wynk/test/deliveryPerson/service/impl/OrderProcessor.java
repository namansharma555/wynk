package com.wynk.test.deliveryPerson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.deliveryPerson.model.OrderDeliveryDTO;
import com.wynk.test.deliveryPerson.request.UpdateDeliveryPersonStatusRequest;
import com.wynk.test.deliveryPerson.response.DeliveryPersonStatusResponse;
import com.wynk.test.deliveryPerson.service.IDeliveryPersonService;
import com.wynk.test.deliveryPerson.service.IOrderDeliveryService;
import com.wynk.test.request.AssignOrderRequest;
import com.wynk.test.request.UpdateOrderStatusRequest;
import com.wynk.test.response.AssignOrderResponse;
import com.wynk.test.response.UpdateOrderStatusResponse;

@Component
public class OrderProcessor {

	@Autowired
	IOrderDeliveryService orderDeliveryService;

	@Autowired
	IDeliveryPersonService deliveryPersonService;

	@Autowired
	RestTemplate restTemplate;

	@Value("${restaurant.service.host}")
	private String restaurantHost;
	@Value("${restaurant.service.port}")
	private String restaurantPort;

	public AssignOrderResponse assignOrder(AssignOrderRequest request) {
		AssignOrderResponse response = new AssignOrderResponse();

		DeliveryPersonStatus deliveryStatus = deliveryPersonService.getDeliveryPersonStatus();
		if (deliveryStatus.equals(DeliveryPersonStatus.IDLE)) {
			OrderDeliveryDTO dto = OrderDeliveryDTO.builder().deliveryPersonStatus(DeliveryPersonStatus.ACTIVE)
					.orderDeliveryStatus(OrderDeliveryStatus.ACCEPTED).orderId(request.getOrderId())
					.userAddress(request.getUserAddress()).userId(request.getUserId()).build();
			orderDeliveryService.assignOrder(dto);
			response.setOrderDeliveryStatus(OrderDeliveryStatus.ACCEPTED);
		} else {
			response.setOrderDeliveryStatus(OrderDeliveryStatus.DECLINED);
		}
		return response;
	}

	public DeliveryPersonStatusResponse getDeliveryPersonStatus() {

		DeliveryPersonStatusResponse response = new DeliveryPersonStatusResponse();
		DeliveryPersonStatus deliveryStatus = deliveryPersonService.getDeliveryPersonStatus();
		switch (deliveryStatus) {
		case ACTIVE:
			OrderDeliveryDTO orderDTO = orderDeliveryService.getCurrentOrder();
			Integer estimatedTime = orderDeliveryService.getCurrentOrderTime(orderDTO);
			response.setEstimatedTime(estimatedTime);
			response.setDeliveryPersonStatus(DeliveryPersonStatus.ACTIVE);
			break;
		case IDLE:
			response.setDeliveryPersonStatus(DeliveryPersonStatus.IDLE);
			break;
		}
		return response;

	}

	public void updateDeliveryPersonStatus(UpdateDeliveryPersonStatusRequest request) {
		OrderDeliveryStatus status = request.getOrderDeliveryStatus();
		String orderId = request.getOrderId();
		switch (status) {

		case STARTED:
			orderDeliveryService.updateOrderStatus(orderId, OrderDeliveryStatus.STARTED, DeliveryPersonStatus.ACTIVE);
			updateRestaurant(request);
		case DELIVERED:
			orderDeliveryService.updateOrderStatus(orderId, OrderDeliveryStatus.STARTED, DeliveryPersonStatus.IDLE);
			updateRestaurant(request);
		case CANCELLED:
			orderDeliveryService.updateOrderStatus(orderId, OrderDeliveryStatus.STARTED, DeliveryPersonStatus.IDLE);
			updateRestaurant(request);

		}

	}

	private void updateRestaurant(UpdateDeliveryPersonStatusRequest request) {

		String restaurantURL = "http://" + restaurantHost + ":" + restaurantPort
				+ "/restaurantService/api/v1/updateOrder";

		UpdateOrderStatusRequest orderRequest = UpdateOrderStatusRequest.builder()
				.orderDeliveryStatus(request.getOrderDeliveryStatus()).orderId(request.getOrderId())
				.userId(request.getUserId()).build();

		ResponseEntity<UpdateOrderStatusResponse> response = restTemplate.postForEntity(restaurantURL, orderRequest,
				UpdateOrderStatusResponse.class);

	}

}
