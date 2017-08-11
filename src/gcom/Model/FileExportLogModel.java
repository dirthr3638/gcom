package gcom.Model;

import lombok.Data;

@Data
public class FileExportLogModel {
	private int exportNo;
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
	
	private String filePath = "";
	private String password = "";
	private String notice = "";

	private int fileId;
	private String serverTime = "";
	private String clientTime = "";
}
