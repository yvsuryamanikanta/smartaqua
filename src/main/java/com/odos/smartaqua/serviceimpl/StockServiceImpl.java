package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.StockDTO;
import com.odos.smartaqua.entities.Stock;
import com.odos.smartaqua.repository.ProductRepository;
import com.odos.smartaqua.repository.QtyCategoryRepository;
import com.odos.smartaqua.repository.StockRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.StockService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private QtyCategoryRepository quantityTypeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * -----------------SAVE STOCK -------------
	 */
	public ResponseEntity<ResponseDTO> save(StockDTO stockdto) {
		ResponseDTO responseDTO = new ResponseDTO();
		String availablestock = stockRepository.findStockByProduct(stockdto.getProductid(), stockdto.getUserid());
		if (availablestock != null) {
			Stock stock = new Stock();
			BeanUtils.copyProperties(stockdto, stock);
			stock.setQuantitycategories(quantityTypeRepository.findById(stockdto.getQuantitycategoryid()).get());
			stock.setProduct(productRepository.findById(stockdto.getProductid()).get());
			stock.setUser(userRepository.findById(stockdto.getUserid()).get());
			stock.setOldstock(availablestock);
			int stock_available = Integer.parseInt(availablestock);
			int new_availablestock = Integer.parseInt(stockdto.getNewstock());
			int new_stock = stock_available + new_availablestock;
			stock.setAvailablestock(String.valueOf(new_stock));
			try {
				stockRepository.save(stock);
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
						AquaConstants.saved);
			} catch (Exception e) {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
						e.getMessage());
			}
		} else {
			Stock stock = new Stock();
			BeanUtils.copyProperties(stockdto, stock);
			stock.setQuantitycategories(quantityTypeRepository.findById(stockdto.getQuantitycategoryid()).get());
			stock.setProduct(productRepository.findById(stockdto.getProductid()).get());
			stock.setUser(userRepository.findById(stockdto.getUserid()).get());
			stock.setOldstock("0");
			stock.setAvailablestock(stockdto.getNewstock());
			try {
				stockRepository.save(stock);
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
						AquaConstants.saved);
			} catch (Exception e) {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
						e.getMessage());
			}
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------FIND STOCK -------------
	 */
	public ResponseEntity<ResponseDTO> findStock() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<StockDTO> stockDtoList = new ArrayList<StockDTO>();
			List<Stock> productsList = stockRepository.getProductist();
			for (int i = 0; i < productsList.size(); i++) {
				Stock stock = (Stock) productsList.get(i);
				StockDTO stockdto = new StockDTO(stock.getStockid(), stock.getUser().getUserid(),stock.getProduct().getProductid(),
						stock.getProduct().getProductname(),
						stock.getProduct().getProductcategory().getProductcatgeoryid(),
						stock.getQuantitycategories().getQuantitycategoryid(), stock.getNewstock(), stock.getOldstock(),
						stock.getAvailablestock(), stock.getMrp(), stock.getPath(), "" + stock.getCreateddate(),
						stock.getProduct().getProductcategory().getCode(),
						stock.getQuantitycategories().getQtycategorycode());
				stockDtoList.add(stockdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, stockDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					AquaConstants.failed+e, AquaConstants.failed);
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------FIND STOCK BY USER -------------
	 */
	public ResponseEntity<ResponseDTO> findStockbyUser(Long userid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<StockDTO> stockDtoList = new ArrayList<StockDTO>();
			List<Stock> productsList = stockRepository.getStockByUser(userid);
			for (int i = 0; i < productsList.size(); i++) {
				Stock stock = (Stock) productsList.get(i);
				StockDTO stockdto = new StockDTO(stock.getStockid(), stock.getUser().getUserid(), stock.getProduct().getProductid(),
						stock.getProduct().getProductname(),
						stock.getProduct().getProductcategory().getProductcatgeoryid(),
						stock.getQuantitycategories().getQuantitycategoryid(), stock.getNewstock(), stock.getOldstock(),
						stock.getAvailablestock(), stock.getMrp(), stock.getPath(), "" + stock.getCreateddate(),
						stock.getProduct().getProductcategory().getCode(),
						stock.getQuantitycategories().getQtycategorycode());
				stockDtoList.add(stockdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, stockDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------FIND STOCK PRODUCT ID -------------
	 */
	public ResponseEntity<ResponseDTO> findStockbyProduct(Long userid, Long productid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<StockDTO> stockDtoList = new ArrayList<StockDTO>();
			List<Stock> productsList = stockRepository.getStockByProduct(userid, productid);
			for (int i = 0; i < productsList.size(); i++) {
				Stock stock = (Stock) productsList.get(i);
				StockDTO stockdto = new StockDTO(stock.getStockid(), stock.getUser().getUserid(),stock.getProduct().getProductid(),
						stock.getProduct().getProductname(),
						stock.getProduct().getProductcategory().getProductcatgeoryid(),
						stock.getQuantitycategories().getQuantitycategoryid(), stock.getNewstock(), stock.getOldstock(),
						stock.getAvailablestock(), stock.getMrp(), stock.getPath(), "" + stock.getCreateddate(),
						stock.getProduct().getProductcategory().getCode(),
						stock.getQuantitycategories().getQtycategorycode());
				stockDtoList.add(stockdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, stockDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
