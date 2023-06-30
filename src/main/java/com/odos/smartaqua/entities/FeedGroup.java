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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedgroup")
public class FeedGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long feedgroupid;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User user;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "cultureid")
	public Culture culture;
	
	@Column(nullable = true, updatable = true)
	public String access;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "feedgroup", cascade = CascadeType.MERGE)
	public Set<FeedTemplate> feedtemplate;

	@Column(nullable = false, updatable = true)
	public String groupname;
	
	@Column(nullable = false, updatable = true)
	public String feeddate;
	
	@Column(nullable = false, updatable = true)
	public String feeddateandtime;
	
	@Column(nullable = false, updatable = true)
	public String comment;
	
	@Column(nullable = false, updatable = true)
	public String type;

	@Column(nullable = true, updatable = true)
	public String groupimage;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;
	
}
