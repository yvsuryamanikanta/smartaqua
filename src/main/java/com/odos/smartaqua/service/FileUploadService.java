package com.odos.smartaqua.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.odos.smartaqua.dto.ResponseDTO;

public interface FileUploadService {

	ResponseEntity<ResponseDTO> uploadFile(MultipartFile file, String filetype);

	ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request);

}
