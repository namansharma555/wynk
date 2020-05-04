package com.wynk.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wynk.test.entity.OrderLedger;

@Mapper
public interface IOrderLedgerDao {

	@Insert("INSERT INTO order_ledger(order_id, user_id, order_metadata, order_status, version) values(#{orderId},#{userId},#{orderMetadata},#{orderStatus},#{version})")
	public void save(OrderLedger transaction);

	@Select("SELECT * FROM order_ledger WHERE order_id = #{orderId} ORDER BY version DESC LIMIT 1")
	@Results(value = { @Result(column = "order_id", property = "orderId"),
			@Result(column = "user_id", property = "userId"),
			@Result(column = "order_metadata", property = "orderMetadata"),
			@Result(column = "order_status", property = "orderStatus"),
			@Result(column = "version", property = "version"), @Result(column = "created_on", property = "createdOn"),
			@Result(column = "updated_on", property = "updatedOn") })
	OrderLedger getOrderByOrderId(@Param("orderId") final String orderId);

}
