package com.odos.smartaqua.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TankInfoDTO {

	private String tankid;

	private String tankname;

	private String tanklocation;

	private String tankimage;

	public String tankSize;

	public String tankSizeType;

	public String latitude;

	public String longitude;

	private List<PreparationDTO> preparationDTOList;

	private List<StockingDTO> stockingDTOList;

}
