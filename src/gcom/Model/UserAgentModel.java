package gcom.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	private Boolean isConnection = false; 
	
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
			isConnection = false;
		}else{
			Date d = new Date();
			long l = d.getTime();
			try{
				SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d2 = format.parse(value);
				long l2 = d2.getTime();
				if(l - l2 < 1800000){
					isConnection = true;
				};
			}catch(Exception e){				
			}
			
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
