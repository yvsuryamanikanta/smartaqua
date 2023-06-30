package com.odos.smartaqua.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long bannerid;
	
	@Column(nullable = true, updatable = true, unique = true)
	public String bannertitle;

	@Column(nullable = true, updatable = true, unique = true)
	public String bannerimage;
	
	@Column(nullable = true, updatable = true, unique = true)
	public String bannertype;
	
	@Column(nullable = true, updatable = true, unique = true)
	public String bannerlink;

	@Column(nullable = false, updatable = true)
	public String cretaedby;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;


}
