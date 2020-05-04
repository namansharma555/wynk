package com.wynk.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wynk.test.order.service.IOrderProcessor;
import com.wynk.test.request.OrderStatusRequest;
import com.wynk.test.request.PlaceOrderRequest;
import com.wynk.test.request.UpdateOrderStatusRequest;
import com.wynk.test.response.OrderStatusResponse;
import com.wynk.test.response.PlaceOrderResponse;
import com.wynk.test.response.UpdateOrderStatusResponse;

@RestController("/restaurantService/api")
public class OrderCheckoutController {
	
	@Autowired
	IOrderProcessor orderProcessor;

	@PostMapping("/v1/placeOrder")
	public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody @Valid PlaceOrderRequest request) {

		PlaceOrderResponse response= orderProcessor.placeOrder(request);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/v1/updateOrder")
	public ResponseEntity<UpdateOrderStatusResponse> updateOrder(@RequestBody @Valid UpdateOrderStatusRequest request) {

		UpdateOrderStatusResponse response = orderProcessor.updateOrderStatus(request);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/v1/getOrderStatus")
	public ResponseEntity<OrderStatusResponse> getOrderStatus(@RequestBody @Valid OrderStatusRequest request) {

		OrderStatusResponse response = orderProcessor.getOrderStatus(request);
		return ResponseEntity.ok().body(response);
		
	}

}
