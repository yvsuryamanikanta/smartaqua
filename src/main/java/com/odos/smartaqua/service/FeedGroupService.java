package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.FeedGroupDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface FeedGroupService {

	ResponseEntity<ResponseDTO> saveFeed(FeedGroupDTO feedGroupDTO);

	ResponseEntity<ResponseDTO> feedFeedListbyDate(Long userid, Long cultureId, String feedDate, String type);

}
