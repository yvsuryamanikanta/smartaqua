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
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long roleid;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.MERGE)
	public Set<User> user;

	@Column(nullable = true, updatable = true, unique = true)
	public String rolename;

	@Column(nullable = true, updatable = true, unique = true)
	public String rolecode;

	@Column(nullable = false, updatable = true)
	public String cretaedby;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;


}
