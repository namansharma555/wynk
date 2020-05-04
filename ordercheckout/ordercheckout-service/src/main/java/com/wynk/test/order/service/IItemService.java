package com.wynk.test.order.service;

import java.util.List;

import com.wynk.test.commons.moel.ItemQuantity;

public interface IItemService {

	public Integer getOrderBakeTime(List<ItemQuantity> list);
}
