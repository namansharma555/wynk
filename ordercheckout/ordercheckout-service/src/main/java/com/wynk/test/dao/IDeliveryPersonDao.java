package com.wynk.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wynk.test.commons.enums.DeliveryPersonStatus;
import com.wynk.test.entity.DeliveryPerson;

@Mapper
public interface IDeliveryPersonDao {

	@Insert("INSERT into delivery_person name,host,port,number,address,status) VALUES(#{name},#{host},#{port},#{number},{address},{status} ON DUPLICATE KEY UPDATE status= #{status})")
	public void save(DeliveryPerson deliveryPerson);

	@Select("SELECT `id`,`name`,`host`,`port`,`number`, `address`,`status` FROM `delivery_person` status = #{status} ")
	public List<DeliveryPerson> getAllIdleDeliveryPersons(@Param("status") DeliveryPersonStatus status);
}
