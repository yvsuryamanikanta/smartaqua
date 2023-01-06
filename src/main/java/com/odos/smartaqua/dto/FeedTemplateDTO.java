package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedTemplateDTO {

	public Long productID;
	public Long productcatgeoryID;
	public Long quantitycategoryid;
	public String productqty;
	public String priceperqty;
	public String availablestock;
}
