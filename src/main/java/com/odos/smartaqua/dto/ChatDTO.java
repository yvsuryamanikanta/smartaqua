package com.odos.smartaqua.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {

	public Long userid;

	public Long chatId;

	public String content;

	public String module;

	public String moduleLink;
	
	public String mediaType;

	public String mediaLink;

	public String createddate;
	
	public String modifieddate;

}
