package com.odos.smartaqua.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query(nativeQuery = true, value = "select * from stock")
	List<Stock> getProductist();

	@Query(nativeQuery = true, value = "select * from stock s1"
			+ " inner join (select productid,max(createddate) as createddate from stock GROUP BY productid ORDER by productid DESC) s2"
			+ " on s2.productid=s1.productid and s1.createddate=s2.createddate where s1.userid =:userid")
	List<Stock> getStockByUser(@Param("userid") Long userid);

	@Query(nativeQuery = true, value = "SELECT availablestock FROM stock where productid =:productid and userid =:userid ORDER BY stockid DESC LIMIT 1 ")
	String findStockByProduct(@Param("productid") Long productid, @Param("userid") Long userid);

	@Query(nativeQuery = true, value = "SELECT priceperquantity FROM stock where productid =:productid and userid =:userid ORDER BY stockid DESC LIMIT 1 ")
	String findPriceByProduct(@Param("productid") Long productid, @Param("userid") Long userid);

	@Query(nativeQuery = true, value = "select * from stock where userid =:userid and productid =:productid")
	List<Stock> getStockByProduct(@Param("userid") Long userid, @Param("productid") Long productid);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE stock SET availablestock =:availablestock where productid =:productid and userid =:userid")
	void updateProduct(@Param("productid") Long productid, @Param("availablestock") String availablestock,
			@Param("userid") Long userid);

}
