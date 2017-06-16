package gcom.user.model;

import java.util.HashMap;

import lombok.Data;

@Data
public class UserPolicyNameModel {
	
	private HashMap<String, String> policy = new HashMap<String, String>();
	
	public UserPolicyNameModel() {
		//policy.put("policy_uninstall_enabled" , "에이전트 삭제 가능 여부");
		//policy.put("policy_fs_encryption_enabled" , "파일 암호화 가능 여부");
		//policy.put("policy_cd_encryption_enabled" , "CD 암호화 사용 여부");
		//policy.put("policy_printer_enabled" , "프린터 사용 가능 여부");
		//policy.put("policy_cd_enabled" , "CD 사용 여부");
		//policy.put("policy_cd_export_enabled" , "CD 복사/이동 가능 여부");
		//policy.put("policy_wlan_enabled" , "무선랜 사용 가능 여부");
		//policy.put("policy_net_share_descriptor" , "공유 폴더 사용 여부");
		//policy.put("policy_web_export_enabled" , "WEB외부 반출 가능 여부");
		
		
		//
		policy.put("policy_watermark_enabled" , "워터마크 출력 여부");
		
		
		policy.put("policy_removal_storage_export_enabled" , "저장정보 외부 반출 시 삭제 여부");
		policy.put("policy_removal_storage_admin_mode" , "관리자 모드 저장 정보 삭제");
		policy.put("policy_usb_descriptor" , "인가된 USB장치 사용 가능 여부");
		policy.put("policy_port_descriptor" , "시리얼 포트 사용 가능 여부");
		policy.put("policy_net_descriptor" , "외부 인터넷 사용 가능 여부");
		
		
		policy.put("policy_sensitive_file_access" , "민감 파일 접근 시 삭제 여부");
		policy.put("policy_sensitive_dir_enabled" , "보호 폴더 접근 가능 여부");
		policy.put("policy_authentication_code" , "인증 코드 사용 여부");
		
		policy.put("policy_usb_control_enabled" , "USB 통제 기능 사용 여부");
		policy.put("policy_print_log_descriptor" , "프린트 로그 사용 여부");
	}
	
}
