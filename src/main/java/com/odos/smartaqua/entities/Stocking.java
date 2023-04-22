package com.odos.smartaqua.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stocking")
public class Stocking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long stockingid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "tankid")
	public Tank tank;
	
	@Column(nullable = true, updatable = true)
	public String ammonia;

	@Column(nullable = false, updatable = true)
	public String nitrite;
	
	@Column(nullable = false, updatable = true)
	public String alkalnity;
	
	@Column(nullable = false, updatable = true)
	public String hardness;

	@Column(nullable = true, updatable = true)
	public String iron;
	
	@Column(nullable = true, updatable = true)
	public String mineral;
	
	@Column(nullable = true, updatable = true)
	public String clorine;
	
	@Column(nullable = true, updatable = true)
	public String salnity;
	
	@Column(nullable = true, updatable = true)
	public String transparancy;
	
	@Column(nullable = true, updatable = true)
	public String watercolor;
	
	@Column(nullable = true, updatable = true)
	public String waterdepth;
	
	@Column(nullable = true, updatable = true)
	public String plsize;
	
	@Column(nullable = true, updatable = true)
	public String plpcrresult;
	
	@Column(nullable = true, updatable = true)
	public String plpackingdensity;
	
	@Column(nullable = true, updatable = true)
	public String plage;
	
	@Column(nullable = true, updatable = true)
	public String hepathopancreasCondition;
	
	@Column(nullable = true, updatable = true)
	public String avgnoofplPerBag;
	
	@Column(nullable = true, updatable = true)
	public String acclinitization;
	
	@Column(nullable = true, updatable = true)
	public String seedtrnsportationtime;
	
	@Column(nullable = true, updatable = true)
	public String vmodeoftransport;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;
	
}
