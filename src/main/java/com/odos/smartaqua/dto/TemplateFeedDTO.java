package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateFeedDTO {

	public Long templateid;

	public Long userid;

	public Long feedgroupid;

	public Long productcatgeoryid;

	public Long productid;

	public String productqty;

	public String priceperqty;

	public String groupname;

	public String name;

	public String productname;

}
