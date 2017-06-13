package gcom.service.Device;

import java.util.HashMap;
import java.util.List;

import gcom.Model.DiskExportModel;
import gcom.Model.LoginLogModel;
import gcom.Model.UsbConnectModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserAgentModel;

public interface IDeviceInfoService {

	public List<UsbDevInfoModel> getUnAuthUsbList(HashMap<String, Object> map);
	public int getUnAuthUsbListCount(HashMap<String, Object> map);
	public List<DiskExportModel> getDiskTranList(HashMap<String, Object> map);
	public int getDiskTranListCount(HashMap<String, Object> map);
	public List<UsbConnectModel> getUsbBlockList(HashMap<String, Object> map);
	public int getUsbBlockListCount(HashMap<String, Object> map);
	
}