package gcom.service.Device;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.DeviceDataDAO;
import gcom.DAO.DiskDataDAO;
import gcom.Model.DiskExportModel;
import gcom.Model.PrintFileModel;
import gcom.Model.UsbConnectModel;
import gcom.Model.UsbDevInfoModel;

public class DeviceInfoServiceImpl implements IDeviceInfoService {

	DeviceDataDAO uaDao = new DeviceDataDAO();
	DiskDataDAO diskDao = new DiskDataDAO();
	
	//비인가 USB목록
	public List<UsbDevInfoModel> getUnAuthUsbList(HashMap<String, Object> map){		
		return diskDao.getUnAuthUsbList(map);
	}	
	public int getUnAuthUsbListCount(HashMap<String, Object> map){		
		return diskDao.getUnAuthUsbListCount(map);		
	}
	
	//이동식디스크 파일전송로그
	public List<DiskExportModel> getDiskTranList(HashMap<String, Object> map){		
		return diskDao.getDiskExportList(map);
	}	
	public int getDiskTranListCount(HashMap<String, Object> map){		
		return diskDao.getDiskExportListCount(map);		
	}

	//USB차단로그
	public List<UsbConnectModel> getUsbBlockList(HashMap<String, Object> map){		
		return diskDao.getUsbConnectList(map);
	}	
	public int getUsbBlockListCount(HashMap<String, Object> map){		
		return diskDao.getUsbConnectListCount(map);		
	}

	
	//프린트로그
	public List<PrintFileModel> getPrintLogList(HashMap<String, Object> map){		
		return uaDao.getPrintLogList(map);
	}	
	public int getPrintLogCount(HashMap<String, Object> map){		
		return uaDao.getPrintLogListCount(map);		
	}

}
