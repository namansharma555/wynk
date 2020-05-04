package com.wynk.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wynk.test.entity.Item;

@Mapper
public interface IItemDao {

	@Select("Select item_id,item_name,bake_time from item where item_id =#{itemId}")
	@Results(value= {
			@Result(column="item_id",property= "itemId"),
			@Result(column="item_name",property= "itemName"),
			@Result(column="bake_time",property= "bakeTime"),
			})
	public Item getItemDetails(@Param("itemId") String itemId) ;
	
	
	/*
	 * 
	 * @Select("SELECT * FROM client WHERE client_id = #{clientId} ORDER BY version DESC LIMIT 1 ")
	@Results(value = { @Result(column = "id", property = "id"), @Result(column = "client_id", property = "clientId"),
			@Result(column = "version", property = "version"),
			@Result(column = "client_secret", property = "clientSecret"),
			@Result(column = "callback_url", property = "callbackUrl"),
			@Result(column = "created_on", property = "createdOn"),
			@Result(column = "updated_on", property = "updatedOn") })
	public Client getClientByClientId(@Param("clientId") String clientId);

	 * 
	 * 
	 * 
	 * private String itemId;
	private String itemName;
	private Integer bakeTime;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Select("select accountId,instrumentId,type,status,attributes,created_on,updated_on from  `INSTRUMENT_ACCOUNT` WHERE `accountId` = #{accountId}")
    AccountEntity getAccountByAccountId(@Param("accountId") String accountId);
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
}
