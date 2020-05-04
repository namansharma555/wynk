package com.wynk.test.order.service;

import com.wynk.test.commons.enums.OrderStatus;
import com.wynk.test.commons.moel.OrderDTO;

public interface IOrderService {

	public void createOrder(OrderDTO orderDTO);

	public OrderDTO updateOrder(OrderDTO orderDTO);

	public OrderStatus getOrderStatus(String orderId);

	public OrderDTO getOrderByOrderId(String orderId);

}
