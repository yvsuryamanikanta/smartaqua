package com.odos.smartaqua.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialDaysDTO {

	public Long dayid;
	public String daytitle;
	public String date;
	public String daytype;
	public String daylink;
	public String cretaedby;
	public String createddate;
	public String modifieddate;
	public boolean isactive;
}
