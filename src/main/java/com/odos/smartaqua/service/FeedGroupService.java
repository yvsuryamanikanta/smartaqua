package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.FeedGroupDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface FeedGroupService {

	ResponseEntity<ResponseDTO> saveFeedTemplate(FeedGroupDTO feedGroupDTO);

	ResponseEntity<ResponseDTO> findAllTemplates(Long userid);

	ResponseEntity<ResponseDTO> findGroups(Long userid);

	ResponseEntity<ResponseDTO> findtemplateGroups(Long userid, Long groupid);
	
	ResponseEntity<ResponseDTO> feedList(Long userid, Long cultureId,String feedDate);

}
