package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.DashBoardDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.ChecktrayObservation;
import com.odos.smartaqua.entities.FeedGroup;
import com.odos.smartaqua.entities.LabObservation;
import com.odos.smartaqua.repository.ChecktrayObservationRepository;
import com.odos.smartaqua.repository.FeedGroupRepository;
import com.odos.smartaqua.repository.LabObservationRepository;
import com.odos.smartaqua.service.DashBoardService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class DashBoardServiceImpl implements DashBoardService {

	@Autowired
	private FeedGroupRepository feedGroupRepository;
	
	@Autowired
	private ChecktrayObservationRepository checktrayObservationRepository;
	
	@Autowired
	private LabObservationRepository labObservationRepository;

	@Override
	public ResponseEntity<ResponseDTO> findAllGroups(Long userid, Long cultureid, Long tankid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<FeedGroup> feedGroupsList = feedGroupRepository.findGroupByCultureId(userid, cultureid);
			List<ChecktrayObservation> checktrayObservationList = checktrayObservationRepository.findChecktrayObservationListByTank(tankid);
			List<LabObservation> labObservationList = labObservationRepository.findLabObservationList(tankid);
			List<DashBoardDTO> dashBoardDtoList = new ArrayList<DashBoardDTO>();
			if (feedGroupsList.size() != 0) {
				for (int i = 0; i < feedGroupsList.size(); i++) {
					FeedGroup feedgroup = (FeedGroup) feedGroupsList.get(i);
					DashBoardDTO dashBoardDTO = new DashBoardDTO(feedgroup.getFeedgroupid(),
							feedgroup.getCulture().getTank().getTankid(), feedgroup.getCulture().getCultureid(),
							feedgroup.getAccess(), "FEED", feedgroup.getFeeddate());
					dashBoardDtoList.add(dashBoardDTO);
				}
				
				if(checktrayObservationList.size()!=0) {
					for (int j = 0; j < checktrayObservationList.size(); j++) {
						ChecktrayObservation checktrayObservation = (ChecktrayObservation) checktrayObservationList.get(j);
						DashBoardDTO checktrayObservationDTO = new DashBoardDTO(checktrayObservation.getChecktrayobsvid(),
								checktrayObservation.getTank().getTankid(), checktrayObservation.getTank().getTankid(),
								"", "CHECKTRAY", checktrayObservation.getChecktrayobsvdate());
						dashBoardDtoList.add(checktrayObservationDTO);
					}
				}
				
				if(labObservationList.size()!=0) {
					for (int j = 0; j < labObservationList.size(); j++) {
						LabObservation labObservation = (LabObservation) labObservationList.get(j);
						DashBoardDTO labObservationDTO = new DashBoardDTO(labObservation.getLabobservationid(),
								labObservation.getTank().getTankid(), labObservation.getTank().getTankid(),
								"", "LAB", labObservation.getLabobsvdate());
						dashBoardDtoList.add(labObservationDTO);
					}
				}
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, dashBoardDtoList,
						AquaConstants.success);
			} else {
				responseDTO = new ResponseDTO(AquaConstants.failed, "200", AquaConstants.failed,
						AquaConstants.failed);
			}
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					AquaConstants.failed, AquaConstants.failed);
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
