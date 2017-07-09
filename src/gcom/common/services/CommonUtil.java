package gcom.common.services;

import java.util.ArrayList;

public class CommonUtil {

	public static String getStrPolicyChangeOperation(String value, String strPolicy){
		String result  = "";
		String blockYn = "";
		String blockCode = "";
		
		if(strPolicy.substring(0,1).equals("Y")){
			blockYn = "Y";
			blockCode = strPolicy.length() > 1 ? strPolicy.substring(2, strPolicy.length()) : "" ;
			
			if(blockCode.contains(value)) {
				result = blockYn + "," + getStrPolicySubData(value, blockCode);
			} else {
				result = strPolicy;
			}

		}else{
			blockYn = "N";
			blockCode = strPolicy.length() > 1 ? strPolicy.substring(2, strPolicy.length()) : "" ;
			
			if(blockCode.contains(value)) {
				result = strPolicy;
			} else {
				result = blockYn + "," + getStrPolicyAddData(value, blockCode);
			}
		}
		
		return result;
	}
	
	// 차단 코드 N일 경우 해당 코드가 포함되있지 않을 경우 코드 추가
	private static String getStrPolicyAddData(String value, String blockCode) {
		return blockCode + "," +  value;
	}

	// 차단 코드 Y일 경우 해당 코드가 포함되었을 경우 해당 코드 제거
	private static String getStrPolicySubData(String value, String blockCode) {
		String codeArray[] = blockCode.split(",");
		ArrayList<String> resultArray = new ArrayList<String>();
		
		 for(int i = 0 ; i < codeArray.length; i ++) {
         	if(!value.equals(codeArray[i])){
         		resultArray.add(codeArray[i]);
         	}
         }
		 
		return resultArray.toString();
	}
	
}
