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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long userid;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	public Roles roles;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "uniqueID")
	public Device device;
		
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Tank> tank;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Product> product;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Culture> culture;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Stock> stock;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Invoice> invoice;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<FeedGroup> feedgroup;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<FeedTemplate> feedtemplate;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<AnimalObservation> animalObservations;
	/*
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<TemplateGroup> templategroup;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<ChecktrayGroup> checktraygroup;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<ChecktrayTemplate> checktraytemplate;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<LabGroup> labgroup;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<LabTemplate> labtemplate;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<WorkConversation> workconversation;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<FeedConversation> feedconversation;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<LabConversation> labconversation;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<ChecktrayConversation> checktrayconversation;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<TreatmentConversation> treatmentconversation;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Harvesting> harvesting;
	


	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Conversation> conversation;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<FeedConversation> feedconversation;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Checktray> checktray;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<ChecktrayTemplates> checktraytemplates;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<TrayConverse> trayconverse;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<TrayTemplate> traytemplate;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<LabConverse> labconverse;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<WorkConverse> workconverse;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<GrowthConverse> growthconverse;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<HarvestGroup> harvestgroup;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<Expends> expends;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<TreatmentTemplate> treatmenttemplate;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<TreatmentConversation> treatmentconversation;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<LabTest> labtest;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<LabConversation> labconversation;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<ChecktrayConversation> checktrayconversation;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<WorkConversation> workconversation;
	
	*/
	
	@Column(nullable = true, updatable = true)
	public String username;

	@Column(nullable = false, updatable = true)
	public String usernumber;

	@Column(nullable = true, updatable = true)
	public String useremail;

	@Column(nullable = false, updatable = true)
	public String password;

	@Column(nullable = true, updatable = true)
	public String userlocation;

	@Column(nullable = true, updatable = true)
	public String userimage;

	@Column(nullable = false, updatable = true)
	public String createdby;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default false")
	public Boolean isactive = false;

}
