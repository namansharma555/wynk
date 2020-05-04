package com.wynk.test.deliveryPerson.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wynk.test.deliveryPerson.dao.entity.DeliveryPersonEntity;

@Mapper
public interface DeliveryPersonDao {

	@Insert("INSERT INTO delivery_person (delivery_person_name,status) VALUES(#{deliveryPersonName},#{status})  ON DUPLICATE KEY UPDATE delivery_person= #{status}")
	void save(DeliveryPersonEntity deliveryPersonEntity);

	@Select("select * from delivery_person where id=#{id}")
	@Results(value = { @Result(column = "id", property = "id"), @Result(column = "status", property = "status"),
			@Result(column = "delivery_person_name", property = "deliveryPersonName"),

	})
	public DeliveryPersonEntity getDeliveryPersonDetails(@Param("id") Integer id);

}
