package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.StockDTO;
import com.odos.smartaqua.service.StockService;

@RequestMapping(value = "/api/stock")
@RestController
public class StockController {

	@Autowired
	private StockService stockService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveStock(@RequestBody StockDTO stockdto) {
		return stockService.save(stockdto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getStockList() {
		return stockService.findStock();
	}

	@RequestMapping(value = "/list/{userid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getStockByUser(@PathVariable("userid") Long userid) {
		return stockService.findStockbyUser(userid);
	}

	@RequestMapping(value = "/list/{userid}/{productid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getStockByProduct(@PathVariable("userid") Long userid,
			@PathVariable("productid") Long productid) {
		return stockService.findStockbyProduct(userid, productid);
	}

}
