package com.wynk.test.deliveryPerson.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wynk.test.deliveryPerson.request.UpdateDeliveryPersonStatusRequest;
import com.wynk.test.deliveryPerson.response.DeliveryPersonStatusResponse;
import com.wynk.test.deliveryPerson.service.impl.OrderProcessor;
import com.wynk.test.request.AssignOrderRequest;
import com.wynk.test.response.AssignOrderResponse;

@RestController("/deliveryPerson/api/")
public class DeliveryPersonController {

	@Autowired
	OrderProcessor orderProcessor;

	@RequestMapping("/v1/assignOrder")
	public ResponseEntity<AssignOrderResponse> assignOrder(@RequestBody @Valid AssignOrderRequest request) {

		AssignOrderResponse response = orderProcessor.assignOrder(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/v1/getDeliveryPersonStatus")
	public ResponseEntity<DeliveryPersonStatusResponse> getDeliveryPersonStatus() {

		DeliveryPersonStatusResponse response = orderProcessor.getDeliveryPersonStatus();
		return ResponseEntity.ok(response);
	}

	@RequestMapping("/v1/updateDeliveryPersonStatus")
	public void updateDeliveryPersonStatus(@RequestBody @Valid UpdateDeliveryPersonStatusRequest request) {

		orderProcessor.updateDeliveryPersonStatus(request);
	}

}
