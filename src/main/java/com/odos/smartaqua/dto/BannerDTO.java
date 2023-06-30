package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerDTO {

	public Long bannerid;
	public String bannertitle;
	public String bannerimage;
	public String bannertype;
	public String bannerlink;
	public String cretaedby;
	public String createddate;
	public String modifieddate;
	public boolean isactive;
}
