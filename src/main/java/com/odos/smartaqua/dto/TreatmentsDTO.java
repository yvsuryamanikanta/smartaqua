package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentsDTO {

	private Long treatmentsid;

	private Long tankid;
	
	private String decease;
	
	private String solution1;
	
	private String solution2;
	
	private String solution3;
	
	private String traetmentdate;
	
	private String createddate;
	
	private String modifieddate;
	

}
