package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PCRObservationDTO {

	private Long pcrobservationid;

	private Long tankid;

	private Long userid;

	private String culturecode;

	private String cultureloc;

	private String obsvdate;

	private String physicalActivity;

	private String meanBodyLeangth;

	private String dorsalSpinesCount;

	private String estimatedPlAge;

	private String coefficientOfSizeVariation;

	private String sampleSalinity;

	private String salinitySressSurvival;

	private String formalinSressSurvival;

	private String gillDevStatus;

	private String muscleGutRation;

	private String ectoparasiteattachments;

	private String endoParasite;

	private String necrosis;

	private String structuralDeformities;

	private String hepathopancreasLipid;

	private String mbvOcclusionBodies;

	private String hypherTropiedNucleiInHp;

	private String pcrResultWssv;

	private String wssvCtValueSeviority;

	private String pcrResultEhp;

	private String ehpCtValueSeviority;

	private String pcrResultIhhnv;

	private String ihhnvCtValueSeviority;

	private String pcrResultEms;

	private String emsCtValueSeviority;

	private String comments;

	private String createddate;

	private String modifieddate;

	private boolean ismandatory;

}
