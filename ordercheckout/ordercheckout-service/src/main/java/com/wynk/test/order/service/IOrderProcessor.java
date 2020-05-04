package com.wynk.test.order.service;
import com.wynk.test.request.OrderStatusRequest;
import com.wynk.test.request.PlaceOrderRequest;
import com.wynk.test.request.UpdateOrderStatusRequest;
import com.wynk.test.response.OrderStatusResponse;
import com.wynk.test.response.PlaceOrderResponse;
import com.wynk.test.response.UpdateOrderStatusResponse;

public interface IOrderProcessor {

	public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest);

	public OrderStatusResponse getOrderStatus(OrderStatusRequest orderStatusRequest);

	public UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest);
	
	
}
