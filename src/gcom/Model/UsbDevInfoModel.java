package gcom.Model;

import lombok.Data;

@Data
public class UsbDevInfoModel {
	private int usbId;
	private String name = "";
	private String vid  = "";
	private String pid  = "";
	private String serialNumber  = "";
	private boolean allow  = true;
	private String description  = "";
}
