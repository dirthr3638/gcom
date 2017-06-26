package gcom.controller.action;

import java.util.HashMap;
import java.util.Map;

import gcom.service.Statistic.IStatisticService;
import gcom.service.Statistic.StatisticServiceImpl;

public class getStatisticAction {
	public Map<String, Object> getFlotChartData(Map<String, Object> map){
		IStatisticService as = new StatisticServiceImpl();
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(map.get("setType").toString().equals("DAY")){
			result.put("USB", as.getUSBChartDayData(map));			
			result.put("EXPORT", as.getExportChartDayData(map));			
			result.put("PRINT", as.getPrintChartDayData(map));			
			result.put("PATTERN", as.getPatternChartDayData(map));			

			
		}else if(map.get("setType").toString().equals("MONTH")){
			result.put("USB", as.getUSBChartMonthData(map));			
			result.put("EXPORT", as.getExportChartMonthData(map));			
			result.put("PRINT", as.getPrintChartMonthData(map));			
			result.put("PATTERN", as.getPatternChartMonthData(map));			

		}
		
		return result;						
	}
}
