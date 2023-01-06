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
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	public Long stockid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "productid")
	public Product product;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "quantitycategoryid")
	public QuantityCategories quantitycategories;
	
	@Column(nullable = true, updatable = true, unique = false)
	public String newstock;
	
	@Column(nullable = true, updatable = true, unique = false)
	public String oldstock;
	
	@Column(nullable = true, updatable = true, unique = false)
	public String availablestock;

	@Column(nullable = false, updatable = true, unique = false)
	public String mrp;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;
}
