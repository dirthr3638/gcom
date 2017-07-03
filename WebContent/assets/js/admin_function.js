
function getPolicyApplyData(){
	var map = new Object();
	
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
	map['patternFileControl'] 	= $('#chk_patternFileControl_item').is(':checked') == true ? 1 : 0 ;			// 민감파일 접근 시 삭제
	map['printLogDesc'] 		= $(':radio[name="radio_printLogDesc_item"]:checked').val();					// 프린터 인쇄 로그
	
	// USB 탭 데이터 Set Operation
	map['isUsbBlock']		= $('#att_usb_block_type').val();			// USB 차단정책
	
	// 시리얼 탭 데이터 Set Operation
	map['isComPortBlock']	= $('#att_com_port_type').val();			// 시리얼 포트 차단정책
	
	// 네트워크 탭 데이터 Set Operation
	map['isNetPortBlock']	= $('#att_net_port_type').val();			// 네트워크 포트 차단정책
	
	// 프로세스 탭 데이터 Set Operation
	map['isProcessList']	= $('#att_process_type').val();				// 프로세스 차단정책
	
	// 패턴 탭 데이터 Set Operation
	map['isFilePattern']	= $('#att_pattern_type').val();				// 민감 패턴 차단정책
	
	// 사이트 탭 데이터 Set Operation
	map['isWebAddr']		= $('#att_website_block_type').val();		// 사이트 차단정책
	
	// 메신저 탭 데이터 Set Operation
	map['isMsgBlock']		= $('#att_msg_block_type').val();			// 메신저 차단정책
	
	// 워터마크 탭 데이터 Set Operation // 워터마크 정책
	var isWaterMarkPrint	= $(':radio[name="radio_water_mark_print"]:checked').val();
	var waterMarkDate		= $('#att_waterMark_end_date').val(); 
	var waterMarkType		= $('#att_waterMark_type').val();
	var waterMaskPolicy = isWaterMarkPrint=='N'? 'N' : isWaterMarkPrint + "," + waterMarkDate + "," + waterMarkType;
	
	map['waterMark'] = waterMaskPolicy;									// 워터마크 정책
	
	return map;
}

function getApplyPolicyDetailItem(data){
	var sOut = '<table class="table table-bordered">';
				
	//sOut += '<tr><td class="center-cell">요청시간:</td><td>' + data.requestServerTime +'</td>';
	//sOut += '<td style="padding-left:100px;" class="center-cell">요청시간(PC):</td><td>' + data.requestClientTime +'</td></tr>';	
	sOut += '<col width="300px"><col><col width="300px"><col>';
		
	if(data.isUninstall == true){
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트삭제가능:</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트삭제가능:</td><td>불가</td>';		
	}

	if(data.isFileEncryption == true){
		sOut += '<td class="center-cell th-cell-gray">파일실시간암호화:</td><td>암호화</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">파일실시간암호화:</td><td>비암호화</td></tr>';			
	}

	if(data.isCdEncryption == true){
		sOut += '<tr><td class="center-cell th-cell-gray">CD실시간암호화</td><td>암호화</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">CD실시간암호화</td><td>비암호화</td>';		
	}
	
	if(data.isPrint == true){
		sOut += '<td class="center-cell th-cell-gray">프린트사용여부:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">프린트사용여부:</td><td>불가</td></tr>';			
	}
	
	if(data.isCdEnabled == true){
		sOut += '<tr><td class="center-cell th-cell-gray">CD사용가능여부:</td><td>허용</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">CD사용가능여부:</td><td>불가</td>';			
	}
	
	if(data.isCdExport == true){
		sOut += '<td class="center-cell th-cell-gray">CD반출여부:</td><td>허용</td></tr>';		
	}else{
		sOut += '<td class="center-cell th-cell-gray">CD반출여부:</td><td>차단</td></tr>';	
	}
	
	if(data.isWlan == true){
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜사용가능여부:</td><td>허용</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜사용가능여부:</td><td>차단</td>';	
	}
	
	if(data.isNetShare == true){
		sOut += '<td class="center-cell th-cell-gray">공유폴더사용여부:</td><td>사용</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">공유폴더사용여부:</td><td>미사용</td></tr>';			
	}
	
	if(data.isWebExport == true){
		sOut += '<tr><td class="center-cell th-cell-gray">메일반출여부:</td><td>허용</td>';		
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">메일반출여부:</td><td>차단</td>';	
	}
	
	if(data.isUsbBlock == true){
		sOut += '<td class="center-cell th-cell-gray">USB포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isUsbBlock' + ', '+ data.usbBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">USB포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isUsbBlock' + ', '+ data.usbBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td></tr>';		
	}
	
	if(data.isComPortBlock == true){
		sOut += '<tr><td class="center-cell th-cell-gray">시리얼포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isComPortBlock' + ', '+ data.comPortBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">시리얼포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isComPortBlock' + ', '+ data.comPortBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td>';		
	}
	
	if(data.isNetPortBlock == true){
		sOut += '<td class="center-cell th-cell-gray">네트워크포트사용여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isNetPortBlock' + ', '+ data.netPortBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">네트워크포트사용여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isNetPortBlock' + ', '+ data.netPortBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td></tr>';			
	}
	
	if(data.isProcessList == true){
		sOut += '<tr><td class="center-cell th-cell-gray">프로세스차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isProcessList' + ', '+ data.processListCode +');" ><i class="fa fa-search"></i> 상세</a></td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">프로세스차단여부:</td><td>허용 </td>';	
	}
	
	if(data.isFilePattern == true){
		var control_text = data.patternFileControl == 0 ? "격리" : "삭제";
		sOut += '<td class="center-cell th-cell-gray">민감패턴차단여부:</td><td>차단 [검출패턴처리 : '+ control_text + '] &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isFilePattern' + ', '+ data.filePatternCode +');" ><i class="fa fa-search"></i> 상세</a></td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">민감패턴차단여부:</td><td>허용 </td></tr>';	
	}
	
	if(data.isWebAddr == true){
		sOut += '<tr><td class="center-cell th-cell-gray">사이트차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isWebAddr' + ', '+ data.webAddrCode +');" ><i class="fa fa-search"></i> 상세</a></td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">사이트차단여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isWebAddr' + ', '+ data.webAddrCode +');" ><i class="fa fa-search"></i> 상세</a></td>';			
	}
	
	if(data.isMsgBlock == true){
		sOut += '<td class="center-cell th-cell-gray">메신저차단여부:</td><td>차단 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isMsgBlock' + ', '+ data.msgBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td></tr>';
	}else{
		sOut += '<td class="center-cell th-cell-gray">메신저차단여부:</td><td>허용 &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isMsgBlock' + ', '+ data.msgBlockCode +');" ><i class="fa fa-search"></i> 상세</a></td></tr>';
	}
	
	if(data.isWaterMark == true){
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크사용여부:</td><td>허용 ['+ data.waterMarkEndDate +'까지] &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:fn_sel_policy_detailOpen(' + 'isWaterMark' + ', '+ data.waterMarkType +');" ><i class="fa fa-search"></i> 상세</a></td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크사용여부:</td><td>불가</td>';			
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
		sOut += '<i class="fa fa-trash policy_icon" style="color:#7ed67e " title="에이전트삭제가능"></i>';
	}else{
		sOut += '<i class="fa fa-trash policy_icon" style="color:#ea6a66" title="에이전트삭제불가"></i>';
	}

	if(data.isFileEncryption == true){
		sOut += '<i class="fa fa-file policy_icon" aria-hidden="true" style="color:#7ed67e" title="실시간파일암호화"></i>';
	}else{
		sOut += '<i class="fa fa-file policy_icon" aria-hidden="true" style="color:#ea6a66" title="실시간파일 비암호화"></i>';
	}

	if(data.isCdEncryption == true){
		sOut += '<i class="fa fa-get-pocket policy_icon" aria-hidden="true" style="color:#7ed67e" title="CD암호화"></i>'
	}else{
		sOut += '<i class="fa fa-get-pocket policy_icon" aria-hidden="true" style="color:#ea6a66" title="CD비암호화"></i>';
	}
	
	if(data.isPrint == true){
		sOut += '<i class="fa fa-print policy_icon" style="color:#7ed67e" title="프린트가능"></i>';
	}else{
		sOut += '<i class="fa fa-print policy_icon" style="color:#ea6a66" title="프린트불가"></i>';
	}
	
	if(data.isCdEnabled == true){
		sOut += '<i class="fa fa-database policy_icon" aria-hidden="true" style="color:#7ed67e" title="CD사용허용"></i>';
	}else{
		sOut += '<i class="fa fa-database policy_icon" aria-hidden="true" style="color:#ea6a66" title="CD사용차단"></i>';
	}
	
	if(data.isCdExport == true){
		sOut += '<i class="fa fa-minus-circle policy_icon" aria-hidden="true" style="color:#7ed67e" title="CD반출허용"></i>';
	}else{
		sOut += '<i class="fa fa-minus-circle policy_icon" aria-hidden="true" style="color:#ea6a66" title="CD반출차단"></i>';
	}
	
	if(data.isWlan == true){
		sOut += '<i class="fa fa-wifi policy_icon" style="color:#7ed67e" title="무선랜허용"></i>';
	}else{
		sOut += '<i class="fa fa-wifi policy_icon" style="color:#ea6a66" title="무선랜차단"></i>';
	}
	
	if(data.isNetShare == true){
		sOut += '<i class="fa fa-folder-open policy_icon" style="color:#7ed67e" title="공유폴더허용"></i>';
	}else{
		sOut += '<i class="fa fa-folder-open policy_icon" style="color:#ea6a66" title="공유폴더차단"></i>';
	}
	
	if(data.isWebExport == true){
		sOut += '<i class="fa fa-envelope policy_icon" style="color:#7ed67e" title="메일반출허용"></i>';	
	}else{
		sOut += '<i class="fa fa-envelope policy_icon" style="color:#ea6a66" title="메일반출차단"></i>';
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


