package com.wynk.test.deliveryPerson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.commons.enums.OrderDeliveryStatus;
import com.wynk.test.commons.moel.Address;
import com.wynk.test.deliveryPerson.dao.DeliveryPersonDao;
import com.wynk.test.deliveryPerson.dao.OrderDeliveryDao;
import com.wynk.test.deliveryPerson.dao.entity.DeliveryPersonEntity;
import com.wynk.test.deliveryPerson.dao.entity.OrderDeliveryEntity;
import com.wynk.test.deliveryPerson.model.OrderDeliveryDTO;
import com.wynk.test.deliveryPerson.service.IOrderDeliveryService;

public class OrderDeliveryService implements IOrderDeliveryService {

	@Autowired
	DeliveryPersonDao deliveryPersonDao;

	@Autowired
	OrderDeliveryDao orderDeliveryDao;

	@Override
	@Transactional
	public void assignOrder(OrderDeliveryDTO orderDeliveryDTO) {

		OrderDeliveryEntity entity = OrderDeliveryEntity.builder().orderId(orderDeliveryDTO.getOrderId())
				.status(orderDeliveryDTO.getOrderDeliveryStatus()).userId(orderDeliveryDTO.getUserId())
				.userAddresss(orderDeliveryDTO.getUserAddress()).build();
		DeliveryPersonEntity deliveryPersonEntity = DeliveryPersonEntity.builder().id(1)
				.status(orderDeliveryDTO.getDeliveryPersonStatus()).build();

		orderDeliveryDao.save(entity);
		deliveryPersonDao.save(deliveryPersonEntity);

	}

	@Override

	public OrderDeliveryDTO getCurrentOrder() {

		OrderDeliveryEntity entity = orderDeliveryDao.getCurrentOrder();
		OrderDeliveryDTO orderdto = OrderDeliveryDTO.builder().orderId(entity.getOrderId())
				.orderDeliveryStatus(entity.getStatus()).userAddress(entity.getUserAddresss())
				.userId(entity.getUserId()).build();
		return orderdto;
	}

	@Override
	@Transactional
	public void updateOrderStatus(String orderId, OrderDeliveryStatus status,
			DeliveryPersonStatus deliveryPersonStatus) {

		OrderDeliveryEntity entity = OrderDeliveryEntity.builder().orderId(orderId).status(status).build();
		DeliveryPersonEntity deliveryPersonEntity = DeliveryPersonEntity.builder().id(1).status(deliveryPersonStatus)
				.build();
		orderDeliveryDao.save(entity);
		deliveryPersonDao.save(deliveryPersonEntity);

	}

	@Override
	// this will be the s2 service which will be responsible for calculating the
	// distance of the user address from the riders curren location
	public Integer getCurrentOrderTime(OrderDeliveryDTO orderDTO) {

		Address userAddress = orderDTO.getUserAddress();
		return (int) (Math.random() * 30);
	}

}
