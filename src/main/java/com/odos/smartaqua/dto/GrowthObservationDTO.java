package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrowthObservationDTO {

	private Long growthobsvid;

	private Long tankid;
	
	private String count;
	
	private String growthobservationdate;
	
	private String createddate;
	
	private String modifieddate;
	

}
