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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	public Long productid;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productcatgeoryid")
	public ProductCategory productcategory;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "quantitycategoryid")
	public QuantityCategories quantitycategories;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "brandid")
	public Brand brand;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.MERGE)
	public Set<FeedTemplate> feedtemplate;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.MERGE)
	public Set<Stock> stock;

	@Column(nullable = false, unique = true)
	public String productname;

	@Column(nullable = false)
	public String costperqty;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
