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
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	public Long invoiceid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User user;
	
	@Column(nullable = true, updatable = true, unique = false)
	public String store;
	
	@Column(nullable = true, updatable = true, unique = false)
	public String purchasedate;
	
	@Column(nullable = false, updatable = true, unique = false)
	public String amount;
	
	@Column(nullable = false, updatable = true, unique = false)
	public String discount;
	
	@Column(nullable = false, updatable = true, unique = false)
	public String path;
	
	@Column(nullable = true, updatable = true, unique = false)
	public String paymenttype;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;
}
