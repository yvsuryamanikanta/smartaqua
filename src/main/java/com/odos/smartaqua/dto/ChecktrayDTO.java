package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChecktrayDTO {

	private Long checktrayid;

	private Long tankid;
	
	private String checktrayname;
	
	private String createddate;
	
	private String modifieddate;
	

}
