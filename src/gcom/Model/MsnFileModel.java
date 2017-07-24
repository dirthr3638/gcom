package gcom.Model;

import lombok.Data;

@Data
public class MsnFileModel {
	private int msgNo;
	private int userNo;
	private String userName = "";
	private String userId = "";
	private int deptId ;
	private String duty = "";
	private String fileId = "";
	private String rank = "";
	private String ipAddr = "";
	private String macAddr = "";
	private String pcName = "";
	private String deptName = "";
	private String msgType = "";
	private String fileList = "";
	private String sendServerTime = "";
	private String sendClientTime = "";
}
