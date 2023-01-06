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
public class Tank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long tankid;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User user;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tank", cascade = CascadeType.MERGE)
	public Set<Culture> culture;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tank", cascade = CascadeType.MERGE)
	public Set<Checktray> checktray;

	@Column(nullable = false, updatable = true)
	public String tankname;

	@Column(nullable = true, updatable = true)
	public String tanklocation;

	@Column(nullable = true, updatable = true)
	public String tankimage;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
