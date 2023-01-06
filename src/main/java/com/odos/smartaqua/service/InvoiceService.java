package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.InvoiceDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface InvoiceService {

	ResponseEntity<ResponseDTO> save(InvoiceDTO invoiceDTO);
	
	ResponseEntity<ResponseDTO> findInvoicebyUser(Long userid);
	


}
