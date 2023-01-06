package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabObservationDTO {

	private Long labobservationid;

	private Long tankid;
	
	private Long userid;
	
	private String phvalue;
	
	private String salinity;
	
	private String co3;
	
	private String hco3;
	
	private String cahardness;
	
	private String mghardness;
	
	private String calcium;
	
	private String magnesium;
	
	private String potassium;
	
	private String sodium;
	
	private String iron;
	
	private String ionizedammonia;
	
	private String unionizedammonia;
	
	private String nitrate;
	
	private String hydrogensulphide;
	
	private String labdo;
	
	private String co2;
	
	private String greenalgae;
	
	private String diatom;
	
	private String bluegreenalgae;
	
	private String dinoflegellates;
	
	private String zooplankton;
	
	private String dafloc;
	
	private String vibriogreencolonies;
	
	private String vibrioyellowcolonies;
	
	private String labobsvdate;
	
	private String createddate;
	
	private String modifieddate;
	

}
