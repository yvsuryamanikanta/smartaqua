package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreparationDTO {

	private Long preparationid;

	private Long templateID;

	private Long tankid;
	
	private String previousdecease;
	
	private String recordkeeping;
	
	private String drying;
	
	private String biosecurity;

	private String scrapping;
	
	private String ploughing;
	
	private String liming;
	
	private String soilcheck;
	
	private String fillingwatertype;
	
	private String watersource;
	
	private String pondtype;
	
	private String filteration;
	
	private String bleaching;
	
	private String minerals;
	
	private String probiotics;
	
	private String fertilization;
	
	private String ehp;

	private String createddate;
	
	private String modifieddate;
	
	private boolean isactive;

}
