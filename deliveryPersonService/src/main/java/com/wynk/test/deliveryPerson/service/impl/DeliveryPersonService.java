package com.wynk.test.deliveryPerson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.deliveryPerson.dao.DeliveryPersonDao;
import com.wynk.test.deliveryPerson.dao.entity.DeliveryPersonEntity;
import com.wynk.test.deliveryPerson.service.IDeliveryPersonService;

public class DeliveryPersonService implements IDeliveryPersonService{

	@Autowired
	DeliveryPersonDao deliveryPersonDao;

	@Override
	public DeliveryPersonStatus getDeliveryPersonStatus() {
		
		DeliveryPersonEntity entity = deliveryPersonDao.getDeliveryPersonDetails(1);
		return entity.getStatus();
	}

}
