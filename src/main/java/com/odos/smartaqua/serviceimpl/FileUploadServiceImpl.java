package com.odos.smartaqua.serviceimpl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.UploadFileDTO;
import com.odos.smartaqua.service.FileStorageService;
import com.odos.smartaqua.service.FileUploadService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private FileStorageService fileStorageService;


	/*
	 * --------------------Upload Feed File------------
	 */

	public ResponseEntity<ResponseDTO> uploadFile(MultipartFile file, String filetype) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			String fileName = fileStorageService.storeFile(file);

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/files/download/").path(fileName).toUriString();

			UploadFileDTO uploadFileDTO = new UploadFileDTO(fileDownloadUri, filetype);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, uploadFileDTO,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * --------------------Download Feed File------------
	 */

	public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			contentType = null;
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
