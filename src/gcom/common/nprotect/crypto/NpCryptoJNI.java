package gcom.common.nprotect.crypto;

public class NpCryptoJNI
{
	// ARIA constants
	public static final int ARIA_128 = 16;
	public static final int ARIA_192 = 24;
	public static final int ARIA_256 = 32;
	public static final int OP_MODE_ECB = 1;
	public static final int OP_MODE_CBC = 2;

	// SHA constants
	public static final int SHA_256 = 32;
	public static final int SHA_512 = 64;

	// RSA constants
	public static final int RSA_2048 = 2048;

	// Crypto Current State
	public static final int NP_STATUS_LOADED = 0x00000001;
	public static final int NP_STATUS_TERMINATE = 0x00000002;
	public static final int NP_STATUS_TESTING = 0x00000004;
	public static final int NP_STATUS_AUTHORIZED = 0x00000008;
	public static final int NP_STAUTS_ERROR = 0x00000010;

	// 
	public int init()
	{
		return nativeInit();
	}
	
	public int release()
	{
		return nativeRelease();
	}

	//// Java nPorect Crypto Manager
	//public long npInit()
	//{
	//    return nativeNpInit();
	//}

	//public void npDestroy(int handle)
	//{
	//    nativeNpDestroy(handle);
	//}

	//public long npSelfTest(int handle)
	//{
	//    return nativeNpSelfTest(handle);
	//}

	//public long npGetState(int handle)
	//{
	//    return nativeNpGetState(handle);
	//}

	// Java CTR_DRBG
	// ���� ����
	public long npRandom(int handle, byte[] rand, int s_rand)
	{
		if (null == rand)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (rand.length < s_rand)
		{
			return ((long)NpDefErr.NPERR_SMALL_BUFFER_SIZE << 32);
		}

		return nativeNpRandom(handle, rand, s_rand);
	}

	// Java HASH
	// Hash ����
	public long npHash(int handle, int type, byte[] msg, int s_msg, byte[] hash, int s_hash)
	{
		if (null == msg || null == hash)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.SHA_256 != type && NpCryptoJNI.SHA_512 != type)
		{
			return ((long)NpDefErr.NPERR_SHA_INVALID_MD_TYPE << 32);
		}

		if (type > s_hash)
		{
			return ((long)NpDefErr.NPERR_SMALL_BUFFER_SIZE << 32);
		}

		return nativeNpHash(handle, type, msg, s_msg, hash, s_hash);
	}

	// Java HMAC
	// HMAC ����
	public long npHMAC(int handle, int type, byte[] key, int s_key, byte[] msg, int s_msg, byte[] hmac, int s_hmac)
	{
		if (null == key || null == msg || null == hmac)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.SHA_256 != type && NpCryptoJNI.SHA_512 != type)
		{
			return ((long)NpDefErr.NPERR_SHA_INVALID_MD_TYPE << 32);
		}

		if (type > s_hmac)
		{
			return ((long)NpDefErr.NPERR_SMALL_BUFFER_SIZE << 32);
		}

		return nativeNpHMAC(handle, type, key, s_key, msg, s_msg, hmac, s_hmac);
	}

	// Java ARIAKey_Gen
	// ��ĪŰ ���� - ARIA
	public long npSKGen(int handle, ARIAKey key, int s_key)
	{
		if (null == key)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.ARIA_128 != s_key && NpCryptoJNI.ARIA_192 != s_key && NpCryptoJNI.ARIA_256 != s_key)
		{
			return ((long)NpDefErr.NPERR_ARIA_INVALID_KEY_SIZE << 32);
		}
		key._keySize = s_key;
		return nativeNpSKGen(handle, key._key, s_key);
	}

	// Java ARIA_Encrypt
	// ��Ī ��ȣȭ
	public long npEncSymk(int handle, ARIAKey key, int op_mode, byte[] plain, int s_plain, byte[] cipher, int s_cipher)
	{
		if (null == key || null == plain || null == cipher)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.ARIA_128 != key._keySize && NpCryptoJNI.ARIA_192 != key._keySize && NpCryptoJNI.ARIA_256 != key._keySize)
		{
			return ((long)NpDefErr.NPERR_ARIA_INVALID_KEY_SIZE << 32);
		}

		if (NpCryptoJNI.OP_MODE_ECB != op_mode && NpCryptoJNI.OP_MODE_CBC != op_mode)
		{
			return ((long)NpDefErr.NPERR_ARIA_INVALID_OP_MODE << 32);
		}

		int iPadSize = s_plain % 16;
		if (0 != iPadSize)
			iPadSize = 16 - iPadSize;
		else
			iPadSize = 16;
		int iOutSize = s_plain + iPadSize;

		if (s_cipher < iOutSize)
		{
			return ((long)NpDefErr.NPERR_SMALL_BUFFER_SIZE << 32);
		}

		return nativeNpEncSymk(handle, key._keySize, key._key, key._iv, op_mode, plain, s_plain, cipher, s_cipher);
	}

	// Java ARIA_Decrypt
	// ARIA ��ȣȭ
	public long npDecSymk(int handle, ARIAKey key, int op_mode, byte[] cipher, int s_cipher, byte[] plain, int s_plain)
	{
		if (null == key || null == plain || null == cipher)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.ARIA_128 != key._keySize && NpCryptoJNI.ARIA_192 != key._keySize && NpCryptoJNI.ARIA_256 != key._keySize)
		{
			return ((long)NpDefErr.NPERR_ARIA_INVALID_KEY_SIZE << 32);
		}

		if (NpCryptoJNI.OP_MODE_ECB != op_mode && NpCryptoJNI.OP_MODE_CBC != op_mode)
		{
			return ((long)NpDefErr.NPERR_ARIA_INVALID_OP_MODE << 32);
		}

		if (s_plain < s_cipher)
		{
			return ((long)NpDefErr.NPERR_SMALL_BUFFER_SIZE << 32);
		}

		return nativeNpDecSymk(handle, key._keySize, key._key, key._iv, op_mode, cipher, s_cipher, plain, s_plain);
	}
	
	// Java RSA_Key_Gen
	// RSA Ű ����
	public long npPKGen(int handle, RSAPublicKey puk, RSAPrivateKey prk, int s_key)
	{
		if (null == puk || null == prk)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.RSA_2048 != s_key)
		{
			return ((long)NpDefErr.NPERR_RSA_INVALID_KEY_SIZE << 32);
		}
		puk.bits = prk.bits = s_key;

		return nativeNpPKGen(handle, puk.n_dat, puk.e_dat, prk.n_dat, prk.e_dat, prk.d_dat, s_key);
	}
	
	// Java RSAES_Encrypt
	// RSA ��ȣȭ
	public long npEncPK(int handle, RSAPublicKey puk, byte[] plain, int s_plain, byte[] label, int s_label, byte[] cipher, int s_cipher)
	{
		if (null == puk || null == plain || null == cipher)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.RSA_2048 != puk.bits)
		{
			return ((long)NpDefErr.NPERR_RSA_INVALID_KEY_SIZE << 32);
		}

		return nativeNpEncPK(handle, puk.bits, puk.n_dat, puk.e_dat, plain, s_plain, label, s_label, cipher, s_cipher);
	}

	// Java RSAES_Decrypt
	// RSA ��ȣȭ
	public long npDecPK(int handle, RSAPrivateKey prk, byte[] cipher, int s_cipher, byte[] label, int s_label, byte[] plain, int s_plain)
	{
		if (null == prk || null == cipher || null == plain)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.RSA_2048 != prk.bits)
		{
			return ((long)NpDefErr.NPERR_RSA_INVALID_KEY_SIZE << 32);
		}

		return nativeNpDecPK(handle, prk.bits, prk.n_dat, prk.e_dat, prk.d_dat, cipher, s_cipher, label, s_label, plain, s_plain);
	}

	// Java RSA_PSS_SIGN
	// ���� ����
	public long npSignPK(int handle, RSAPrivateKey prk, byte[] msg, int s_msg, byte[] sign, int s_sign)
	{
		if (null == prk || null == msg || null == sign)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.RSA_2048 != prk.bits)
		{
			return ((long)NpDefErr.NPERR_RSA_INVALID_KEY_SIZE << 32);
		}

		return nativeNpSignPK(handle, prk.bits,prk.n_dat, prk.e_dat, prk.d_dat, msg, s_msg, sign, s_sign);
	}

	// Java RSA_PSS_VERIFY_SIGN
	// ���� ����
	public long npVerifyPK(int handle, RSAPublicKey puk, byte[] msg, int s_msg, byte[] sign, int s_sign)
	{
		if (null == puk || null == msg || null == sign)
		{
			return ((long)NpDefErr.NPERR_PARAM_NULL_POINT << 32);
		}

		if (NpCryptoJNI.RSA_2048 != puk.bits)
		{
			return ((long)NpDefErr.NPERR_RSA_INVALID_KEY_SIZE << 32);
		}

		return nativeNpVerifyPK(handle, puk.bits, puk.n_dat, puk.e_dat, msg, s_msg, sign, s_sign);
	}
	
	//
	//
	//
	/*
	private long NpDecSymk(int handle, int keySize, byte[] key, byte[] iv, int op_mode, byte[] cipher, int s_cipher, byte[] plain, int s_plain) {
		
		return nativeNpDecSymk(handle, keySize, key, iv, op_mode, cipher, s_cipher, plain, s_plain);
	}
	 */
	public long npPKGen(int handle, int[] puk_n, int[] puk_e, int[] prk_n, int[] prk_e, int[] prk_d, int s_key) {
		
		return nativeNpPKGen(handle, puk_n, puk_e, prk_n, prk_e, prk_d, s_key);
	}
	
	////////////////////////////////////////////////////////////
	// JNI ���� ////////////////////////////////////////////////
	//														  //
	// warning!												  //
	// * �Ʒ��� JNI �� ��Ƽ�����带 �������� ����!!			  //
	//														  //

	// Native 
	private native int nativeInit();
	private native int nativeRelease();

	// Native nProtect Crypto Manager
	//private native long nativeNpInit();
	//private native void nativeNpDestroy(int handle);
	//private native long nativeNpSelfTest(int handle);
	//private native long nativeNpGetState(int handle);

	// Native CTR_DRBG
	private native long nativeNpRandom(int handle, byte[] rand, int s_rand);

	// Native HASH & HMAC
	private native long nativeNpHash(int handle, int sha_md_type, byte[] msg, int s_msg, byte[] hash, int s_hash);
	private native long nativeNpHMAC(int handle, int sha_md_type, byte[] key, int s_key, byte[] msg, int s_msg, byte[] hash, int s_hash);

	// Native ARIA
	private native long nativeNpSKGen(int handle, byte[] key, int keySize);
	private native long nativeNpEncSymk(int handle, int keySize, byte[] key, byte[] iv, int op_mode, byte[] plain, int s_plain, byte[] cipher, int s_cipher);
	private native long nativeNpDecSymk(int handle, int keySize, byte[] key, byte[] iv, int op_mode, byte[] cipher, int s_cipher, byte[] plain, int s_plain);

	// Native RSA
	private native long nativeNpPKGen(int handle, int[] puk_n, int[] puk_e, int[] prk_n, int[] prk_e, int[] prk_d, int s_key);
	private native long nativeNpEncPK(int handle, int nSize, int[] puk_n, int[] puk_e, byte[] plain, int s_plain, byte[] label, int s_label, byte[] cipher, int s_cipher);
	private native long nativeNpDecPK(int handle, int nSize, int[] prk_n, int[] prk_e, int[] prk_d, byte[] cipher, int s_cipher, byte[] label, int s_label, byte[] plain, int s_plain);
	private native long nativeNpSignPK(int handle, int nSize, int[] prk_n, int[] prk_e, int[] prk_d, byte[] msg, int s_msg, byte[] sign, int s_sign);
	private native long nativeNpVerifyPK(int handle, int nSize, int[] puk_n, int[] puk_e, byte[] msg, int s_msg, byte[] sign, int s_sign);

	// JNI �� //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////

	static public String gGetSysKind()
	{
		String os_name = System.getProperty("os.name");
		String sun_arch_data_model = System.getProperty("sun.arch.data.model");
		String sys_kind = os_name.substring(0, 3).toLowerCase();
		sys_kind += sun_arch_data_model;
		return sys_kind;
	}

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes)
	{
		char[] hexChars = new char[bytes.length*2];
		for (int j = 0; j < bytes.length; j++)
		{
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	static
	{
		System.loadLibrary("nProtectCrypto4Java");
	}
/*
	public static void main(String args[])
	{
		long nRet = 0;
		byte btSrc[] = {	'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
							'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
							'1', '2', '3', '4', '5', 0x00 };
		// size of btSrc;
		int nSrc = 26;

		// ���� �޼ҵ� ��ü ����
		NpCryptoJNI cryptoJNI = new NpCryptoJNI();

		//////////////////////////////////////////////////////////////////////
		// ��ȣ��� �ʱ�ȭ
		int nHdl = cryptoJNI.init();
		if (0 > nHdl)
		{
			System.out.println("init failed");
			return;
		}

		//////////////////////////////////////////////////////////////////////
		// ���� �߻� (Random) �׽�Ʈ
		byte randBuf[] = new byte[32];
		for (int i = 0; i < randBuf.length; i++)
			randBuf[i] = 0;

		nRet = cryptoJNI.npRandom(nHdl, randBuf, randBuf.length);
		System.out.println("npRandom: " + nRet + ", " + bytesToHex(randBuf));

		//////////////////////////////////////////////////////////////////////
		// Hash ���� �׽�Ʈ
		byte btHash[] = new byte[32];
		int nHash = 0;

		nRet = cryptoJNI.npHash(nHdl, NpCryptoJNI.SHA_256, btSrc, nSrc, btHash, btHash.length);

		System.out.println("npHash: " + nRet + ", " + bytesToHex(btHash));

		//////////////////////////////////////////////////////////////////////
		// ARIA Ű ���� �׽�Ʈ
		ARIAKey ariaKey = new ARIAKey();
		nRet = cryptoJNI.npSKGen(nHdl, ariaKey, NpCryptoJNI.ARIA_128);

		System.out.println("npSKGen: " + nRet + ", " + bytesToHex(ariaKey._key) + ", " + bytesToHex(ariaKey._iv));

		//////////////////////////////////////////////////////////////////////
		// ARIA ��ȣȭ �׽�Ʈ

		// ��ȣȭ�� ���̳ʸ�. + 16 �� ���������� 16�� ����� padding �ϱ� ������ �ݵ�� �ʿ��ϴ�.
		int nSize = nSrc + 16;
		byte btEnc[] = new byte[nSize];
		int nEnc = 0;

		nRet = cryptoJNI.npEncSymk(nHdl, ariaKey, NpCryptoJNI.OP_MODE_CBC, btSrc, nSrc, btEnc, btEnc.length);

		System.out.println("npEncSymk: " + nRet + ", " + bytesToHex(btEnc));

		//////////////////////////////////////////////////////////////////////
		// ARIA ��ȣȭ �׽�Ʈ

		nEnc = (int)nRet & 0x0000ffff;
		
		// ��ȣȭ�� ���̳ʸ�. + 16 �� ���������� 16�� ����� padding �ϱ� ������ �ݵ�� �ʿ��ϴ�.
		byte btDec[] = new byte[200];
		int nDec = 0;
		
		nRet = cryptoJNI.npDecSymk(nHdl, ariaKey, NpCryptoJNI.OP_MODE_CBC, btEnc, nEnc, btDec, btDec.length);

		System.out.println("npDecSymk: " + nRet + ", " + bytesToHex(btDec));


		//////////////////////////////////////////////////////////////////////
		// ����
		cryptoJNI.release();
	}
	*/
}
