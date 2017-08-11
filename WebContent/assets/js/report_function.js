function getPolicyDetailTable(data){
	var sOut = '<table class="table table-bordered">';
	sOut += '<col width="25%"><col width="25%"><col width="25%"><col width="25%">'
	sOut += '<tr><td class="center-cell th-cell-gray">요청시간</td><td>' + data.requestServerTime +'</td>';
	sOut += '<td class="center-cell th-cell-gray">요청시간(PC)</td><td>' + data.requestClientTime +'</td></tr>';	

	if(data.uninstall_enabled == true){
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트삭제가능</td><td>허용</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">에이전트삭제가능</td><td>불허</td>';		
	}

	if(data.isPrint == true){
		sOut += '<td class="center-cell th-cell-gray">프린트사용가능</td><td>허용</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">프린트사용가능</td><td>불허</td></tr>';			
	}

	if(data.isWaterMark == true){
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크:</td><td>출력</td>';
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">워터마크:</td><td>미출력</td>';		
	}
	
	if(data.isFileEncryption == true){
		sOut += '<td class="center-cell th-cell-gray">실시간파일암호화:</td><td>암호화</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">실시간파일암호화:</td><td>비암호화</td></tr>';			
	}
	
	if(data.isUsbBlock == true){
		sOut += '<tr><td class="center-cell th-cell-gray">USB차단:</td><td>차단</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">USB차단:</td><td>허용</td>';			
	}
	
	if(data.isComPortBlock == true){
		sOut += '<td class="center-cell th-cell-gray">시리얼차단:</td><td>차단</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">시리얼차단:</td><td>허용</td></tr>';			
	}
	
	if(data.isWlan == true){	//무선랜
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜:</td><td>차단</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">무선랜:</td><td>허용</td>';			
	}
	
	if(data.isFilePattern == true){	//메일반출
		sOut += '<td class="center-cell th-cell-gray">메일반출:</td><td>허용</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">메일반출:</td><td>불허</td></tr>';			
	}
	
	if(data.isFilePattern == true){
		sOut += '<tr><td class="center-cell th-cell-gray">민감정보파일접근:</td><td>차단</td>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">민감정보파일접근:</td><td>허용</td>';			
	}
	
	if(data.isNetShare == true){	//공유폴더
		sOut += '<td class="center-cell th-cell-gray">공유폴더:</td><td>차단</td></tr>';	
	}else{
		sOut += '<td class="center-cell th-cell-gray">공유폴더:</td><td>허용</td></tr>';			
	}
	
	if(data.isCdEnabled == true){
		sOut += '<tr><td class="center-cell th-cell-gray">USB차단:</td><td>차단</td></tr>';	
	}else{
		sOut += '<tr><td class="center-cell th-cell-gray">USB차단:</td><td>허용</td></tr>';			
	}
	
	sOut += '</table>';
	
	return sOut;

}

function getPolicyIcon(data){
	var sOut = '';

	if(data.isUninstall == true){
		sOut += '<i class="fa fa-trash policy_icon" style="color:blue" title="에이전트삭제가능"></i>'
	}else{
		sOut += '<i class="fa fa-trash policy_icon" style="color:red" title="에이전트삭제불가"></i>'
	}

	if(data.isPrint == true){
		sOut += '<i class="fa fa-print policy_icon" style="color:blue" title="프린트가능"></i>'
	}else{
		sOut += '<i class="fa fa-print policy_icon" style="color:red" title="프린트불가"></i>'
	}

	if(data.isWaterMark == true){
		sOut += '<i class="fa fa-tint policy_icon" style="color:blue" title="워터마크출력"></i>'
	}else{
		sOut += '<i class="fa fa-tint policy_icon" style="color:red" title="워터마크미출력"></i>'
	}
	
	if(data.isFileEncryption == true){
		sOut += '<i class="fa fa-lock policy_icon" style="color:blue" title="실시간파일암호화"></i>'
	}else{
		sOut += '<i class="fa fa-lock policy_icon" style="color:red" title="실시간파일 비암호화"></i>'
	}
	
	if(data.isUsbBlock == true){
		sOut += '<i class="fa fa-usb policy_icon" style="color:red" title="USB포트차단"></i>'
	}else{
		sOut += '<i class="fa fa-usb policy_icon" style="color:blue" title="USB포트허용"></i>'
	}
	
	if(data.isComPortBlock == true){
		sOut += '<i class="fa fa-plug policy_icon" style="color:red" title="시리얼포트차단"></i>'
	}else{
		sOut += '<i class="fa fa-plug policy_icon" style="color:blue" title="시리얼포트허용"></i>'
	}
	
	if(data.isWlan == true){	//무선랜
		sOut += '<i class="fa fa-wifi policy_icon" style="color:blue" title="무선랜허용"></i>'
	}else{
		sOut += '<i class="fa fa-wifi policy_icon" style="color:red" title="무선랜차단"></i>'
	}
	
	if(data.isFilePattern == true){	//메일반출
		sOut += '<i class="fa fa-envelope policy_icon" style="color:blue" title="메일허용"></i>'
	}else{
		sOut += '<i class="fa fa-envelope policy_icon" style="color:red" title="메일차단"></i>'
	}
	
	if(data.isFilePattern == true){
		sOut += '<i class="fa fa-file-text policy_icon" style="color:blue" title="민감정보파일차단"></i>'
	}else{
		sOut += '<i class="fa fa-file-text policy_icon" style="color:red" title="민감정보파일허용"></i>'
	}
	
	if(data.isNetShare == true){	//공유폴더
		sOut += '<i class="fa fa-file-archive-o policy_icon" style="color:blue" title="공유폴더허용"></i>'
	}else{
		sOut += '<i class="fa fa-file-archive-o policy_icon" style="color:red" title="공유폴더차단"></i>'
	}
	
	if(data.isCdEnabled == true){
		sOut += '<i class="fa fa-folder-open policy_icon" style="color:blue" title="CD사용허용"></i>'
	}else{
		sOut += '<i class="fa fa-folder-open policy_icon" style="color:red" title="CD사용차단"></i>'
	}
	
	return sOut;

}