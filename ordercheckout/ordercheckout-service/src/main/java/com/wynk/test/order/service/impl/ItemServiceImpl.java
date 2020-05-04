package com.wynk.test.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wynk.test.commons.moel.ItemQuantity;
import com.wynk.test.dao.IItemDao;
import com.wynk.test.entity.Item;
import com.wynk.test.order.service.IItemService;

public class ItemServiceImpl implements IItemService{

	@Autowired
	IItemDao itemDao;
	
	@Override
	public Integer getOrderBakeTime(List<ItemQuantity> list) {
		
		Integer bakeTime = Integer.MIN_VALUE;
		for(ItemQuantity item:list) {
			Item restaurantItem = itemDao.getItemDetails(item.getItemId());
			bakeTime = Math.max(bakeTime, restaurantItem.getBakeTime());
		}
		return bakeTime;
	}

}
