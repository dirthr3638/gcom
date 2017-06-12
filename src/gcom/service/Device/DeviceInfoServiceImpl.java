package gcom.service.Device;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.DeviceDataDAO;
import gcom.Model.UsbDevInfoModel;

public class DeviceInfoServiceImpl implements IDeviceInfoService {

	DeviceDataDAO uaDao = new DeviceDataDAO();
	
	//비인가 USB목록
	public List<UsbDevInfoModel> getUnAuthUsbList(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbList(map);
	}	
	public int getUnAuthUsbListCount(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbListCount(map);		
	}
	
	//이동식디스크 파일전송로그
	public List<UsbDevInfoModel> getDiskTranList(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbList(map);
	}	
	public int getDiskTranListCount(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbListCount(map);		
	}

	//USB차단로그
	public List<UsbDevInfoModel> getUsbBlockList(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbList(map);
	}	
	public int getUsbBlockCount(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbListCount(map);		
	}

	
	//프린트로그
	public List<UsbDevInfoModel> getPrintLogList(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbList(map);
	}	
	public int getPrintLogCount(HashMap<String, Object> map){		
		return uaDao.getUnAuthUsbListCount(map);		
	}

}
