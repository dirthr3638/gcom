package gcom.Model;

import lombok.Data;

@Data
public class FileOwnerShipLogModel {
	private int ownershipNo;
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
	private String ownerType = "";
	private String ownerData = "";
	private int fileId;
	private String serverTime = "";
	private String clientTime = "";
}
