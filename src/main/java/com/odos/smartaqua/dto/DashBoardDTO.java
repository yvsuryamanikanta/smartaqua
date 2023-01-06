package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardDTO {

	private Long id;
	
	private Long tankId;
	
	private Long cultureid;

	private String access;
	
	private String groupname;
	
	private String date;
	

}
