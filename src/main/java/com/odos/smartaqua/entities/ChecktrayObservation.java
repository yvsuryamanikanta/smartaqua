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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "checktrayobservation")
public class ChecktrayObservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long checktrayobsvid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "checktrayid")
	public Checktray checktray;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "tankid")
	public Tank tank;
	
	@Column(nullable = false, updatable = true)
	public String feedstatus;
	
	@Column(nullable = false, updatable = true)
	public String wastagecolor;
	
	@Column(nullable = false, updatable = true)
	public String mortalitytype;
	
	@Column(nullable = false, updatable = true)
	public String mortalitycount;
	
	@Column(nullable = false, updatable = true)
	public String potaciumdefeciency;
	
	@Column(nullable = false, updatable = true)
	public String magniciumdefeciency;
	
	@Column(nullable = false, updatable = true)
	public String calciumdefeciency;
	
	@Column(nullable = false, updatable = true)
	public String vibrieostatus;
	
	@Column(nullable = false, updatable = true)
	public String crampstatus;
	
	@Column(nullable = false, updatable = true)
	public String checktrayobsvdate;
	
	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
