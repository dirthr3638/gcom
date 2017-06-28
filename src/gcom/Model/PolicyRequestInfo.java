package gcom.Model;

import lombok.Data;

@Data
public class PolicyRequestInfo {
	private int policyNo;
	private int userNo;
	private String userName = "";
	private String userId = "";
	private String duty = "";
	private String rank = "";
	private String ipAddr = "";
	private String macAddr = "";
	private String pcName = "";
	private String deptName = "";
	private String phone = "";

	private Boolean isUninstall;
	private Boolean isFileEncryption;
	private Boolean isCdEncryption;
	private Boolean isPrint;
	private Boolean isCdEnabled;
	private Boolean isCdExport;
	private Boolean isWlan;
	private Boolean isNetShare;
	private Boolean isWebExport;
	private Boolean isStorageExport;
	private Boolean isStorageAdmin;

	private Boolean isUsbBlock;
	
	private Boolean isComPortBlock;
	
	private Boolean isNetPortBlock;
	
	private Boolean isProcessList;
	private Boolean isFilePattern;
	
	private Boolean isWebAddr;
	
	private String waterMarkInfo = "";
	private Boolean isWaterMark;
	private String waterMarkEndDate = "";
	private int waterMarkType;
	
	private int printLogDesc;	//0:로그 전송 안함	1:이벤트 로그	2: 파일 원본 로그
	//private String quarantinePathAccessCode = "";
	private int patternFileControl;   //검출된 패턴파일 처리 방법 0:격리 1:삭제

	private PolicyInfoModel oldPolicy = null;
	
	public void setIsUninstall(int value){
		if(value == 0){
			isUninstall = false;
		}else{
			isUninstall = true;
		}
	}
	public void setIsFileEncryption(int value){
		if(value == 0){
			isFileEncryption = false;
		}else{
			isFileEncryption = true;
		}
	}
	public void setIsCdEncryption(int value){
		if(value == 0){
			isCdEncryption = false;
		}else{
			isCdEncryption = true;
		}
	}
	public void setIsPrint(int value){
		if(value == 0){
			isPrint = false;
		}else{
			isPrint = true;
		}
	}
	public void setIsCdEnabled(int value){
		if(value == 0){
			isCdEnabled = false;
		}else{
			isCdEnabled = true;
		}
	}
	public void setIsCdExport(int value){
		if(value == 0){
			isCdExport = false;
		}else{
			isCdExport = true;
		}
	}
	public void setIsWlan(int value){
		if(value == 0){
			isWlan = false;
		}else{
			isWlan = true;
		}
	}
	public void setIsNetShare(int value){
		if(value == 0){
			isNetShare = false;
		}else{
			isNetShare = true;
		}
	}
	public void setIsWebExport(int value){
		if(value == 0){
			isWebExport = false;
		}else{
			isWebExport = true;
		}
	}
	public void setIsStorageExport(int value){
		if(value == 0){
			isStorageExport = false;
		}else{
			isStorageExport = true;
		}
	}
	public void setIsStorageAdmin(int value){
		if(value == 0){
			isStorageAdmin = false;
		}else{
			isStorageAdmin = true;
		}
	}
	
	public void setIsUsbBlock(String value){
		if(value.substring(0,1).equals("Y")){
			isUsbBlock = true;
		}else{
			isUsbBlock = false;
		};		
	}
	public void setIsComPortBlock(String value){
		if(value.substring(0,1).equals("Y")){
			isComPortBlock = true;
		}else{
			isComPortBlock = false;
		};		
	}
	public void setIsNetPortBlock(String value){
		if(value.substring(0,1).equals("Y")){
			isNetPortBlock = true;
		}else{
			isNetPortBlock = false;
		};		
	}
	public void setIsWebAddr(String value){
		if(value.substring(0,1).equals("Y")){
			isWebAddr = true;
		}else{
			isWebAddr = false;
		};		
	}
	public void setWatermarkInfo(String value){
		String[] spv = value.split(",");
		if(spv[0].equals("Y")){
			isWaterMark = true;
		}else{
			isWaterMark = false;
		}
		waterMarkEndDate = spv[1];
	}

	public void setIsProcessList(int value){
		if(value == 0){
			isProcessList = false;
		}else{
			isProcessList = true;
		}
	}
	
	public void setIsFilePattern(int value){
		if(value == 0){
			isFilePattern = false;
		}else{
			isFilePattern = true;
		}
	}
}
