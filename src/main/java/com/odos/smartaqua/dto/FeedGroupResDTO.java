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
public class FeedGroupResDTO {

	private Long templateID;

	private Long userID;

	private String groupname;
	
	private String feeddate;

	private List<FeedTemplateResponseDTO> feedProducts;

	private List<FeedTemplateResponseDTO> suppliments;

}
