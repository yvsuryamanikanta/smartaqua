package com.odos.smartaqua.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedGroupDTO {

	private Long feedgroupid;

	private Long templateID;

	private Long userID;
	
	private Long cultureid;

	private String access;
	
	private String groupname;
	
	private String feeddate;
	
	private String feeddateandtime;
	
	private String comment;

	private List<FeedTemplateDTO> feedProducts;

	private List<FeedTemplateDTO> suppliments;

}
