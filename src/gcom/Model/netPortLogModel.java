package gcom.Model;

import lombok.Data;

@Data
public class netPortLogModel {
	private int portNo;
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
	private String processName = "";
	private String description = "";
	private String control = "";
	private String serverTime = "";
	private String clientTime = "";
}
