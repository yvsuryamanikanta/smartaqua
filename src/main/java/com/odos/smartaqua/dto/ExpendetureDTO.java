package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpendetureDTO {

	private Long expendsid;

	private Long tankid;
	
	private String amount;
	
	private String expendsdate;
	
	private String reason;
	
	private String createddate;
	
	private String modifieddate;
	

}
