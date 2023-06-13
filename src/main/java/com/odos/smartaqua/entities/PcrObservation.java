package com.odos.smartaqua.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PcrObservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long pcrobservationid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "tankid")
	public Tank tank;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User user;
	
	@Column(nullable = false, updatable = true)
	public String culturecode;
	
	@Column(nullable = false, updatable = true)
	public String cultureloc;
	
	@Column(nullable = false, updatable = true)
	public String obsvdate;
	
	@Column(nullable = false, updatable = true)
	public String physicalActivity;
	
	@Column(nullable = false, updatable = true)
	public String meanBodyLeangth;
	
	@Column(nullable = false, updatable = true)
	public String dorsalSpinesCount;
	
	@Column(nullable = false, updatable = true)
	public String estimatedPlAge;
	
	@Column(nullable = false, updatable = true)
	public String coefficientOfSizeVariation;
	
	@Column(nullable = false, updatable = true)
	public String sampleSalinity;
	
	@Column(nullable = false, updatable = true)
	public String salinitySressSurvival;
	
	@Column(nullable = false, updatable = true)
	public String formalinSressSurvival;
	
	@Column(nullable = false, updatable = true)
	public String gillDevStatus;
	
	@Column(nullable = false, updatable = true)
	public String muscleGutRation;
	
	@Column(nullable = false, updatable = true)
	public String ectoparasiteattachments;
	
	@Column(nullable = false, updatable = true)
	public String endoParasite;
	
	@Column(nullable = false, updatable = true)
	public String necrosis;
	
	@Column(nullable = false, updatable = true)
	public String structuralDeformities;
	
	@Column(nullable = false, updatable = true)
	public String hepathopancreasLipid;
	
	@Column(nullable = false, updatable = true)
	public String mbvOcclusionBodies;
	
	@Column(nullable = false, updatable = true)
	public String hypherTropiedNucleiInHp;
	
	@Column(nullable = false, updatable = true)
	public String pcrResultWssv;
	
	@Column(nullable = false, updatable = true)
	public String wssvCtValueSeviority;
	
	@Column(nullable = false, updatable = true)
	public String pcrResultEhp;
	
	@Column(nullable = false, updatable = true)
	public String ehpCtValueSeviority;
	
	@Column(nullable = false, updatable = true)
	public String pcrResultIhhnv;
	
	@Column(nullable = false, updatable = true)
	public String ihhnvCtValueSeviority;
	
	@Column(nullable = false, updatable = true)
	public String pcrResultEms;
	
	@Column(nullable = false, updatable = true)
	public String emsCtValueSeviority;
	
	@Column(nullable = false, updatable = true)
	public String comments;
	
	
	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
