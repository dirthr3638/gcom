package gcom.common.util;

public class ConfigInfo {
	/* file path */
	public final static String FILE_UPLOAD_PATH = "/uploads";
	
	/* Result Code */
	public final static String RETURN_CODE_SUCCESS = "S";	// 성공
	public final static String RETURN_CODE_ERROR = "E";		// 실패
	public final static String DUP_USER_NUMBER = "DUN";		// 중복된 사번 존재
	public final static String DUP_USER_ID = "DUI";			// 중복된 아이디 존재
	public final static String EXIST_CHILD_DEPT = "ECD";	// 하위부서 존재
	public final static String EXIST_DEPT_AGENT = "EDA";	// 에이전트 존재
	public final static String EXIST_DEPT_USER= "EDU";		// 중복된 아이디 존재
	public final static String EXIST_USER_NUMBER= "EUN";	// 중복된 아이디 존재
	
	public final static String EXIST_NOT_AGENT = "ENA";		// Agent 정보 없음. "허용실패하였습니다. 정책 정보에 장치는 추가되었으나 해당 사용자 Agent 정보가 존재하지 않습니다."
	public final static String EXIST_NOT_PARAM = "ENP"; 	// 필요정보가 존재하지 않음. "허용실패하였습니다. 장치 정보 추가 시 문제가 발생하였습니다."
	
	
}
