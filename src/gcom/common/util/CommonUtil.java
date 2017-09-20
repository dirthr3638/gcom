package gcom.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
			icon += "<i class=\"fa fa-trash policy_icon\" style=\"color:#ea6a66\" title=\"에이전트삭제불가능\"></i>";
		}

		if(data.getIsFileEncryption()){
			icon += "<i class=\"fa fa-file policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"파일암호화사용\"></i>";
		}else{
			icon += "<i class=\"fa fa-file policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"파일암호화미사용\"></i>";
		}

		if(data.getIsCdEncryption()){
			icon += "<i class=\"fa fa-get-pocket policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"CD암호화사용\"></i>";
		}else{
			icon += "<i class=\"fa fa-get-pocket policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"CD암호화미사용\"></i>";
		}
		
		if(data.getIsPrint()){
			icon += "<i class=\"fa fa-print policy_icon\" style=\"color:#7ed67e\" title=\"프린터사용가능\"></i>";
		}else{
			icon += "<i class=\"fa fa-print policy_icon\" style=\"color:#ea6a66\" title=\"프린터사용불가능\"></i>";
		}
		
		if(data.getIsCdEnabled()){
			icon += "<i class=\"fa fa-database policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"CD사용\"></i>";
		}else{
			icon += "<i class=\"fa fa-database policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"CD미사용\"></i>";
		}
		
		if(data.getIsCdExport()){
			icon += "<i class=\"fa fa-minus-circle policy_icon\" aria-hidden=\"true\" style=\"color:#7ed67e\" title=\"CD반출가능\"></i>";
		}else{
			icon += "<i class=\"fa fa-minus-circle policy_icon\" aria-hidden=\"true\" style=\"color:#ea6a66\" title=\"CD반출불가능\"></i>";
		}
		
		if(data.getIsWlan()){
			icon += "<i class=\"fa fa-wifi policy_icon\" style=\"color:#7ed67e\" title=\"무선랜사용\"></i>";
		}else{
			icon += "<i class=\"fa fa-wifi policy_icon\" style=\"color:#ea6a66\" title=\"무선랜미사용\"></i>";
		}
		
		if(data.getIsNetShare()){
			icon += "<i class=\"fa fa-share-alt policy_icon\" style=\"color:#7ed67e\" title=\"공유폴더사용\"></i>";
		}else{
			icon += "<i class=\"fa fa-share-alt policy_icon\" style=\"color:#ea6a66\" title=\"공유폴더미사용\"></i>";
		}
		
		if(data.getIsWebExport()){
			icon += "<i class=\"fa fa-envelope policy_icon\" style=\"color:#7ed67e\" title=\"메일반출사용\"></i>";	
		}else{
			icon += "<i class=\"fa fa-envelope policy_icon\" style=\"color:#ea6a66\" title=\"메일반출미사용\"></i>";
		}
		
		if(data.getIsSensitiveDirEnabled()){
			icon += "<i class=\"fa fa-folder-open policy_icon\"  style=\"color:#7ed67e\" title=\"보호폴더접근가능\"></i>";	
		}else{
			icon += "<i class=\"fa fa-folder-open policy_icon\"  style=\"color:#ea6a66\" title=\"보호폴더접근불가능\"></i>";		
		}
		
		if(data.getIsSensitiveFileAccess()){
			icon += "<i class=\"fa fa-file-archive-o policy_icon\" style=\"color:#7ed67e\" title=\"민감파일접근시삭제\"></i>";		
		}else{
			icon += "<i class=\"fa fa-file-archive-o policy_icon\"  style=\"color:#ea6a66\" title=\"민감파일접근시보호폴더로이동\"></i>";	
		}
		
		if(data.getIsStorageExport()){
			icon += "<i class=\"fa fa-archive policy_icon\"  style=\"color:#7ed67e\" title=\"디스크반출가능\"></i>";	
		}else{
			icon += "<i class=\"fa fa-archive policy_icon\"  style=\"color:#ea6a66\" title=\"디스크반출불가능\"></i>";		
		}
		
		if(data.getIsStorageAdmin()){
			icon += "<i class=\"fa fa-id-card policy_icon\" style=\"color:#7ed67e\" title=\"디스크관리가능\"></i>";		
		}else{
			icon += "<i class=\"fa fa-id-card policy_icon\" style=\"color:#ea6a66\" title=\"디스크관리불가\"></i>";	
		}
		
		if(data.getIsUsbControlEnabled()){
			icon += "<i class=\"fa fa-cogs policy_icon\"  style=\"color:#7ed67e\" title=\"USB통제기능사용\"></i>";	
		}else{
			icon += "<i class=\"fa fa-cogs policy_icon\"  style=\"color:#ea6a66\" title=\"USB통제기능미사용\"></i>";		
		}
		
		if(data.getPatternFileControl() == 1){
			icon += "<i class=\"fa fa-file-powerpoint-o policy_icon\"  style=\"color:#7ed67e\" title=\"검출된패턴파일삭제\"></i>";		
		}else{
			icon += "<i class=\"fa fa-file-powerpoint-o policy_icon\"  style=\"color:#ea6a66\" title=\"검출된패턴파일격리\"></i>";	
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

	public static String getFomatLimitTime(String data) {
		String result = "";
		
		if("".equals(data) || "0".equals(data)) {
			result = data;
		} else {
			String[] temp =  data.split(">");
			
			String from = temp[0];
			if(temp.length > 1) {
				String limitType = temp[1].substring(0, 1);
				String limitTime = temp[1].substring(1, temp[1].length());
				
				Calendar cal = new GregorianCalendar(Locale.KOREA);
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				try {
					Date date = transFormat.parse(from);
					cal.setTime(date);
					
					if ("H".equals(limitType)) {
						cal.add(Calendar.HOUR, Integer.parseInt(limitTime)); // 시간을 더한다
					} else if ("D".equals(limitType)) {
						cal.add(Calendar.DAY_OF_YEAR, Integer.parseInt(limitTime)); // 일을 더한다
					} else if ("M".equals(limitType)) {
						cal.add(Calendar.MONTH, Integer.parseInt(limitTime)); // 월을 더한다
					}
					
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    String strDate = fm.format(cal.getTime());
					result = strDate;
			
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public static String getReplaceHtmlChar(String str) {
		String data = "";
		StringBuffer sb = new StringBuffer();
		 
		for(int i=0; i < str.length(); i++) 
		{
		    char c = str.charAt(i);
		    switch (c) 
		   {
		      case '<' : 
		        sb.append("&lt;");
		        break;
		     case '>' : 
		        sb.append("&gt;");
		        break;
		     case '&' :
		        sb.append("&amp;");
		        break;
		     case '"' :
		        sb.append("&quot;");
		        break;
		     case '\\' : 
		        sb.append("&apos;");
		        break;
		     default:
		        sb.append(c);
		     } 
		 }
		data = sb.toString();
	
		return data;
	}
	
}
