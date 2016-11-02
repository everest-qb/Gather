package com.sunteam.dc.json;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ListStation {

	@XmlTransient
	public static String List_VIDEO_FAIL="fail";
	@XmlTransient
	public static String List_VIDEO_SUCCESS="success";
	
	
	
}
