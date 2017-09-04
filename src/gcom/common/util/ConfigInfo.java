package gcom.common.util;

public class ConfigInfo {
	/* file path */
	public final static String FILE_UPLOAD_PATH = "/uploads";
	public final static String AGENT_FILE_PATH = "/agent";
	public final static String AGENT_FILE_REAL_NAME = "/testAgent.txt";
	public final static String AGENT_FILE_VIEW_NAME = "gcomAgent";
	
	/* Result Code */
	public final static String RETURN_CODE_SUCCESS = "S";	// 성공
	public final static String RETURN_CODE_ERROR = "E";		// 실패
	public final static String DUP_USER_NUMBER = "DUN";		// 중복된 사번 존재
	public final static String DUP_USER_ID = "DUI";			// 중복된 아이디 존재
	public final static String EXIST_CHILD_DEPT = "ECD";	// 하위부서 존재
	public final static String EXIST_DEPT_AGENT = "EDA";	// 에이전트 존재
	public final static String EXIST_DEPT_USER= "EDU";		// 
	public final static String EXIST_USER_NUMBER= "EUN";	// 중복된 사번 존재
	
	public final static String NOT_EXIST_USER= "NEU";		//아이디 없음
	public final static String NOT_CORRECT_PASSWORD = "NCP";		//패스워드 틀림
	public final static String NOT_CORRECT_PASSWORD_ID = "NCPI";		//패스워드나 아이디 틀림
	
	public final static String EXIST_OVER_FILE_SIZE = "M";	// 파일사이즈초과
	
	public final static String EXIST_NOT_AGENT = "ENA";		// Agent 정보 없음. 
	public final static String EXIST_NOT_PARAM = "ENP"; 	// 필요정보가 존재하지 않음. 
	public final static String EXIST_FAIL_VERIFY = "EFV";	// 데이터 검증 실패 
	
	public final static String NOT_CORRECT_IP = "NCI";		//아이피 거부
	
	public final static String RETURN_CODE_SUCCESS_OVERLAP = "SO"; //일부성공 중복존재
}
