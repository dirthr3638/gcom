package gcom.Model;

import lombok.Data;

@Data
public class FileEventLogModel {
	private int fileNo;
	private String userNo;
	private String userName = "";
	private String userId = "";
	private int deptId ;
	private String duty = "";
	private String rank = "";
	private String ipAddr = "";
	private String macAddr = "";
	private String pcName = "";
	private String deptName = "";
	
	private String fileId = "";
	private String description = "";

	private String serverTime = "";
	private String clientTime = "";
}
