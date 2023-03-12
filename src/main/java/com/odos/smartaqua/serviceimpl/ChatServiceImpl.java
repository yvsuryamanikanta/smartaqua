package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ChatDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TankDTO;
import com.odos.smartaqua.entities.Chat;
import com.odos.smartaqua.entities.Tank;
import com.odos.smartaqua.repository.ChatRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.ChatService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * ----------------SAVE CHAT --------------------
	 */

	public ResponseEntity<ResponseDTO> save(ChatDTO chatDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Chat chat = new Chat();
		BeanUtils.copyProperties(chatDTO, chat);
		chat.setUser(userRepository.findById(chatDTO.getUserid()).get());
		try {
			chatRepository.save(chat);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
					AquaConstants.success, AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------FIND CHAT BY USER ID--------------------
	 */
	public ResponseEntity<ResponseDTO> findTankByUser(String userid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ChatDTO> chatDtoList = new ArrayList<ChatDTO>();
			List<Chat> chatList = chatRepository.findListByUser(userid);
			if (chatList != null) {
				for (int i = 0; i < chatList.size(); i++) {
					Chat chat = (Chat) chatList.get(i);
					ChatDTO responseChat = new ChatDTO();
					BeanUtils.copyProperties(chat, responseChat);
					responseChat.setUserid(chat.getUser().getUserid());
					responseChat.setCreateddate(""+chat.getCreateddate());
					responseChat.setModifieddate(""+chat.getModifieddate());
					chatDtoList.add(responseChat);
				}
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, chatDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	};

}
