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
@Table(name = "preparation")
public class Preparation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long preparationid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "tankid")
	public Tank tank;
	
	@Column(nullable = true, updatable = true)
	public String previousdecease;

	@Column(nullable = false, updatable = true)
	public String recordkeeping;
	
	@Column(nullable = false, updatable = true)
	public String drying;
	
	@Column(nullable = false, updatable = true)
	public String biosecurity;

	@Column(nullable = true, updatable = true)
	public String scrapping;
	
	@Column(nullable = true, updatable = true)
	public String ploughing;
	
	@Column(nullable = true, updatable = true)
	public String liming;
	
	@Column(nullable = true, updatable = true)
	public String soilcheck;
	
	@Column(nullable = true, updatable = true)
	public String fillingwatertype;
	
	@Column(nullable = true, updatable = true)
	public String watersource;
	
	@Column(nullable = true, updatable = true)
	public String pondtype;
	
	@Column(nullable = true, updatable = true)
	public String filteration;
	
	@Column(nullable = true, updatable = true)
	public String bleaching;
	
	@Column(nullable = true, updatable = true)
	public String minerals;
	
	@Column(nullable = true, updatable = true)
	public String probiotics;
	
	@Column(nullable = true, updatable = true)
	public String fertilization;
	
	@Column(nullable = true, updatable = true)
	public String ehp;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;
	
}
