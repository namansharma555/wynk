package com.wynk.test.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wynk.test.commons.enums.OrderStatus;
import com.wynk.test.commons.moel.OrderDTO;
import com.wynk.test.commons.moel.OrderMetadata;
import com.wynk.test.dao.IOrderLedgerDao;
import com.wynk.test.dao.IOrderTransactionManagementDao;
import com.wynk.test.dao.IUserOrderDao;
import com.wynk.test.entity.OrderLedger;
import com.wynk.test.entity.OrderTransactionManagementEntity;
import com.wynk.test.entity.UserOrderEntity;
import com.wynk.test.order.service.IOrderService;
import com.wynk.test.utils.JsonDeserializer;
import com.wynk.test.utils.JsonSerializer;

public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderLedgerDao orderLedgerDao;

	@Autowired
	IOrderTransactionManagementDao transactionManagementDao;

	@Autowired
	IUserOrderDao userOrderDao;

	@Override
	@Transactional
	public void createOrder(OrderDTO orderDTO) {

		String orderId = orderDTO.getOrderId();
		OrderStatus status = orderDTO.getOrderStatus();
		OrderMetadata orderMetadata = orderDTO.getMetadata();

		String metadata = JsonSerializer.serialize(orderMetadata);

		OrderLedger transaction = OrderLedger.builder().orderId(orderId).orderMetadata(metadata).orderStatus(status)
				.version(1).build();
		UserOrderEntity entity = UserOrderEntity.builder().userId(orderDTO.getMetadata().getUserDetails().getUserId())
				.orderId(orderId).orderMetadata(metadata).orderStatus(status).build();
		OrderTransactionManagementEntity transactionManagementEntity = OrderTransactionManagementEntity.builder()
				.orderId(orderId).orderStatus(status).build();
		orderLedgerDao.save(transaction);
		transactionManagementDao.save(transactionManagementEntity);
		userOrderDao.save(entity);

	}

	@Override
	@Transactional
	public OrderDTO updateOrder(OrderDTO orderDTO) {

		String orderId = orderDTO.getOrderId();
		OrderStatus status = orderDTO.getOrderStatus();
		OrderMetadata orderMetadata = orderDTO.getMetadata();

		String metadata = JsonSerializer.serialize(orderMetadata);

		OrderLedger orderLedger = OrderLedger.builder().orderId(orderId).orderMetadata(metadata).orderStatus(status)
				.version(orderDTO.getVersion()).build();
		UserOrderEntity entity = UserOrderEntity.builder().userId(orderDTO.getMetadata().getUserDetails().getUserId())
				.orderId(orderId).orderMetadata(metadata).orderStatus(status).build();
		OrderTransactionManagementEntity transactionManagementEntity = OrderTransactionManagementEntity.builder()
				.orderId(orderId).orderStatus(status).build();
		orderLedgerDao.save(orderLedger);
		transactionManagementDao.save(transactionManagementEntity);
		userOrderDao.save(entity);
		return orderDTO;
	}

	@Override
	public OrderStatus getOrderStatus(String orderId) {

		OrderTransactionManagementEntity orderTransactionManagementEntity = transactionManagementDao
				.getOrderStatus(orderId);
		return orderTransactionManagementEntity.getOrderStatus();
	}

	@Override
	public OrderDTO getOrderByOrderId(String orderId) {

		OrderLedger orderLedger = orderLedgerDao.getOrderByOrderId(orderId);
		OrderMetadata orderMetadata = JsonDeserializer.deserialize(orderLedger.getOrderMetadata(), OrderMetadata.class);
		OrderDTO orderDTO = OrderDTO.builder().metadata(orderMetadata).orderId(orderId)
				.version(orderLedger.getVersion()).orderStatus(orderLedger.getOrderStatus()).build();
		return orderDTO;
	}

}
