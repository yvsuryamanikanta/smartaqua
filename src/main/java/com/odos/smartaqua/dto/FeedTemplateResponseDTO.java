package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedTemplateResponseDTO {

	public Long productID;
	public Long productcatgeoryID;
	public Long quantitycategoryid;
	public String productName;
	public String priceperqty;
	public String productqty;
	public String quantity;
	public String comments;
}
