package com.odos.smartaqua.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VersionDTO {

	public String versionname;

	public int versioncode;

	public String sourcetype;

	public String updatepath;

	public String updatemessage;

	public boolean ismandatory;

}
