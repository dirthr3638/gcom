package gcom.Model;

import lombok.Data;

@Data
public class PolicyProcessModel {
	private int proNo;
	private String processName ="";
	private String hash = "";
	private String notice = "";
	private int valid ;
}
