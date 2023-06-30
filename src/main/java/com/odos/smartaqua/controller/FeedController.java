package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.FeedGroupDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.FeedGroupService;

@RequestMapping(value = "/api/feed")
@RestController
public class FeedController {

	@Autowired
	private FeedGroupService feedGroupService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveFeedGroupTemplate(@RequestBody FeedGroupDTO feedGroupDTO) {
		return feedGroupService.saveFeed(feedGroupDTO);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> getFeedGroupTemplates(@RequestBody FeedGroupDTO feedGroupDTO) {
		return feedGroupService.feedFeedListbyDate(feedGroupDTO.getUserID(), feedGroupDTO.getCultureid(),
				feedGroupDTO.getFeeddate(), feedGroupDTO.getType());
	}
}
