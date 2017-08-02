package gcom.common.nprotect.crypto;

public interface NpDefErr {
	// successful operation
	int NPCRYPTO_SUCCESS				= 0x0000;
	
	// the difinitions of common error
	int NPERR_FAIL_ALLOC_MEM 			= 0x0001;
	int NPERR_PARAM_NULL_POINT			= 0x0002;
	int NPERR_SMALL_BUFFER_SIZE			= 0x0003;
	int NPERR_FAIL_NEED_INITIALIZE		= 0x0004;
	
	// the difinitions of error in states
	int NPERR_FAIL_ERROR_STATE			= 0x0052;
	
	// the difinitions of error in self-test
	int NPERR_FAIL_KAT_SHA256			= 0x0080;
	int NPERR_FAIL_KAT_SHA512			= 0x0081;
	int NPERR_FAIL_KAT_HMAC256			= 0x0082;
	int NPERR_FAIL_KAT_HMAC512			= 0x0083;
	int NPERR_FAIL_KAT_ARIA				= 0x0084;
	int NPERR_FAIL_KAT_CTR_DRBG			= 0x0085;
	int NPERR_FAIL_KAT_RSA_OAEP			= 0x0086;
	int NPERR_FAIL_KAT_RSA_PSS			= 0x0087;
	int NPERR_FAIL_ENTROPY_HEALTH_TEST	= 0x0088;
	
	// the difinitions of error in ARIA
	int NPERR_ARIA_KEYGEN_FAIL			= 0x1100;
	int NPERR_ARIA_ENCRYPT_FAIL			= 0x1101;
	int NPERR_ARIA_DECRYPT_FAIL			= 0x1102;
	int NPERR_ARIA_INVALID_KEY_SIZE		= 0x1103;
	int NPERR_ARIA_INVALID_OP_MODE		= 0x1104;
	
	// the difinitions of error in CTR_DRBG
	int NPERR_CTR_DRBG_DUPLICATE_FAIL	= 0x1200;
	
	// the difinitions of error in SHA
	int NPERR_SHA_INVALID_MD_TYPE		= 0x1300;
	
	// the difinitions of error in RSA
	int NPERR_RSA_KEY_GEN_FAIL			= 0x1600;
	int NPERR_RSA_ENCRYPT_FAIL			= 0x1601;
	int NPERR_RSA_DECRYPT_FAIL			= 0x1602;
	int NPERR_RSA_SIGN_GENERATE_FAIL	= 0x1700;
	int NPERR_RSA_SIGN_VERIFY_FAIL		= 0x1701;
	int NPERR_RSA_INVALID_KEY_SIZE		= 0x1702;
	
	// the difinitions of error in checking integrity
	int NPERR_FILE_INTEGRITY_FAIL		= 0x1800;
}
