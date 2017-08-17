package gcom.Model;

import lombok.Data;

@Data
public class CDExportLogModel {
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
	
	private int fileId;
	private String fileList;
	private String notice;
	private int exportStatus;
	private String guid;
	private int status;
	private String label;
	
	private boolean valid;
	
	private String createdServerTime;
	private String createdClientTime;
	private String updateServerTime;
	private String updateClientTime;

	private String serverTime = "";
	private String clientTime = "";
}
