package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoilObservationDTO {

	private Long soilobservationid;

	private Long tankid;
	
	private Long userid;
	
	private String culturecode;
	
	private String cultureloc;
	
	private String obsvdate;
	
	private String soiltype;
	
	private String phvalue;
	
	private String salinity;
	
	private String texture;
	
	private String sand;
	
	private String slit;
	
	private String clay;
	
	private String organiccarbon;
	
	private String organicmatter;
	
	private String nitrogen;
	
	private String phosphorous;
	
	private String pottasium;
	
	private String sulphur;
	
	private String ca;
	
	private String mg;
	
	private String iron;
	
	private String zinc;
	
	private String ammonia;
	
	private String microbiology;
	
	private String comments;
	
	private String createddate;

	private String modifieddate;

	private boolean isactive;
	

}
