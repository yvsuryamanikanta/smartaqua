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
public class GrowthObservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	public Long growthobsvid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "tankid")
	public Tank tank;
	
	@Column(nullable = false, updatable = true)
	public String count;
	
	@Column(nullable = false, updatable = true)
	public String growthobservationdate;
	
	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
