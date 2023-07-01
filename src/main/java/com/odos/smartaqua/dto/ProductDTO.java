package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	public Long productid;
	
	public Long userid;

	public Long productcatgeoryid;

	public Long quantitycategoryid;

	public Long brandid;

	public String productname;

	public String costperqty;
	
	public String quantity;

}
