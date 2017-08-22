
function getPolicyApplyData(flag){
	var map = new Object();
	
	if (flag == 'true') {
		console.log("solo")
		// 기본 탭 데이터 Set Operation
		map['isUninstall'] 			= $('#chk_isUninstall_item').is(':checked') == true ? 1 : 0 ;					// 에이전트 삭제 가능 여부
		map['isFileEncryption']		= $('#chk_isFileEncryption_item').is(':checked') == true ? 1 : 0 ;				// 파일실시간 암호화 여부
		map['isCdEncryption'] 		= $('#chk_isCdEncryption_item').is(':checked') == true ? 1 : 0 ;				// CD실시간 암호화 여부
		map['isPrint'] 				= $('#chk_isPrint_item').is(':checked') == true ? 1 : 0 ;						// 프린터 사용 여부	
		map['isCdEnabled'] 			= $('#chk_isCdEnabled_item').is(':checked') == true ? 1 : 0 ;					// CD 사용여부
		map['isCdExport'] 			= $('#chk_isCdExport_item').is(':checked') == true ? 1 : 0 ;					// CD 반출 여부
		map['isWlan'] 				= $('#chk_isWlan_item').is(':checked') == true ? 1 : 0 ;						// 무선랜 사용 여부
		map['isNetShare'] 			= $('#chk_isNetShare_item').is(':checked') == true ? 1 : 0 ;					// 공유폴더 사용 여부
		map['isWebExport'] 			= $('#chk_isWebExport_item').is(':checked') == true ? 1 : 0 ;					// 메일 반출 여부
				
		map['isSensitiveDirEnabled'] = $('#chk_isSensitiveDir_item').is(':checked') == true ? 1 : 0 ;				// 보호폴더 접근 사용여부
		map['isSensitiveFileAccess'] = $('#chk_isSensitiveFileAccess_item').is(':checked') == true ? 1 : 0 ;		// 민감파일 접근 여부
		map['isStorageExport'] 		= $('#chk_isStorageExport_item').is(':checked') == true ? 1 : 0 ;				// 디스크반출가능 여부
		map['isStorageAdmin'] 		= $('#chk_isStorageAdmin_item').is(':checked') == true ? 1 : 0 ;				// 디스크 관리자 여부
		map['isUsbControlEnabled'] 	= $('#chk_isUsbControl_item').is(':checked') == true ? 1 : 0 ;					// USB통제 여부
		map['patternFileControl'] 	= $('#chk_patternFileControl_item').is(':checked') == true ? 1 : 0 ;			// 민감파일 접근 시 삭제
	
	} else {
		console.log("many")
		// 기본 탭 데이터 Set Operation
		map['isUninstall'] 			= $('#chk_isUninstall_item').val();					// 에이전트 삭제 가능 여부
		map['isFileEncryption']		= $('#chk_isFileEncryption_item').val();			// 파일실시간 암호화 여부
		map['isCdEncryption'] 		= $('#chk_isCdEncryption_item').val();				// CD실시간 암호화 여부
		map['isPrint'] 				= $('#chk_isPrint_item').val();						// 프린터 사용 여부	
		map['isCdEnabled'] 			= $('#chk_isCdEnabled_item').val();					// CD 사용여부
		map['isCdExport'] 			= $('#chk_isCdExport_item').val();					// CD 반출 여부
		map['isWlan'] 				= $('#chk_isWlan_item').val();						// 무선랜 사용 여부
		map['isNetShare'] 			= $('#chk_isNetShare_item').val();					// 공유폴더 사용 여부
		map['isWebExport'] 			= $('#chk_isWebExport_item').val();					// 메일 반출 여부

		map['isSensitiveDirEnabled'] = $('#chk_isSensitiveDir_item').val();				// 보호폴더 접근 사용여부
		map['isSensitiveFileAccess'] = $('#chk_isSensitiveFileAccess_item').val();		// 민감파일 접근 여부
		map['isStorageExport'] 		= $('#chk_isStorageExport_item').val();				// 디스크반출가능 여부
		map['isStorageAdmin'] 		= $('#chk_isStorageAdmin_item').val();				// 디스크 관리자 여부
		map['isUsbControlEnabled'] 	= $('#chk_isUsbControl_item').val();				// USB통제 여부
		map['patternFileControl'] 	= $('#chk_patternFileControl_item').val();			// 민감파일 접근 시 삭제
	
	}
	 
	map['printLogDesc'] 		= $(':radio[name="radio_printLogDesc_item"]').is(':checked') == true ? $(':radio[name="radio_printLogDesc_item"]:checked').val() : -1 ;				// 프린터 인쇄 로그
	
	// USB 탭 데이터 Set Operation
	map['isUsbBlock']		= $('#att_usb_block_type').val();			// USB 차단정책
	
	// 시리얼 탭 데이터 Set Operation
	map['isComPortBlock']	= $('#att_com_port_type').val();			// 시리얼 포트 차단정책
	
	// 네트워크 탭 데이터 Set Operation
	map['isNetPortBlock']	= $('#att_net_port_type').val();			// 네트워크 포트 차단정책
	
	// 프로세스 탭 데이터 Set Operation
	map['isProcessList']	= $('#att_process_type').val();				// 프로세스 차단정책
	map['isProcessCheck']   = $(':radio[name="radio_process_block"]').is(':checked') == true ? 1 : -1 ;
	
	// 패턴 탭 데이터 Set Operation
	map['isFilePattern']	= $('#att_pattern_type').val();				// 민감 패턴 차단정책
	map['isPatternCheck']   = $(':radio[name="radio_pattern_block"]').is(':checked') == true ? 1 : -1 ;
	
	// 사이트 탭 데이터 Set Operation
	map['isWebAddr']		= $('#att_website_block_type').val();		// 사이트 차단정책
	
	// 메신저 탭 데이터 Set Operation
	map['isMsgBlock']		= $('#att_msg_block_type').val();			// 메신저 차단정책
	
	// 워터마크 탭 데이터 Set Operation // 워터마크 정책
	var isWaterMarkPrint	= $(':radio[name="radio_water_mark_print"]:checked').val();	
	var waterMarkCheck = $(':radio[name="radio_water_mark_print"]').is(':checked') == true ? 1 : -1 ;				
	map['isWaterMarkPrint'] = waterMarkCheck;
	var waterMarkDate		= $('#att_waterMark_end_date').val();
	var waterMaekTime		= $('#att_waterMark_end_time').val();
	
	// 워터마크 검증을 위한 데이터
	map['waterPolicyValue'] = isWaterMarkPrint;
	map['waterMarkDate'] = waterMarkDate;
	map['waterMaekTime'] = waterMaekTime;
	
	if (waterMaekTime != "") {
		waterMaekTime = getFormatTime(waterMaekTime);
	}
	
	var waterMarkType		= $('#att_waterMark_type').val();			// 워터마크 타입
	var waterMaskPolicy = '';
	
	if (waterMarkCheck != -1) {
		waterMaskPolicy = isWaterMarkPrint=='N'? isWaterMarkPrint : isWaterMarkPrint + "," + waterMarkDate + " " + waterMaekTime + "," + waterMarkType;
	} else {
		waterMaskPolicy = '';
	}
	
	map['waterMark'] = waterMaskPolicy;									// 워터마크 정책
	
	return map;
}

function getFormatTime(time) {
	var splitTimeArray = time.split(':');
	var hour = splitTimeArray[0].trim();
	var min = splitTimeArray[1].trim();
	var checkTime = splitTimeArray[2].trim();
	
	var changeHour = '';
	
	if (checkTime == 'PM') {
		if( parseInt(hour) != 12) {
			changeHour = (parseInt(hour) + 12).toString() ;
		} else {
			changeHour = "12";
		}
	} else {
		if(parseInt(hour) == 12) {
			changeHour = "00";
		} else {
			changeHour = hour;
		}
	}
	
	return changeHour+":"+min;
}

function getApplyPolicyDetailItem(data){
	var sOut = '<table class="table table-bordered">';
				
	//sOut += '<tr><td class="center-cell">요청시간:</td><td>' + data.requestServerTime +'</td>';
	//sOut += '<td style="padding-left:100px;" class="center-cell">요청시간(PC):</td><td>' + data.requestClientTime +'</td></tr>';	
	sOut += '<col width="300px"><col><col width="300px"><col>';
		
	if(data.isUninstall == true){
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트 삭제 가능 여부:</td><td>가능</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트 삭제 가능 여부:</td><td>불가능</td>';		
	}

	if(data.isFileEncryption == true){
		sOut += '<td class="center-cell th-cell-gray">파일 암호화 사용:</td><td>사용</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">파일 암호화 사용:</td><td>미사용</td></tr>';			
	}

	if(data.isCdEncryption == true){
		sOut += '<tr><td class="center-cell th-cell-gray">CD 암호화 사용:</td><td>사용</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">CD 암호화 사용:</td><td>미사용</td>';		
	}
	
	if(data.isPrint == true){
		sOut += '<td class="center-cell th-cell-gray">프린터 사용 가능 여부:</td><td>가능</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">프린트 사용 가능 여부:</td><td>불가능</td></tr>';			
	}
	
	if(data.isCdEnabled == true){
		sOut += '<tr><td class="center-cell th-cell-gray">CD 사용 여부:</td><td>사용</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">CD 사용 여부:</td><td>미사용</td>';			
	}
	
	if(data.isCdExport == true){
		sOut += '<td class="center-cell th-cell-gray">CD 반출 가능 여부:</td><td>가능</td></tr>';		
	}else{
		sOut += '<td class="center-cell th-cell-gray">CD 반출 가능 여부:</td><td>불가능</td></tr>';	
	}
	
	if(data.isWlan == true){
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜 사용 여부:</td><td>사용</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜 사용 여부:</td><td>미사용</td>';	
	}
	
	if(data.isNetShare == true){
		sOut += '<td class="center-cell th-cell-gray">공유폴더 사용 여부:</td><td>사용</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">공유폴더 사용 여부:</td><td>미사용</td></tr>';			
	}
	
	if(data.isWebExport == true){
		sOut += '<tr><td class="center-cell th-cell-gray">메일반출 사용 여부:</td><td>사용</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">메일반출 사용 여부:</td><td>미사용</td>';	
	}
	
	if(data.isSensitiveDirEnabled == true){
		sOut += '<td class="center-cell th-cell-gray">보호폴더 접근 가능 여부:</td><td>가능</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">보호폴더 접근 가능 여부:</td><td>불가능</td></tr>';		
	}
	
	if(data.isSensitiveFileAccess == true){
		sOut += '<tr><td class="center-cell th-cell-gray">민감파일 접근시 삭제 여부:</td><td>삭제</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">민감파일 접근시 삭제 여부:</td><td>보호폴더로 이동</td>';	
	}
	
	if(data.isStorageExport == true){
		sOut += '<td class="center-cell th-cell-gray">디스크 반출 가능 여부:</td><td>가능</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">디스크 반출 가능 여부:</td><td>불가능</td></tr>';		
	}
	
	if(data.isStorageAdmin == true){
		sOut += '<tr><td class="center-cell th-cell-gray">디스크 관리자 여부:</td><td>관리자</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">디스크 관리자 여부:</td><td>일반</td>';	
	}
	
	if(data.isUsbControlEnabled == true){
		sOut += '<td class="center-cell th-cell-gray">USB통제 기능 사용 여부:</td><td>사용</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">USB통제 기능 사용 여부:</td><td>미사용</td></tr>';		
	}
	
	if(data.patternFileControl == 1){
		sOut += '<tr><td class="center-cell th-cell-gray">검출된 패턴파일 삭제 여부:</td><td>삭제</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">검출된 패턴파일 삭제 여부:</td><td>격리</td>';	
	}
	
	if(data.isUsbBlock == true){
		sOut += '<td class="center-cell th-cell-gray">USB포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isUsbBlock\'' + ', \''+ data.usbBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">USB포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isUsbBlock\'' + ', \''+ data.usbBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td></tr>';		
	}
	
	if(data.isComPortBlock == true){
		sOut += '<tr><td class="center-cell th-cell-gray">시리얼포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isComPortBlock\'' + ', \''+ data.comPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">시리얼포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isComPortBlock\'' + ', \''+ data.comPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td>';		
	}
	
	if(data.isNetPortBlock == true){
		sOut += '<td class="center-cell th-cell-gray">네트워크포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isNetPortBlock\'' + ', \''+ data.netPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">네트워크포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isNetPortBlock\'' + ', \''+ data.netPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td></tr>';			
	}
	
	if(data.isProcessList == true){
		sOut += '<tr><td class="center-cell th-cell-gray">프로세스차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isProcessList\'' + ', \''+ data.processListCode +'\');" ><i class="fa fa-search"></i> 상세</a></td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">프로세스차단여부:</td><td>허용 </td>';	
	}
	
	if(data.isFilePattern == true){
		sOut += '<td class="center-cell th-cell-gray">민감패턴차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isFilePattern\'' + ', \''+ data.filePatternCode +'\');" ><i class="fa fa-search"></i> 상세</a></td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">민감패턴차단여부:</td><td>허용 </td></tr>';	
	}
	
	if(data.isWebAddr == true){
		sOut += '<tr><td class="center-cell th-cell-gray">사이트차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWebAddr\'' + ', \''+ data.webAddrCode +'\');" ><i class="fa fa-search"></i> 상세</a></td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">사이트차단여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWebAddr\'' + ', \''+ data.webAddrCode +'\');" ><i class="fa fa-search"></i> 상세</a></td>';			
	}
	
	if(data.isMsgBlock == true){
		sOut += '<td class="center-cell th-cell-gray">메신저차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isMsgBlock\'' + ', \''+ data.msgBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td></tr>';
	}else{
		sOut += '<td class="center-cell th-cell-gray">메신저차단여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isMsgBlock\'' + ', \''+ data.msgBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></td></tr>';
	}
	
	if(data.isWaterMark == true){
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크사용여부:</td><td>사용 ['+ data.waterMarkEndDate +'까지] &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWaterMark\'' + ', \''+ data.waterMarkType +'\');" ><i class="fa fa-search"></i> 상세</a></td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크사용여부:</td><td>미사용</td>';			
	}
	
	if(data.printLogDesc == 0){
		sOut += '<td class="center-cell th-cell-gray">프린터 인쇄 로그:</td><td>로그전송안함</td></tr>';	
	}else if (data.printLogDesc == 1) {
		sOut += '<td class="center-cell th-cell-gray">프린터 인쇄 로그:</td><td>이벤트로그</td></tr>';			
	} else {
		sOut += '<td class="center-cell th-cell-gray">프린터 인쇄 로그:</td><td>파일원본로그</td></tr>';		
	}
	
	sOut += '</table>';
	
	return sOut;

}

function getPolicyIcon(data){
	var sOut = '';
	
	if(data.isUninstall == true){
		sOut += '<i class="fa fa-trash policy_icon" style="color:#7ed67e" title="에이전트삭제가능"></i>';
	}else{
		sOut += '<i class="fa fa-trash policy_icon" style="color:#ea6a66" title="에이전트삭제불가능"></i>';
	}

	if(data.isFileEncryption == true){
		sOut += '<i class="fa fa-file policy_icon" aria-hidden="true" style="color:#7ed67e" title="파일암호화사용"></i>';
	}else{
		sOut += '<i class="fa fa-file policy_icon" aria-hidden="true" style="color:#ea6a66" title="파일암호화미사용"></i>';
	}

	if(data.isCdEncryption == true){
		sOut += '<i class="fa fa-get-pocket policy_icon" aria-hidden="true" style="color:#7ed67e" title="CD암호화사용"></i>'
	}else{
		sOut += '<i class="fa fa-get-pocket policy_icon" aria-hidden="true" style="color:#ea6a66" title="CD암호화미사용"></i>';
	}
	
	if(data.isPrint == true){
		sOut += '<i class="fa fa-print policy_icon" style="color:#7ed67e" title="프린터사용가능"></i>';
	}else{
		sOut += '<i class="fa fa-print policy_icon" style="color:#ea6a66" title="프린터사용불가능"></i>';
	}
	
	if(data.isCdEnabled == true){
		sOut += '<i class="fa fa-database policy_icon" aria-hidden="true" style="color:#7ed67e" title="CD사용"></i>';
	}else{
		sOut += '<i class="fa fa-database policy_icon" aria-hidden="true" style="color:#ea6a66" title="CD미사용"></i>';
	}
	
	if(data.isCdExport == true){
		sOut += '<i class="fa fa-minus-circle policy_icon" aria-hidden="true" style="color:#7ed67e" title="CD반출가능"></i>';
	}else{
		sOut += '<i class="fa fa-minus-circle policy_icon" aria-hidden="true" style="color:#ea6a66" title="CD반출불가능"></i>';
	}
	
	if(data.isWlan == true){
		sOut += '<i class="fa fa-wifi policy_icon" style="color:#7ed67e" title="무선랜사용"></i>';
	}else{
		sOut += '<i class="fa fa-wifi policy_icon" style="color:#ea6a66" title="무선랜미사용"></i>';
	}
	
	if(data.isNetShare == true){
		sOut += '<i class="fa fa-share-alt policy_icon" style="color:#7ed67e" title="공유폴더사용"></i>';
	}else{
		sOut += '<i class="fa fa-share-alt policy_icon" style="color:#ea6a66" title="공유폴더미사용"></i>';
	}
	
	if(data.isWebExport == true){
		sOut += '<i class="fa fa-envelope policy_icon" style="color:#7ed67e" title="메일반출사용"></i>';	
	}else{
		sOut += '<i class="fa fa-envelope policy_icon" style="color:#ea6a66" title="메일반출미사용"></i>';
	}
	
	if(data.isSensitiveDirEnabled == true){
		sOut += '<i class="fa fa-folder-open policy_icon" style="color:#7ed67e" title="보호폴더접근가능"></i>';	
	}else{
		sOut += '<i class="fa fa-folder-open policy_icon" style="color:#ea6a66" title="보호폴더접근불가능"></i>';		
	}
	
	if(data.isSensitiveFileAccess == true){
		sOut += '<i class="fa fa-file-archive-o policy_icon" style="color:#7ed67e" title="민감파일접근시삭제"></i>';		
	}else{
		sOut += '<i class="fa fa-file-archive-o policy_icon" style="color:#ea6a66" title="민감파일접근시보호폴더로이동"></i>';	
	}
	
	if(data.isStorageExport == true){
		sOut += '<i class="fa fa-archive policy_icon" style="color:#7ed67e" title="디스크반출가능"></i>';	
	}else{
		sOut += '<i class="fa fa-archive policy_icon" style="color:#ea6a66" title="디스크반출불가능"></i>';		
	}
	
	if(data.isStorageAdmin == true){
		sOut += '<i class="fa fa-address-card policy_icon" style="color:#7ed67e" title="디스크관리가능"></i>';		
	}else{
		sOut += '<i class="fa fa-address-card policy_icon" style="color:#ea6a66" title="디스크관리불가"></i>';	
	}
	
	if(data.isUsbControlEnabled == true){
		sOut += '<i class="fa fa-cogs policy_icon" style="color:#7ed67e" title="USB통제기능사용"></i>';	
	}else{
		sOut += '<i class="fa fa-cogs policy_icon" style="color:#ea6a66" title="USB통제기능미사용"></i>';		
	}
	
	if(data.patternFileControl == 1){
		sOut += '<i class="fa fa-file-powerpoint-o policy_icon" style="color:#7ed67e" title="검출된패턴파일삭제"></i>';		
	}else{
		sOut += '<i class="fa fa-file-powerpoint-o policy_icon" style="color:#ea6a66" title="검출된패턴파일격리"></i>';	
	}
	
	if(data.isUsbBlock == true){
		sOut += '<i class="fa fa-usb policy_icon" style="color:#ea6a66" title="USB포트차단"></i>';
	}else{
		sOut += '<i class="fa fa-usb policy_icon" style="color:#7ed67e" title="USB포트허용"></i>';
	}
	
	if(data.isComPortBlock == true){
		sOut += '<i class="fa fa-plug policy_icon" style="color:#ea6a66" title="시리얼포트차단"></i>';
	}else{
		sOut += '<i class="fa fa-plug policy_icon" style="color:#7ed67e" title="시리얼포트허용"></i>';
	}
	
	if(data.isNetPortBlock == true){
		sOut += '<i class="fa fa-sitemap policy_icon" aria-hidden="true" style="color:#ea6a66" title="네트워크포트차단"></i>';	
	}else{
		sOut += '<i class="fa fa-sitemap policy_icon" aria-hidden="true" style="color:#7ed67e" title="네트워크포트허용"></i>';
	}
	
	if(data.isProcessList == true){ 
		sOut += '<i class="fa fa-desktop policy_icon" aria-hidden="true" style="color:#ea6a66" title="프로세스사용차단"></i>';
	}else{
		sOut += '<i class="fa fa-desktop policy_icon" aria-hidden="true" style="color:#7ed67e" title="프로세스사용허용"></i>';	
	}
	
	if(data.isFilePattern == true){
		sOut += '<i class="fa fa-clone policy_icon" aria-hidden="true" style="color:#ea6a66" title="민감정보파일차단"></i>';
	}else{
		sOut += '<i class="fa fa-clone policy_icon" aria-hidden="true" style="color:#7ed67e" title="민감정보파일허용"></i>';
	}
	
	if(data.isWebAddr == true){
		sOut += '<i class="fa fa-internet-explorer policy_icon" aria-hidden="true" style="color:#ea6a66" title="사이트차단"></i>';
	}else{
		sOut += '<i class="fa fa-internet-explorer policy_icon" aria-hidden="true" style="color:#7ed67e" title="사이트허용"></i>';	
	}
	
	if(data.isMsgBlock == true){
		sOut += '<i class="fa fa-commenting policy_icon" aria-hidden="true" style="color:#ea6a66" title="메신저차단"></i>';
	}else{
		sOut += '<i class="fa fa-commenting policy_icon" aria-hidden="true" style="color:#7ed67e" title="메신저허용"></i>';
	}
	
	if(data.isWaterMark == true){
		sOut += '<i class="fa fa-tint policy_icon" style="color:#7ed67e" title="워터마크출력"></i>';
	}else{
		sOut += '<i class="fa fa-tint policy_icon" style="color:#ea6a66" title="워터마크미출력"></i>';
	}
	
	return sOut;

}

function getRequestPolicyDetailTable(data){
	var sOut = '';
	/*sOut += '<button type="button" class="btn btn-blue pull-right" onClick="javascript:fn_permit_request_policy(' + data.oldPolicy.policyNo + ','+ data.requestNo +');" style="margin:0 0 8px 0px;">요청정책승인</button>';*/
	
	sOut += '<table class="table table-bordered">';			
	sOut += '<col width="15%"><col width="35%"><col width="15%"><col width="35%">';
	sOut += '<tr><td class="center-cell th-cell-gray"><b>요청사유</b></td><td colspan="3" >'+ data.reqNotice +'</td></tr>';
	
	sOut += '<tr><td class="center-cell th-cell-gray"><b>정책명</b></td><td class="center-cell th-cell-gray"><b>기존 및 요청 정책</b></td>';
	sOut +=	'<td class="center-cell th-cell-gray"><b>정책명</b></td><td class="center-cell th-cell-gray"><b>기존 및 요청 정책</b></td></tr>';
		
	if(data.oldPolicy.isUninstall == true){
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트삭제가능:</td><td>허용';
		if (data.isUninstall == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>불가</span>';
		}
		sOut += '</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트삭제가능:</td><td>불가';	
		if (data.isUninstall == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td>';
	}

	if(data.oldPolicy.isFileEncryption == true){
		sOut += '<td class="center-cell th-cell-gray">파일실시간암호화:</td><td>암호화';
		if (data.isFileEncryption == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>비암호화</span>';
		}
		sOut += '</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">파일실시간암호화:</td><td>비암호화';
		if (data.isFileEncryption == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>암호화</span>';
		}
		sOut += '</td></tr>';			
	}

	if(data.oldPolicy.isCdEncryption == true){
		sOut += '<tr><td class="center-cell th-cell-gray">CD실시간암호화</td><td>암호화';
		if (data.isCdEncryption == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>비암호화</span>';
		}
		sOut += '</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">CD실시간암호화</td><td>비암호화';
		if (data.isCdEncryption == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>암호화</span>';
		}
		sOut += '</td>';		
	}
	
	if(data.oldPolicy.isPrint == true){
		sOut += '<td class="center-cell th-cell-gray">프린트사용여부:</td><td>허용';
		if (data.isPrint == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>불가</span>';
		}
		sOut += '</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">프린트사용여부:</td><td>불가';
		if (data.isPrint == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td></tr>';			
	}
	
	if(data.oldPolicy.isCdEnabled == true){
		sOut += '<tr><td class="center-cell th-cell-gray">CD사용가능여부:</td><td>허용';
		if (data.isCdEnabled == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>불가</span>';
		}
		sOut += '</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">CD사용가능여부:</td><td>불가';
		if (data.isCdEnabled == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td>';			
	}
	
	if(data.oldPolicy.isCdExport == true){
		sOut += '<td class="center-cell th-cell-gray">CD반출여부:</td><td>허용';
		if (data.isCdExport == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단</span>';
		}
		sOut += '</td></tr>';		
	}else{
		sOut += '<td class="center-cell th-cell-gray">CD반출여부:</td><td>차단';
		if (data.isCdExport == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td></tr>';	
	}
	
	if(data.oldPolicy.isWlan == true){
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜사용가능여부:</td><td>허용';
		if (data.isWlan == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단</span>';
		}
		sOut += '</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜사용가능여부:</td><td>차단';
		if (data.isWlan == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td>';	
	}
	
	if(data.oldPolicy.isNetShare == true){
		sOut += '<td class="center-cell th-cell-gray">공유폴더사용여부:</td><td>사용';
		if (data.isNetShare == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>미사용</span>';
		}
		sOut += '</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">공유폴더사용여부:</td><td>미사용';
		if (data.isWlan == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>사용</span>';
		}
		sOut += '</td></tr>';	
	}
	
	if(data.oldPolicy.isWebExport == true){
		sOut += '<tr><td class="center-cell th-cell-gray">메일반출여부:</td><td>허용';
		if (data.isWebExport == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단</span>';
		}
		sOut += '</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">메일반출여부:</td><td>차단';
		if (data.isWebExport == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td>';	
	}
	
	if(data.oldPolicy.isUsbBlock == true){
		sOut += '<td class="center-cell th-cell-gray">USB포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isUsbBlock\'' + ', \''+ data.oldPolicy.usbBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isUsbBlock == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isUsbBlock\'' + ', \''+ data.usbBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">USB포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isUsbBlock\'' + ', \''+ data.oldPolicy.usbBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isUsbBlock == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isUsbBlock\'' + ', \''+ data.usbBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td></tr>';		
	}
	
	if(data.oldPolicy.isComPortBlock == true){
		sOut += '<tr><td class="center-cell th-cell-gray">시리얼포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isComPortBlock\'' + ', \''+ data.oldPolicy.comPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isComPortBlock == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isComPortBlock\'' + ', \''+ data.comPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">시리얼포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isComPortBlock\'' + ', \''+ data.oldPolicy.comPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isComPortBlock == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isComPortBlock\'' + ', \''+ data.comPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td>';		
	}
	
	if(data.oldPolicy.isNetPortBlock == true){
		sOut += '<td class="center-cell th-cell-gray">네트워크포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isNetPortBlock\'' + ', \''+ data.oldPolicy.netPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isNetPortBlock == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isNetPortBlock\'' + ', \''+ data.netPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">네트워크포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isNetPortBlock\'' + ', \''+ data.oldPolicy.netPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isNetPortBlock == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isNetPortBlock\'' + ', \''+ data.netPortBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td></tr>';			
	}
	
	if(data.oldPolicy.isProcessList == true){
		sOut += '<tr><td class="center-cell th-cell-gray">프로세스차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isProcessList\'' + ', \''+ data.oldPolicy.processListCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isProcessList == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">프로세스차단여부:</td><td>허용'; 
		if (data.isProcessList == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isProcessList\'' + ', \''+ data.processListCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td>';	
	}
	
	// 추후 검출패턴 처리 관련하여 수해야함
	if(data.oldPolicy.isFilePattern == true){
		var control_text = data.oldPolicy.patternFileControl == 0 ? "격리" : "삭제";
		sOut += '<td class="center-cell th-cell-gray">민감패턴차단여부:</td><td>차단 [검출패턴처리 : '+ control_text + '] &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isFilePattern\'' + ', \''+ data.oldPolicy.filePatternCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isFilePattern == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용</span>';
		}
		sOut += '</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">민감패턴차단여부:</td><td>허용'; 
		if (data.isFilePattern == true) {
			var control_text = data.patternFileControl == 0 ? "격리" : "삭제";
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단 [검출패턴처리 : '+ control_text + '] &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isFilePattern\'' + ', \''+ data.filePatternCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		} 
		sOut += '</td></tr>';	
	}
	
	if(data.oldPolicy.isWebAddr == true){
		sOut += '<tr><td class="center-cell th-cell-gray">사이트차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWebAddr\'' + ', \''+ data.oldPolicy.webAddrCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isWebAddr == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWebAddr\'' + ', \''+ data.webAddrCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">사이트차단여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWebAddr\'' + ', \''+ data.oldPolicy.webAddrCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isWebAddr == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWebAddr\'' + ', \''+ data.webAddrCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		} 
		sOut += '</td>';			
	}
	
	if(data.oldPolicy.isMsgBlock == true){
		sOut += '<td class="center-cell th-cell-gray">메신저차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isMsgBlock\'' + ', \''+ data.oldPolicy.msgBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isMsgBlock == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isMsgBlock\'' + ', \''+ data.msgBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		} 
		sOut += '</td></tr>';
	}else{
		sOut += '<td class="center-cell th-cell-gray">메신저차단여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isMsgBlock\'' + ', \''+ data.oldPolicy.msgBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isMsgBlock == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isMsgBlock\'' + ', \''+ data.msgBlockCode +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		} 
		sOut += '</td></tr>';
	}
	
	if(data.oldPolicy.isWaterMark == true){
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크사용여부:</td><td>사용 ['+ data.oldPolicy.waterMarkEndDate +'까지] &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWaterMark\'' + ', \''+ data.oldPolicy.waterMarkType +'\');" ><i class="fa fa-search"></i> 상세</a>';
		if (data.isWaterMark == false) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>미사용</span>';
		}
		sOut += '</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크사용여부:</td><td>미사용';
		if (data.isWaterMark == true) {
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>사용 ['+ data.waterMarkEndDate +'까지] &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + '\'isWaterMark\'' + ', \''+ data.waterMarkType +'\');" ><i class="fa fa-search"></i> 상세</a></span>';
		}
		sOut += '</td>';			
	}
	
	if(data.oldPolicy.printLogDesc == 0){
		sOut += '<td class="center-cell th-cell-gray">프린터 인쇄 로그:</td><td>로그전송안함';
		if (data.printLogDesc != 0) {
			var strPLD = data.printLogDesc == 1? '이벤트로그' : '파일원본로그';
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>'+ strPLD +'</span>';
		}
		sOut += '</td></tr>';	
	}else if (data.oldPolicy.printLogDesc == 1) {
		sOut += '<td class="center-cell th-cell-gray">프린터 인쇄 로그:</td><td>이벤트로그';
		if (data.printLogDesc != 1) {
			var strPLD = data.printLogDesc == 2? '파일원본로그' : '로그전송안함';
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>'+ strPLD +'</span>';
		}	 
		sOut += '</td></tr>';			
	} else if (data.oldPolicy.printLogDesc == 2) {
		sOut += '<td class="center-cell th-cell-gray">프린터 인쇄 로그:</td><td>파일원본로그';
		if (data.printLogDesc != 2) {
			var strPLD = data.printLogDesc == 0? '로그전송안함' : '이벤트로그';
			sOut += '<span><i class="fa fa-arrow-right" aria-hidden="true" style="margin:0 10px; color:#fb827a;"></i>'+ strPLD +'</span>';
		}
		sOut += '</td></tr>';		
	}
	
	sOut += '</table>';
	
	return sOut;

}

	function completeAlert(){
		vex.defaultOptions.className = 'vex-theme-os'
			
		vex.dialog.open({
			message: '완료되었습니다.',
			  buttons: [
			    $.extend({}, vex.dialog.buttons.YES, {
			      text: '확인'
			  })]
		})
	}
	
 	function failAlert(){
		vex.defaultOptions.className = 'vex-theme-os'
			
			vex.dialog.open({
				message: '서버와의 통신에 문제가 발생하였습니다.',
				  buttons: [
				    $.extend({}, vex.dialog.buttons.YES, {
				      text: '확인'
				  })]
			})
 	}
 	
 	function infoAlert(str){
		vex.defaultOptions.className = 'vex-theme-os'
			
			vex.dialog.open({
				message: str,
				  buttons: [
				    $.extend({}, vex.dialog.buttons.YES, {
				      text: '확인'
				  })]
			})
 	}
 	
 	
 	
