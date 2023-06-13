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
public class SoilObservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long soilobservationid;
	
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
	public String soiltype;
	
	@Column(nullable = false, updatable = true)
	public String phvalue;
	
	@Column(nullable = false, updatable = true)
	public String salinity;
	
	@Column(nullable = false, updatable = true)
	public String texture;
	
	@Column(nullable = false, updatable = true)
	public String sand;
	
	@Column(nullable = false, updatable = true)
	public String slit;
	
	@Column(nullable = false, updatable = true)
	public String clay;
	
	@Column(nullable = false, updatable = true)
	public String organiccarbon;
	
	@Column(nullable = false, updatable = true)
	public String organicmatter;
	
	@Column(nullable = false, updatable = true)
	public String nitrogen;
	
	@Column(nullable = false, updatable = true)
	public String phosphorous;
	
	@Column(nullable = false, updatable = true)
	public String pottasium;
	
	@Column(nullable = false, updatable = true)
	public String sulphur;
	
	@Column(nullable = false, updatable = true)
	public String ca;
	
	@Column(nullable = false, updatable = true)
	public String mg;
	
	@Column(nullable = false, updatable = true)
	public String iron;
	
	@Column(nullable = false, updatable = true)
	public String zinc;
	
	@Column(nullable = false, updatable = true)
	public String ammonia;
	
	@Column(nullable = false, updatable = true)
	public String microbiology;
	
	@Column(nullable = false, updatable = true)
	public String comments;
	
	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
