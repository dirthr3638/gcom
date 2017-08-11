package gcom.Model;

import lombok.Data;

@Data
public class DiskConnectLogModel {
	private int connectNo;
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

	private int status = -1;

	private String serverTime = "";
	private String clientTime = "";
}
