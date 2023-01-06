package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChecktrayObservationDTO {

	private Long checktrayobsvid;

	private Long checktrayid;
	
	private Long tankid;
	
	private String feedstatus;
	
	private String wastagecolor;
	
	private String mortalitytype;
	
	private String mortalitycount;
	
	private String potaciumdefeciency;
	
	private String magniciumdefeciency;
	
	private String calciumdefeciency;
	
	private String vibrieostatus;
	
	private String crampstatus;
	
	private String checktrayobsvdate;
	
	private String createddate;
	
	private String modifieddate;
	

}
