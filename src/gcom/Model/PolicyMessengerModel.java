package gcom.Model;

import lombok.Data;

@Data
public class PolicyMessengerModel {
	private int msgNo;
	private String msgName ="";
	private String processName = "";
	private boolean txtLog;
	private boolean txtBlock ;
	private boolean fileLog;
	private boolean fileBlock;
	
	public void setTxtLog(int value) {
		if(value == 0){
			txtLog = false;
		}else{
			txtLog = true;
		}
	}
	public void setTxtBlock(int value) {
		if(value == 0){
			txtBlock = false;
		}else{
			txtBlock = true;
		}
	}
	public void setFileLog(int value) {
		if(value == 0){
			fileLog = false;
		}else{
			fileLog = true;
		}
	}
	public void setFileBlock(int value) {
		if(value == 0){
			fileBlock = false;
		}else{
			fileBlock = true;
		}
	}
}
