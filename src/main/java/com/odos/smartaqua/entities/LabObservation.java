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
public class LabObservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	public Long labobservationid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "tankid")
	public Tank tank;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User user;
	
	@Column(nullable = false, updatable = true)
	public String phvalue;
	
	@Column(nullable = false, updatable = true)
	public String salinity;
	
	@Column(nullable = false, updatable = true)
	public String co3;
	
	@Column(nullable = false, updatable = true)
	public String hco3;
	
	@Column(nullable = false, updatable = true)
	public String cahardness;
	
	@Column(nullable = false, updatable = true)
	public String mghardness;
	
	@Column(nullable = false, updatable = true)
	public String calcium;
	
	@Column(nullable = false, updatable = true)
	public String magnesium;
	
	@Column(nullable = false, updatable = true)
	public String potassium;
	
	@Column(nullable = false, updatable = true)
	public String sodium;
	
	@Column(nullable = false, updatable = true)
	public String iron;
	
	@Column(nullable = false, updatable = true)
	public String ionizedammonia;
	
	@Column(nullable = false, updatable = true)
	public String unionizedammonia;
	
	@Column(nullable = false, updatable = true)
	public String nitrate;
	
	@Column(nullable = false, updatable = true)
	public String hydrogensulphide;
	
	@Column(nullable = false, updatable = true)
	public String labdo;
	
	@Column(nullable = false, updatable = true)
	public String co2;
	
	@Column(nullable = false, updatable = true)
	public String greenalgae;
	
	@Column(nullable = false, updatable = true)
	public String diatom;
	
	@Column(nullable = false, updatable = true)
	public String bluegreenalgae;
	
	@Column(nullable = false, updatable = true)
	public String dinoflegellates;
	
	@Column(nullable = false, updatable = true)
	public String zooplankton;
	
	@Column(nullable = false, updatable = true)
	public String dafloc;
	
	@Column(nullable = false, updatable = true)
	public String vibriogreencolonies;
	
	@Column(nullable = false, updatable = true)
	public String vibrioyellowcolonies;
	
	@Column(nullable = false, updatable = true)
	public String labobsvdate;
	
	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
