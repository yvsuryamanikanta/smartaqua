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
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long deviceid;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "device", cascade = CascadeType.MERGE)
	public Set<User> user;

	@Column(nullable = true)
	private String devicename;

	@Column(nullable = false)
	private String deviceversion;

	@Column(nullable = false, unique = true)
	private String uniqueID;

	@Column(nullable = false, updatable = true, unique = true)
	private String notificationid;

	@Column(nullable = false)
	private String devicetype;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	private Boolean isactive = true;

}
