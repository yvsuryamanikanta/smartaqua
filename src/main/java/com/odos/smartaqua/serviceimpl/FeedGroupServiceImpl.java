package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.FeedGroupDTO;
import com.odos.smartaqua.dto.FeedGroupResDTO;
import com.odos.smartaqua.dto.FeedTemplateDTO;
import com.odos.smartaqua.dto.FeedTemplateResponseDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TemplateFeedDTO;
import com.odos.smartaqua.entities.FeedGroup;
import com.odos.smartaqua.entities.FeedTemplate;
import com.odos.smartaqua.repository.CultureRepository;
import com.odos.smartaqua.repository.FeedGroupRepository;
import com.odos.smartaqua.repository.FeedTemplateRepository;
import com.odos.smartaqua.repository.ProductCategoryRepository;
import com.odos.smartaqua.repository.ProductRepository;
import com.odos.smartaqua.repository.QtyCategoryRepository;
import com.odos.smartaqua.repository.StockRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.FeedGroupService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class FeedGroupServiceImpl implements FeedGroupService {

	@Autowired
	private FeedGroupRepository feedGroupRepository;

	@Autowired
	private FeedTemplateRepository feedTemplateRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private QtyCategoryRepository qtycategoryRepository;

	@Autowired
	private CultureRepository cultureRepository;

	@Autowired
	private StockRepository stockRepository;

	/*
	 * SAVE FEED TEMPLATE
	 */

	public ResponseEntity<ResponseDTO> saveFeedTemplate(FeedGroupDTO feedGroupDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<FeedTemplateDTO> supplimentsList = feedGroupDTO.getSuppliments();
			List<FeedTemplateDTO> feedProductsList = feedGroupDTO.getFeedProducts();
			FeedGroup feedgroup = new FeedGroup();
			BeanUtils.copyProperties(feedGroupDTO, feedgroup);
			feedgroup.setUser(userRepository.findById(feedGroupDTO.getUserID()).get());
			feedgroup.setCulture(cultureRepository.findById(feedGroupDTO.getCultureid()).get());
			FeedGroup feedgroupData = feedGroupRepository.save(feedgroup);
			Long uID = feedGroupDTO.getUserID();
			Long feedGroupID = feedgroupData.getFeedgroupid();
			for (int i = 0; i < feedProductsList.size(); i++) {
				FeedTemplate feedTemplate = new FeedTemplate();
				FeedTemplateDTO feedtemplatedto = (FeedTemplateDTO) feedProductsList.get(i);
				feedTemplate.setProductqty(feedtemplatedto.getProductqty());
				feedTemplate.setPriceperqty(feedtemplatedto.getPriceperqty());
				feedTemplate.setUser(userRepository.findById(uID).get());
				feedTemplate
						.setQuantitycategories(qtycategoryRepository.findById(feedtemplatedto.getQuantitycategoryid()).get());
				feedTemplate
						.setProductcategory(productCategoryRepository.findById(feedtemplatedto.getProductcatgeoryID()).get());
				feedTemplate.setProduct(productRepository.findById(feedtemplatedto.getProductID()).get());
				feedTemplate.setFeedgroup(feedGroupRepository.findById(feedGroupID).get());
				feedTemplateRepository.save(feedTemplate);
				stockRepository.updateProduct(feedtemplatedto.getProductID(), feedtemplatedto.getAvailablestock(),
						feedGroupDTO.getUserID());
			}
			for (int j = 0; j < supplimentsList.size(); j++) {
				FeedTemplate feedTemplate = new FeedTemplate();
				FeedTemplateDTO supplimentsdto = (FeedTemplateDTO) supplimentsList.get(j);
				feedTemplate.setProductqty(supplimentsdto.getProductqty());
				feedTemplate.setPriceperqty(supplimentsdto.getPriceperqty());
				feedTemplate.setUser(userRepository.findById(uID).get());
				feedTemplate
						.setQuantitycategories(qtycategoryRepository.findById(supplimentsdto.getQuantitycategoryid()).get());
				feedTemplate
						.setProductcategory(productCategoryRepository.findById(supplimentsdto.getProductcatgeoryID()).get());
				feedTemplate.setProduct(productRepository.findById(supplimentsdto.getProductID()).get());
				feedTemplate.setFeedgroup(feedGroupRepository.findById(feedGroupID).get());
				feedTemplateRepository.save(feedTemplate);
				stockRepository.updateProduct(supplimentsdto.getProductID(), supplimentsdto.getAvailablestock(),
						feedGroupDTO.getUserID());
			}
			FeedGroupDTO returnfeedgroup = new FeedGroupDTO(feedgroupData.getFeedgroupid(), null,
					feedgroupData.getUser().getUserid(), feedgroupData.getCulture().getCultureid(),
					feedgroupData.getAccess(), feedgroupData.getGroupname(), feedgroupData.getFeeddate(),
					feedgroupData.getComment(), null, null);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, returnfeedgroup,
					AquaConstants.success);

		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed + e);
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * FEED TEMPLATES
	 */
	public ResponseEntity<ResponseDTO> findAllTemplates(Long userid) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			List<TemplateFeedDTO> templatedtolist = new ArrayList<TemplateFeedDTO>();
			List<FeedTemplate> feedTemplateList = feedTemplateRepository.findAll();
			for (int i = 0; i < feedTemplateList.size(); i++) {
				FeedTemplate feedtemplate = (FeedTemplate) feedTemplateList.get(i);
				TemplateFeedDTO templatedto = new TemplateFeedDTO(feedtemplate.getTemplateid(),
						feedtemplate.getUser().getUserid(), feedtemplate.getFeedgroup().getFeedgroupid(),
						feedtemplate.getProductcategory().getProductcatgeoryid(),
						feedtemplate.getProduct().getProductid(), feedtemplate.getProductqty(),
						feedtemplate.getPriceperqty(), feedtemplate.getFeedgroup().getGroupname(),
						feedtemplate.getProductcategory().getName(), feedtemplate.getProduct().getProductname());
				templatedtolist.add(templatedto);
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, templatedtolist,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					AquaConstants.failed + e, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}
	/*
	 * FEED GROUPS
	 */

	public ResponseEntity<ResponseDTO> findGroups(Long userid) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			List<FeedGroupResDTO> templatedtolist = new ArrayList<FeedGroupResDTO>();
			List<FeedGroup> feedGroupsList = feedGroupRepository.findAll();
			List<FeedTemplateResponseDTO> feedProductsList;
			List<FeedTemplateResponseDTO> supplimentsList;

			for (int i = 0; i < feedGroupsList.size(); i++) {

				FeedGroup feedgroup = (FeedGroup) feedGroupsList.get(i);

				List<FeedTemplate> feedTemplatesList = feedTemplateRepository
						.findTemplatesByProductCatg(feedgroup.getFeedgroupid(), new Long(1));
				feedProductsList = new ArrayList<FeedTemplateResponseDTO>();
				for (int j = 0; j < feedTemplatesList.size(); j++) {
					FeedTemplate feedtempalte = (FeedTemplate) feedTemplatesList.get(j);
					FeedTemplateResponseDTO feedtemplatedto = new FeedTemplateResponseDTO();
					BeanUtils.copyProperties(feedtempalte, feedtemplatedto);
					feedtemplatedto.setProductID(feedtempalte.getProduct().getProductid());
					feedtemplatedto.setProductName(feedtempalte.getProduct().getProductname());
					feedtemplatedto.setProductcatgeoryID(feedtempalte.getProductcategory().getProductcatgeoryid());
					feedtemplatedto.setQuantitycategoryid(feedtempalte.getQuantitycategories().getQuantitycategoryid());
					feedtemplatedto.setQuantity(feedtempalte.getQuantitycategories().getQtycategory());
					feedProductsList.add(feedtemplatedto);
				}

				List<FeedTemplate> supplimentTemplatesList = feedTemplateRepository
						.findTemplatesByProductCatg(feedgroup.getFeedgroupid(), new Long(2));
				supplimentsList = new ArrayList<FeedTemplateResponseDTO>();
				for (int k = 0; k < supplimentTemplatesList.size(); k++) {
					FeedTemplate supplimenttempalte = (FeedTemplate) supplimentTemplatesList.get(k);
					FeedTemplateResponseDTO supplimentsDTO = new FeedTemplateResponseDTO();
					BeanUtils.copyProperties(supplimenttempalte, supplimentsDTO);
					supplimentsDTO.setProductID(supplimenttempalte.getProduct().getProductid());
					supplimentsDTO.setProductName(supplimenttempalte.getProduct().getProductname());
					supplimentsDTO.setProductcatgeoryID(supplimenttempalte.getProductcategory().getProductcatgeoryid());
					supplimentsDTO
							.setQuantitycategoryid(supplimenttempalte.getQuantitycategories().getQuantitycategoryid());
					supplimentsDTO.setQuantity(supplimenttempalte.getQuantitycategories().getQtycategory());
					supplimentsList.add(supplimentsDTO);
				}

				FeedGroupResDTO feedgroupdto = new FeedGroupResDTO(feedgroup.getFeedgroupid(),
						feedgroup.getUser().getUserid(), feedgroup.getGroupname(),feedgroup.getFeeddate(), feedProductsList, supplimentsList);
				templatedtolist.add(feedgroupdto);
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, templatedtolist,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					AquaConstants.failed + e, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * TEMPLATES OF FEED GROUP
	 */
	public ResponseEntity<ResponseDTO> findtemplateGroups(Long userid, Long groupid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<TemplateFeedDTO> templatedtolist = new ArrayList<TemplateFeedDTO>();
			List<FeedTemplate> feedTemplateList = feedTemplateRepository.findTemplatesByUser(userid, groupid);
			for (int i = 0; i < feedTemplateList.size(); i++) {
				FeedTemplate feedtemplate = (FeedTemplate) feedTemplateList.get(i);
				TemplateFeedDTO templatedto = new TemplateFeedDTO(feedtemplate.getTemplateid(),
						feedtemplate.getUser().getUserid(), feedtemplate.getFeedgroup().getFeedgroupid(),
						feedtemplate.getProductcategory().getProductcatgeoryid(),
						feedtemplate.getProduct().getProductid(), feedtemplate.getProductqty(),
						feedtemplate.getPriceperqty(), feedtemplate.getFeedgroup().getGroupname(),
						feedtemplate.getProductcategory().getName(), feedtemplate.getProduct().getProductname());
				templatedtolist.add(templatedto);
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, templatedtolist,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					AquaConstants.failed + e, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}
	
	/*
	 * FEED GROUPS
	 */

	public ResponseEntity<ResponseDTO> feedList(Long userid,Long cultureId,String feedDate) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			List<FeedGroupResDTO> templatedtolist = new ArrayList<>();
			List<FeedGroup> feedGroupsList;
			if(feedDate.equalsIgnoreCase("0")) {
				feedGroupsList = feedGroupRepository.findGroupByCultureId(userid,cultureId);
			}else {
				feedGroupsList = feedGroupRepository.findGroupsByCultureFeeddate(userid,cultureId,feedDate);
			}
			
			List<FeedTemplateResponseDTO> feedProductsList;
			List<FeedTemplateResponseDTO> supplimentsList;

			for (int i = 0; i < feedGroupsList.size(); i++) {

				FeedGroup feedgroup = (FeedGroup) feedGroupsList.get(i);

				List<FeedTemplate> feedTemplatesList = feedTemplateRepository
						.findTemplatesByProductCatg(feedgroup.getFeedgroupid(), new Long(1));
				feedProductsList = new ArrayList<FeedTemplateResponseDTO>();
				for (int j = 0; j < feedTemplatesList.size(); j++) {
					FeedTemplate feedtempalte = (FeedTemplate) feedTemplatesList.get(j);
					FeedTemplateResponseDTO feedtemplatedto = new FeedTemplateResponseDTO();
					BeanUtils.copyProperties(feedtempalte, feedtemplatedto);
					feedtemplatedto.setProductID(feedtempalte.getProduct().getProductid());
					feedtemplatedto.setProductName(feedtempalte.getProduct().getProductname());
					feedtemplatedto.setProductcatgeoryID(feedtempalte.getProductcategory().getProductcatgeoryid());
					feedtemplatedto.setQuantitycategoryid(feedtempalte.getQuantitycategories().getQuantitycategoryid());
					feedtemplatedto.setQuantity(feedtempalte.getQuantitycategories().getQtycategory());
					feedtemplatedto.setComments(feedtempalte.getFeedgroup().getComment());
					feedProductsList.add(feedtemplatedto);
				}

				List<FeedTemplate> supplimentTemplatesList = feedTemplateRepository
						.findTemplatesByProductCatg(feedgroup.getFeedgroupid(), new Long(2));
				supplimentsList = new ArrayList<FeedTemplateResponseDTO>();
				for (int k = 0; k < supplimentTemplatesList.size(); k++) {
					FeedTemplate supplimenttempalte = (FeedTemplate) supplimentTemplatesList.get(k);
					FeedTemplateResponseDTO supplimentsDTO = new FeedTemplateResponseDTO();
					BeanUtils.copyProperties(supplimenttempalte, supplimentsDTO);
					supplimentsDTO.setProductID(supplimenttempalte.getProduct().getProductid());
					supplimentsDTO.setProductName(supplimenttempalte.getProduct().getProductname());
					supplimentsDTO.setProductcatgeoryID(supplimenttempalte.getProductcategory().getProductcatgeoryid());
					supplimentsDTO
							.setQuantitycategoryid(supplimenttempalte.getQuantitycategories().getQuantitycategoryid());
					supplimentsDTO.setQuantity(supplimenttempalte.getQuantitycategories().getQtycategory());
					supplimentsDTO.setComments(supplimenttempalte.getFeedgroup().getComment());
					supplimentsList.add(supplimentsDTO);
				}

				FeedGroupResDTO feedgroupdto = new FeedGroupResDTO(feedgroup.getFeedgroupid(),
						feedgroup.getUser().getUserid(), feedgroup.getGroupname(),feedgroup.getFeeddate(), feedProductsList, supplimentsList);
				templatedtolist.add(feedgroupdto);
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, templatedtolist,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					AquaConstants.failed + e, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
