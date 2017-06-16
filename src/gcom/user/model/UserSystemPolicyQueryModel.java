package gcom.user.model;

import lombok.Data;

@Data
public class UserSystemPolicyQueryModel {
	
	private String policySqlQuery = "";
	
	public UserSystemPolicyQueryModel(String code, String keyCode) {
		
		if("system".equals(code)){
			setSelectSystemPolicy();
		} else if("pattern".equals(code)) {
			setSelectPatternPolicy();
		} else if("netport".equals(code)) {
			setSelectNetPortPolicy();
		} else if("process".equals(code)) {
			setSelectProcessPolicy();
		} else if("usbport".equals(code)) {
			setSelectUsbPortPolicy();
		} else if("serialport".equals(code)) {
			setSelectSerialPortPolicy();
		} else if("messenger".equals(code)) {
			setSelectMessengerPolicy();
		} else if("siteblock".equals(code)) {
			setSelectSiteBlockPolicy();
		} else if("isUsbBlock".equals(code)) {
			setSelectWhereKeyUsbDevList(keyCode);
		} else if("isComPortBlock".equals(code)) {
			setSelectWhereKeyComPortList(keyCode);
		} else if("isNetPortBlock".equals(code)) {
			setSelectWhereKeyNetPortList(keyCode);
		} else if("isProcessList".equals(code)) {
			setSelectWhereKeyProcessList(keyCode);
		} else if("isFilePattern".equals(code)) {
			setSelectWhereKeyFilePatternList(keyCode);
		} else if("isWebAddr".equals(code)) {
			setSelectWhereKeyWebAddrList(keyCode);
		} else {
			setSelectSystemPolicy();
		}
	}

	private void setSelectWhereKeyWebAddrList(String keyCode) {
		this.policySqlQuery = "SELECT * FROM web_addr_info WHERE no IN ( " + keyCode + " )";
	}

	private void setSelectWhereKeyFilePatternList(String keyCode) {
		this.policySqlQuery = "SELECT * FROM pattern_info WHERE no IN ( " + keyCode + " )";
	}

	private void setSelectWhereKeyProcessList(String keyCode) {
		this.policySqlQuery = "SELECT * FROM process_info WHERE no IN ( " + keyCode + " )";
	}

	private void setSelectWhereKeyNetPortList(String keyCode) {
		this.policySqlQuery = "SELECT * FROM net_port_info WHERE no IN ( " + keyCode + " )";
	}

	private void setSelectWhereKeyComPortList(String keyCode) {
		this.policySqlQuery = "SELECT * FROM com_port_info WHERE no IN ( " + keyCode + " )";
	}

	private void setSelectWhereKeyUsbDevList(String keyCode) {
		this.policySqlQuery = "SELECT * FROM usb_dev_info WHERE no IN ( " + keyCode + " )";
	}

	private void setSelectSystemPolicy() {
		this.policySqlQuery = "SELECT * FROM system_info WHERE visible = 1";
	}
	
	private void setSelectPatternPolicy() {
		this.policySqlQuery = "SELECT * FROM pattern_info WHERE valid = 1";
	}
	
	private void setSelectNetPortPolicy() {
		this.policySqlQuery = "SELECT * FROM net_port_info WHERE allow = 1";
	}
	
	private void setSelectProcessPolicy() {
		this.policySqlQuery = "SELECT * FROM process_info WHERE valid = 1";
	}

	private void setSelectUsbPortPolicy() {
		this.policySqlQuery = "SELECT * FROM usb_dev_info WHERE allow = 1";
	}

	private void setSelectSerialPortPolicy() {
		this.policySqlQuery = "SELECT * FROM com_port_info WHERE allow = 1";
	}

	private void setSelectMessengerPolicy() {
		this.policySqlQuery = "SELECT * FROM msg_info";
	}
	
	private void setSelectSiteBlockPolicy() {
		this.policySqlQuery = "SELECT * FROM web_addr_info WHERE allow = 1";
	}
	
}
