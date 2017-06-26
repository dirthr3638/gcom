function getRequestPolicyDetailTable(data){
	var sOut = '<table class="table ">';

	sOut += '<tr><td colspan="2" class="center-cell">기존정책</td>';
	sOut += '<td colspan="2" style="padding-left:100px;" class="center-cell">변경요청정책</td></tr>';	

	if(data.isUninstall == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isUninstall == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">에이전트삭제가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">에이전트삭제가능:</td><td>불허</td></tr>';			
	}

	if(data.isFileEncryption == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isFileEncryption == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	if(data.isCdEncryption == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isCdEncryption == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isPrint == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isPrint == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isCdEnabled == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isCdEnabled == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isCdExport == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isCdExport == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isWlan == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isWlan == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isNetShare == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isNetShare == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isWebExport == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isWebExport == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isStorageExport == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isStorageExport == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isStorageAdmin == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isStorageAdmin == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isUsbBlock == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isUsbBlock == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	
	if(data.isComPortBlock == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isComPortBlock == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}

	if(data.isComPortBlock == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isComPortBlock == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}
	
	if(data.isNetPortBlock == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isNetPortBlock == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}
	
	if(data.isProcessList == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isProcessList == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}
	
	if(data.isFilePattern == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isFilePattern == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}
	
	if(data.isWebAddr == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isWebAddr == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}
	
	if(data.isWaterMark == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.isWaterMark == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}
		
	if(data.patternFileControl == true){
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell">에이전트삭제가능:</td><td>불허</td>';		
	}
	if(data.oldPolicy.patternFileControl == true){
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td style="padding-left:100px;" class="center-cell">프린트사용가능:</td><td>불허</td></tr>';			
	}
	
	
	
	sOut += '</table>';
	
	return sOut;

}
