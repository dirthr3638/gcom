package gcom.service.disk;

import java.util.HashMap;
import java.util.List;

import gcom.Model.DiskConnectLogModel;
import gcom.Model.PartitionConnectLogModel;

public interface IDiskService {

	public List<DiskConnectLogModel> getDiskConnectLogList(HashMap<String, Object> map);
	public int getDiskConnectLogListCount(HashMap<String, Object> map);

	public List<PartitionConnectLogModel> getPartitionConnectLogList(HashMap<String, Object> map);
	public int getPartitionConnectLogListCount(HashMap<String, Object> map);

}
