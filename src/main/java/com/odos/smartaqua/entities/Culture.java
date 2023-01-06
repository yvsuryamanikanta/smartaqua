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
public class Culture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	public Long cultureid;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User user;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "tankid")
	public Tank tank;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade = CascadeType.MERGE)
	public Set<FeedGroup> feedgroup;

	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<ChecktrayTemplate> checktraytemplate;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<LabTemplate> labtemplate;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<ChecktrayGroup> checktraygroup;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<LabGroup> labgroup;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<FeedGroup> feedgroup;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<Treatment> treatment;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<Harvesting> harvesting;
	 */

	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<Checktray> checktray;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<TrayConverse> trayconverse;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<WorkConverse> workconverse;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<LabConverse> labconverse;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<GrowthConverse> growthconverse;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<HarvestGroup> harvestgroup;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade =
	 * CascadeType.MERGE) public Set<Expends> expends;
	 */

	@Column(nullable = false, updatable = true)
	public String culturename;

	@Column(nullable = false, updatable = true)
	public int culturenumber;

	@Column(nullable = true, updatable = true)
	public String cultureimage;

	@Column(nullable = true, updatable = true)
	public String culturestatus;

	@Column(nullable = true, updatable = true)
	public String cultureaccess;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;
}
