package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ChatDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.ChatService;


@RequestMapping(value = "/api/chat")
@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> resendOTP(@RequestBody ChatDTO chatDTO) {
		return chatService.save(chatDTO);
	}

	@RequestMapping(value = "/list/{userid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> verifyOTP(@PathVariable("userid") String userid) {
		return chatService.findTankByUser(userid);
	}

}
