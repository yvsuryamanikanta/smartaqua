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
public class Version {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	public Long versionid;

	@Column(nullable = false)
	public String versionname;

	@Column(nullable = false)
	public int versioncode;

	@Column(nullable = false)
	public String sourcetype;

	@Column(nullable = false)
	public String updatepath;

	@Column(nullable = false)
	public String updatemessage;

	@Column(nullable = false)
	public Boolean ismandatory;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

	
}
