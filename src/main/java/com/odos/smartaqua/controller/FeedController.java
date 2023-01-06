package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/template/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveFeedGroupTemplate(@RequestBody FeedGroupDTO feedGroupDTO) {
		return feedGroupService.saveFeedTemplate(feedGroupDTO);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> getFeedGroupTemplates(@RequestBody FeedGroupDTO feedGroupDTO) {
		return feedGroupService.feedList(feedGroupDTO.getUserID(),feedGroupDTO.getCultureid(),feedGroupDTO.getFeeddate());
	}

	@RequestMapping(value = "/template/list/{userid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> feedGroupTemplateList(@PathVariable("userid") Long userid) {
		return feedGroupService.findAllTemplates(userid);
	}

	@RequestMapping(value = "/template/list/{userid}/{groupid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> findtemplateGroups(@PathVariable("userid") Long userid,
			@PathVariable("groupid") Long groupid) {
		return feedGroupService.findtemplateGroups(userid, groupid);
	}

	@RequestMapping(value = "/groups/list/{userid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> feedGroupList(@PathVariable("userid") Long userid) {
		return feedGroupService.findGroups(userid);
	}
}
