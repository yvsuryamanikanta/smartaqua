package com.odos.smartaqua.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query(nativeQuery = true, value = "select * from invoice where userid =:userid")
	List<Invoice> getInvoiceByUser(@Param("userid") Long userid);

	
}
