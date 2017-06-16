package gcom.Model;

import lombok.Data;

@Data
public class MailExportModel {
	private int mailNo;
	private int userNo;
	private String userName = "";
	private String userId = "";
	private int deptId ;
	private String duty = "";
	private String rank = "";
	private String ipAddr = "";
	private String macAddr = "";
	private String pcName = "";
	private String deptName = "";
	private String email ="";
	private String fileName ="";
	private String notice ="";
	private int fileKey;
	private String exportServerTime = "";
	private String exportClientTime = "";
	
}
