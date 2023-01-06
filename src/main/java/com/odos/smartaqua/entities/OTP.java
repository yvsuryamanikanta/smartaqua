package com.odos.smartaqua.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "otp")
public class OTP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long otpid;

	@Column(nullable = false, updatable = true)
	public String usernumber;

	@Column(nullable = false, updatable = true)
	public String otpcode;

	@Column(nullable = false, updatable = true)
	public int daycount;

	@CreationTimestamp
	private Date createddate;

	@UpdateTimestamp
	private Date modifieddate;

	@Column(columnDefinition = "boolean default true")
	public Boolean isactive = true;

}
