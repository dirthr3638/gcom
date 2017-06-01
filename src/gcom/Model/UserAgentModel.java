package gcom.Model;

import lombok.Data;

@Data
public class UserAgentModel {
	private int uid;
	private int deptNo;
	private String duty  = "";
	private String rank  = "";
	private String name  = "";
	private String phone  = "";
	private String id  = "";
	private String deptName  = "";
	private boolean valid;
	private String pcName  = "";
	private String ipAddr  = "";
	private String macAddr  = "";
	private String login_server_time  = "";
	private String connect_server_time = "";
	private String version = "";
	private String install_server_time  = "";
	
	public void setValid(int value){
		if(value == 1){
			valid = true;
		}else{
			valid = false;
		}
	}
	
	public void setLogin_server_time(String value){
		if(value.equals("")){
			login_server_time = "기록없음";
		}else{
			login_server_time = value;
		}
	}
	public void setConnect_server_time(String value){
		if(value.equals("")){
			connect_server_time = "기록없음";
		}else{
			connect_server_time = value;
		}
	}
	public void setInstall_server_time(String value){
		if(value.equals("")){
			install_server_time = "기록없음";
		}else{
			install_server_time = value;
		}
	}

}
