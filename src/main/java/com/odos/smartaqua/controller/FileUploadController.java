package com.odos.smartaqua.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.FileUploadService;


@RequestMapping(value = "/api/files")
@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> uploadFile(@RequestParam("filetype") String filetype,
			@RequestParam("file") MultipartFile file) {

		return fileUploadService.uploadFile(file, filetype);
	}

	@RequestMapping(value = "/download/{fileName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		return fileUploadService.downloadFile(fileName, request);
	}

}
