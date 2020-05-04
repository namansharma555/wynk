package com.wynk.test.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.commons.enums.OrderStatus;
import com.wynk.test.commons.moel.Address;
import com.wynk.test.commons.moel.OrderDTO;
import com.wynk.test.commons.moel.OrderMetadata;
import com.wynk.test.commons.moel.OrderMetadata.DeliveryPersonDetails;
import com.wynk.test.commons.moel.OrderMetadata.OrderDetails;
import com.wynk.test.commons.moel.OrderMetadata.UserDetails;
import com.wynk.test.dao.IDeliveryPersonDao;
import com.wynk.test.deilvery.service.IDeliverService;
import com.wynk.test.entity.DeliveryPerson;
import com.wynk.test.order.service.IItemService;
import com.wynk.test.order.service.IOrderProcessor;
import com.wynk.test.order.service.IOrderService;
import com.wynk.test.request.OrderStatusRequest;
import com.wynk.test.request.PlaceOrderRequest;
import com.wynk.test.request.UpdateOrderStatusRequest;
import com.wynk.test.response.OrderStatusResponse;
import com.wynk.test.response.PlaceOrderResponse;
import com.wynk.test.response.UpdateOrderStatusResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderProcessor implements IOrderProcessor {

	private static String ORDER_PREFIX = "ORDER_";

	private static Integer BUFFER_TIME = 5;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IOrderService orderService;

	@Autowired
	IDeliverService deliveryService;

	@Autowired
	IItemService itemService;

	@Autowired
	IDeliveryPersonDao deliveryPersonDao;

	@Override
	public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {

		PlaceOrderResponse response = new PlaceOrderResponse();
		String orderId = createOrderId();
		OrderMetadata metadata = OrderMetadata.builder()
				.userDetails(UserDetails.builder().userId(placeOrderRequest.getUserId())
						.userAddresss(placeOrderRequest.getDeliveryAddress()).build())
				.orderDetails(OrderDetails.builder().list(placeOrderRequest.getList()).build()).build();

		OrderDTO dto = OrderDTO.builder().orderId(orderId).metadata(metadata).orderStatus(OrderStatus.ACCEPTED)
				.version(1).build();

		orderService.createOrder(dto);

		response.setOrderID(orderId);
		response.setOrderStatus(OrderStatus.RECEIVED);

		// assign delivery

		Address userAddress = placeOrderRequest.getDeliveryAddress();
		String userId = placeOrderRequest.getUserId();

		List<DeliveryPerson> list = deliveryService.getIdlePersonsList();
		DeliveryPerson person = null;
		if (!list.isEmpty()) {
			person = list.get(0);

			// For future use
			Integer orderBakeTime = itemService.getOrderBakeTime(placeOrderRequest.getList());
			Integer transitTime = deliveryService.getOrderDeliveryTransitTime(placeOrderRequest.getDeliveryAddress());
			Integer deliveryTime = orderBakeTime + transitTime + BUFFER_TIME;

			OrderDeliveryStatus orderDeliveryStatus = deliveryService.assignDeliveryExecutive(person, orderId, userId,
					userAddress);
			if (orderDeliveryStatus.equals(OrderDeliveryStatus.ACCEPTED)) {

				OrderMetadata orderMetadata = dto.getMetadata();
				orderMetadata.setDeliveryPersonDetails(DeliveryPersonDetails.builder()
						.deliveryPersonName(person.getName()).deliveryPersonId(person.getId())
						.deliveryPersonHost(person.getHost()).deliveryPersonPort(person.getPort()).build());
				int version = dto.getVersion() + 1;
				dto.setVersion(version);
				dto.setMetadata(orderMetadata);
				dto.setOrderStatus(OrderStatus.ACCEPTED);
				person.setStatus(DeliveryPersonStatus.ACTIVE);
				orderService.updateOrder(dto);
				deliveryService.updateDeliveryPersonStatus(person);

				response.setOrderStatus(OrderStatus.ACCEPTED);
			}

		}
		return response;
	}

	private String createOrderId() {
		StringBuilder sb = new StringBuilder();
		sb.append(ORDER_PREFIX).append(System.currentTimeMillis());
		return sb.toString();

	}

	@Override
	public OrderStatusResponse getOrderStatus(OrderStatusRequest orderStatusRequest) {

		String orderId = orderStatusRequest.getOrderId();
		OrderStatus orderStatus = orderService.getOrderStatus(orderId);
		return new OrderStatusResponse(orderStatus);
	}

	@Override
	public UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {

		UpdateOrderStatusResponse response = new UpdateOrderStatusResponse();
		String orderId = updateOrderStatusRequest.getOrderId();
		DeliveryPerson person = new DeliveryPerson();
		person.setId(updateOrderStatusRequest.getDeliveryPersonId());
		OrderDTO orderDTO = orderService.getOrderByOrderId(orderId);
		Integer version = orderDTO.getVersion();
		orderDTO.setVersion(version);
		OrderDeliveryStatus orderDeliveryStatus = updateOrderStatusRequest.getOrderDeliveryStatus();
		switch (orderDeliveryStatus) {

		case ACCEPTED:
			orderDTO.setOrderStatus(OrderStatus.ACCEPTED);
			response.setOrderStatus(OrderStatus.ACCEPTED);

			orderService.updateOrder(orderDTO);
			break;
		case STARTED:
			orderDTO.setOrderStatus(OrderStatus.ON_DELIVERY);
			response.setOrderStatus(OrderStatus.ON_DELIVERY);
			orderService.updateOrder(orderDTO);
			break;
		case DELIVERED:
			orderDTO.setOrderStatus(OrderStatus.DELIVERED);
			response.setOrderStatus(OrderStatus.DELIVERED);
			person.setStatus(DeliveryPersonStatus.IDLE);
			orderService.updateOrder(orderDTO);
			deliveryService.updateDeliveryPersonStatus(person);
			break;
		case CANCELLED:
			orderDTO.setOrderStatus(OrderStatus.RECEIVED);
			response.setOrderStatus(OrderStatus.RECEIVED);
			person.setStatus(DeliveryPersonStatus.IDLE);
			orderService.updateOrder(orderDTO);
			deliveryService.updateDeliveryPersonStatus(person);
			break;
		default:
			log.error(String.format("Invalid request for order %s", orderId));

		}
		return response;
	}

}
