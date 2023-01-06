package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.InvoiceDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Invoice;
import com.odos.smartaqua.repository.InvoiceRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.InvoiceService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE INVOICE -------------
	 */
	public ResponseEntity<ResponseDTO> save(InvoiceDTO invoiceDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Invoice invoice = new Invoice();
		BeanUtils.copyProperties(invoiceDTO, invoice);
		invoice.setUser(userRepository.findById(invoiceDTO.getUserid()).get());
		try {
			invoiceRepository.save(invoice);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.saved);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					e.getMessage());
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}


	/*
	 * -----------------FIND INVOICE BY USER -------------
	 */

	@Override
	public ResponseEntity<ResponseDTO> findInvoicebyUser(Long userid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
			List<Invoice> productsList = invoiceRepository.getInvoiceByUser(userid);
			for (int i = 0; i < productsList.size(); i++) {
				Invoice invoice = (Invoice) productsList.get(i);
				InvoiceDTO invoiceDTO = new InvoiceDTO(invoice.getInvoiceid(), invoice.getUser().getUserid(),invoice.getStore(),
						invoice.getPurchasedate(),invoice.getAmount(),
						invoice.getDiscount(),invoice.getPath(), invoice.getPaymenttype(), "" + invoice.getCreateddate());
				invoiceDTOList.add(invoiceDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, invoiceDTOList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}


}
