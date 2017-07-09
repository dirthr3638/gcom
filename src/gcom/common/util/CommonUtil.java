package gcom.common.util;

import java.util.ArrayList;

import gcom.user.model.MemberPolicyModel;

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
		String result = "";
		
		if("".equals(blockCode)){
			result = value;
		} else {
			result = blockCode + "," +  value;
		}
		
		return result;
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
		 
		 String result = resultArray.toString();
		 result = result.replace("[", "");
		 result = result.replace("]", "");
		 result = result.replace(" ", "");
		 
		return result;
	}
	
	public static String getPolicyIcon(MemberPolicyModel data){
		String icon = "";
		
		if(data.getIsUninstall()){
			icon += "<i class=\"fa fa-trash policy_icon\" style=\"color:#7ed67e \" title=\"에이전트삭제가능\"></i>";
		}else{
			icon += "<i class=\"fa fa-trash policy_icon\" style=\"color:#ea6a66\" title=\"에이전트삭제불가\"></i>";
		}

		if(data.getIsFileEncryption()){
			icon += "<i class=\"fa fa-file policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"실시간파일암호화\"></i>";
		}else{
			icon += "<i class=\"fa fa-file policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"실시간파일 비암호화\"></i>";
		}

		if(data.getIsCdEncryption()){
			icon += "<i class=\"fa fa-get-pocket policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"CD암호화\"></i>";
		}else{
			icon += "<i class=\"fa fa-get-pocket policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"CD비암호화\"></i>";
		}
		
		if(data.getIsPrint()){
			icon += "<i class=\"fa fa-print policy_icon\" style=\"color:#7ed67e\" title=\"프린트가능\"></i>";
		}else{
			icon += "<i class=\"fa fa-print policy_icon\" style=\"color:#ea6a66\" title=\"프린트불가\"></i>";
		}
		
		if(data.getIsCdEnabled()){
			icon += "<i class=\"fa fa-database policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"CD사용허용\"></i>";
		}else{
			icon += "<i class=\"fa fa-database policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"CD사용차단\"></i>";
		}
		
		if(data.getIsCdExport()){
			icon += "<i class=\"fa fa-minus-circle policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"CD반출허용\"></i>";
		}else{
			icon += "<i class=\"fa fa-minus-circle policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"CD반출차단\"></i>";
		}
		
		if(data.getIsWlan()){
			icon += "<i class=\"fa fa-wifi policy_icon\" style=\"color:#7ed67e\" title=\"무선랜허용\"></i>";
		}else{
			icon += "<i class=\"fa fa-wifi policy_icon\" style=\"color:#ea6a66\" title=\"무선랜차단\"></i>";
		}
		
		if(data.getIsNetShare()){
			icon += "<i class=\"fa fa-folder-open policy_icon\" style=\"color:#7ed67e\" title=\"공유폴더허용\"></i>";
		}else{
			icon += "<i class=\"fa fa-folder-open policy_icon\" style=\"color:#ea6a66\" title=\"공유폴더차단\"></i>";
		}
		
		if(data.getIsWebExport()){
			icon += "<i class=\"fa fa-envelope policy_icon\" style=\"color:#7ed67e\" title=\"메일반출허용\"></i>";	
		}else{
			icon += "<i class=\"fa fa-envelope policy_icon\" style=\"color:#ea6a66\" title=\"메일반출차단\"></i>";
		}
		
		if(data.getIsUsbBlock()){
			icon += "<i class=\"fa fa-usb policy_icon\" style=\"color:#ea6a66\" title=\"USB포트차단\"></i>";
		}else{
			icon += "<i class=\"fa fa-usb policy_icon\" style=\"color:#7ed67e\" title=\"USB포트허용\"></i>";
		}
		
		if(data.getIsComPortBlock()){
			icon += "<i class=\"fa fa-plug policy_icon\" style=\"color:#ea6a66\" title=\"시리얼포트차단\"></i>";
		}else{
			icon += "<i class=\"fa fa-plug policy_icon\" style=\"color:#7ed67e\" title=\"시리얼포트허용\"></i>";
		}
		
		if(data.getIsNetPortBlock()){
			icon += "<i class=\"fa fa-sitemap policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"네트워크포트차단\"></i>";	
		}else{
			icon += "<i class=\"fa fa-sitemap policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"네트워크포트허용\"></i>";
		}
		
		if(data.getIsProcessList()){ 
			icon += "<i class=\"fa fa-desktop policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"프로세스사용차단\"></i>";
		}else{
			icon += "<i class=\"fa fa-desktop policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"프로세스사용허용\"></i>";	
		}
		
		if(data.getIsFilePattern()){
			icon += "<i class=\"fa fa-clone policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"민감정보파일차단\"></i>";
		}else{
			icon += "<i class=\"fa fa-clone policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"민감정보파일허용\"></i>";
		}
		
		if(data.getIsWebAddr()){
			icon += "<i class=\"fa fa-internet-explorer policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"사이트차단\"></i>";
		}else{
			icon += "<i class=\"fa fa-internet-explorer policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"사이트허용\"></i>";	
		}
		
		if(data.getIsMsgBlock()){
			icon += "<i class=\"fa fa-commenting policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"메신저차단\"></i>";
		}else{
			icon += "<i class=\"fa fa-commenting policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"메신저허용\"></i>";
		}
		
		if(data.getIsWaterMark()){
			icon += "<i class=\"fa fa-tint policy_icon\" style=\"color:#7ed67e\" title=\"워터마크출력\"></i>";
		}else{
			icon += "<i class=\"fa fa-tint policy_icon\" style=\"color:#ea6a66\" title=\"워터마크미출력\"></i>";
		}
		
		return icon;
	}
	
}
