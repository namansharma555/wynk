package com.wynk.test.deliveryPerson.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wynk.test.deliveryPerson.dao.entity.OrderDeliveryEntity;

@Mapper
public interface OrderDeliveryDao {

	@Insert("INSERT INTO order_delivery (orderId,status,user_id,user_address) VALUES(#{orderId},#{status},#{userId},#{userAddress})  ON DUPLICATE KEY UPDATE status= #{status}")
	void save(OrderDeliveryEntity orderDeliveryEntity);

	@Select("SELECT * from order_delivery where id=(SELECT max(id) from order_delivery)")
	public OrderDeliveryEntity getCurrentOrder();

}
