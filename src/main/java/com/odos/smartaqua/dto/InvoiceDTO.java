package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

	public Long invoiceid;

	public Long userid;
	
	public String store;
	
	public String purchasedate;
		
	public String amount;

	public String discount;

	public String path;
	
	public String paymenttype;

	public String createddate;
		
}
