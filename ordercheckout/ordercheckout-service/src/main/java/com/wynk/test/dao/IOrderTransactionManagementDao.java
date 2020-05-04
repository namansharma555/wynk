package com.wynk.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wynk.test.entity.OrderTransactionManagementEntity;

@Mapper
public interface IOrderTransactionManagementDao {

	
	@Insert("INSERT INTO order_transaction_management (order_id,order_state) VALUES(#{orderID},#{orderState})  ON DUPLICATE KEY UPDATE transaction_state= #{transactionState}")
	void save(OrderTransactionManagementEntity transaction);
	
	@Select("select order_state from order_transaction_management where order_id=#{orderId}")
	@Results(value = {
			@Result(column="order_id",property="orderId"),
			@Result(column="order_state",property="orderState"),
			@Result(column="created_on",property="createdOn"),
			@Result(column = "updated_on", property = "updatedOn")
	})
	public OrderTransactionManagementEntity getOrderStatus(@Param("orderId") String orderId);
}
