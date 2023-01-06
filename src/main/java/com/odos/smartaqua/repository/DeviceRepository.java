package com.odos.smartaqua.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Device;



public interface DeviceRepository extends JpaRepository<Device, Long>{
	
	@Query(nativeQuery = true, value = "select notificationid from device where uniqueid =:uniqueid")
	String findByuniqueID(@Param("uniqueid") String uniqueid);
	
	@Query(nativeQuery = true, value = "select * from device where uniqueid =:uniqueid")
	Device findDeviceByuniqueID(@Param("uniqueid") String uniqueid);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE device d set d.notificationid =:notificationid where d.uniqueid = :uniqueid")
	int updateByUniqueID(@Param("uniqueid") String uniqueid, @Param("notificationid") String notificationid);
}
