package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	public Long userid;
	public Long roleid;
	public String uniqueid;
	public String username;
	public String password;
	public String usernumber;
	public String useremail;
	public String userlocation;
	public String userimage;
	public String createdby;
	public String notificationid;
	public String rolecode;

}
