package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockingDTO {

	private Long stockingid;
	
	private Long tankid;
	
	private String ammonia;

	private String nitrite;

	private String alkalnity;

	private String hardness;

	private String iron;

	private String mineral;

	private String clorine;

	private String salnity;

	private String transparancy;

	private String watercolor;

	private String waterdepth;

	private String plsize;

	private String plpcrresult;

	private String plpackingdensity;

	private String plage;

	private String hepathopancreasCondition;

	private String avgnoofplPerBag;

	private String acclinitization;

	private String seedtrnsportationtime;

	private String vmodeoftransport;

	private String createddate;

	private String modifieddate;

	private boolean isactive;
}
