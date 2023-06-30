package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

	public Long stockid;

	public Long userid;
	
	public Long productid;
	
	public String productname;
	
	public Long productcategoryid;

	public Long quantitycategoryid;
	
	public String newstock;

	public String oldstock;
	
	public String availablestock;

	public String actualprice;
	
	public String purchaseprice;
	
	public String discount;
	
	public String path;
	
	public String createddate;
	
	public String productcode;
	
	public String quantityname;
}
