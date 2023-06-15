package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalbservationDTO {

	private Long pcrobservationid;

	private Long tankid;

	private Long userid;

	private String culturecode;

	private String cultureloc;

	private String obsvdate;

	private String greencolony;

	private String yellowcolony;

	private String comments;

	private String createddate;

	private String modifieddate;

	private boolean ismandatory;

}
