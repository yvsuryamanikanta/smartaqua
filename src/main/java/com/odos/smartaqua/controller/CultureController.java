package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.CultureDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.CultureService;

@RequestMapping(value = "/api/culture")
@RestController
public class CultureController {

	@Autowired
	private CultureService cultureService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveCulture(@RequestBody CultureDTO culturedto) {
		return cultureService.save(culturedto);
	}

	@RequestMapping(value = "/list/{userid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getAllCultures(@PathVariable("userid") Long userid) {
		return cultureService.findAllCultures(userid);
	}

	@RequestMapping(value = "/list/{userid}/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCultureByTank(@PathVariable("userid") Long userid,
			@PathVariable("tankid") Long tankid) {
		return cultureService.findCultureByTank(userid, tankid);
	}

	@RequestMapping(value = "/feedboy/list/{cultureaccess}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getFeedBoyCultures(@PathVariable("cultureaccess") String cultureaccess) {
		return cultureService.findCultureByAccess(cultureaccess);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> updateCultureAccess(@RequestBody CultureDTO culturedto) {
		return cultureService.updateCultureAccess(culturedto.getUserid(), culturedto.getCultureid(),
				culturedto.getCultureaccess());
	}

}
