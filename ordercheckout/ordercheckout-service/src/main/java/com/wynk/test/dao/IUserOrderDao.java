package com.wynk.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.wynk.test.commons.enums.OrderStatus;
import com.wynk.test.entity.UserOrderEntity;

@Mapper
public interface IUserOrderDao {

	// @Select("Select item_id,item_name,bake_time from item where item_id
	// =#{itemId}")
	@Insert("Insert into user_order(user_id,order_id,order_metadata,order_status)"
			+ "VALUES(#{userId},#{orderId},#{orderMetadata},#{orderStatus} ON DUPLICATE KEY UPDATE order_status= #{orderStatus}")
	public void save(UserOrderEntity userOrderEntity);

	@Update("Update user_order set order_status=#{state} where user_id=#{user_id} and order_id=#{orderId}")
	public void updateUserOrderStatus(@Param("userId") String userId,@Param("orderId") String orderId, @Param("state") OrderStatus state);

	/*
	 *
	 *@Update("UPDATE transaction set state = #{state} WHERE txn_id = #{txnId}")
	 * void updateStateByTxnId(@Param("txnId") String txnId, @Param("state")
	 * TxnState state);

	 *
	 * 
	 * @Insert("INSERT INTO client(id, client_id, version, client_secret, callback_url, created_on, updated_on)"
	 * +
	 * "VALUES(#{id}, #{clientId}, #{version}, #{clientSecret}, #{createdOn}, now() )"
	 * ) public void create(Client transaction);
	 * 
	 * 
	 * 
	 *  user_order
	 * private String userId; private String orderId; private String orderMetadata;
	 * private OrderStatus orderStatus; private LocalDateTime createdOn; private
	 * LocalDateTime updatedOn;
	 * 
	 * 
	 */

	/*
	 * 
	 * @Select("SELECT * FROM client WHERE client_id = #{clientId} ORDER BY version DESC LIMIT 1 "
	 * )
	 * 
	 * @Results(value = { @Result(column = "id", property = "id"), @Result(column =
	 * "client_id", property = "clientId"),
	 * 
	 * @Result(column = "version", property = "version"),
	 * 
	 * @Result(column = "client_secret", property = "clientSecret"),
	 * 
	 * @Result(column = "callback_url", property = "callbackUrl"),
	 * 
	 * @Result(column = "created_on", property = "createdOn"),
	 * 
	 * @Result(column = "updated_on", property = "updatedOn") }) public Client
	 * getClientByClientId(@Param("clientId") String clientId);
	 * 
	 * 
	 * 
	 * 
	 * private String itemId; private String itemName; private Integer bakeTime;
	 * private LocalDateTime createdOn; private LocalDateTime updatedOn;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Select("select accountId,instrumentId,type,status,attributes,created_on,updated_on from  `INSTRUMENT_ACCOUNT` WHERE `accountId` = #{accountId}"
	 * ) AccountEntity getAccountByAccountId(@Param("accountId") String accountId);
	 * 
	 * 
	 */

}
