package gcom.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class DiskExportModel {
	private int exportNo;
	private int userNo;
	private String exportServerTime  = "";
	private String exportClientTime  = "";
	private int grade;
	private String fileList = "";
	private String notice = "";
	private String exportStatus = "";
	private String fileId;
	private String userId = "";
	private int deptId ;
	private String duty = "";
	private String rank = "";
	private String ipAddr = "";
	private String macAddr = "";
	private String pcName = "";
	private String deptName = "";

}
