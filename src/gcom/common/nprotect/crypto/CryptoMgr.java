package gcom.common.nprotect.crypto;

import java.util.Arrays;

import gcom.common.nprotect.crypto.*;

public class CryptoMgr {
	
	public static final int CM_DEFAULT_PK_KEY_LENGTH = 256; // 2048bit(256)
	public static final int CM_DEFAULT_PK_KEY_BIT_LENGTH = NpCryptoJNI.RSA_2048; //CM_DEFAULT_PK_KEY_LENGTH * 8;
	public static final int CM_DEFAULT_SK_KEY_LENGTH = NpCryptoJNI.ARIA_128; // 128bit(16)
	public static final int CM_DEFAULT_SK_BLOCK_LENGTH = 16;
	public static final int CM_DEFAULT_HASH_LENGTH = NpCryptoJNI.SHA_512;  // SHA512(64)
	public static final int CM_DEFAULT_SALT_INFO_LENGTH = 8 + CM_DEFAULT_SK_BLOCK_LENGTH; // == UserNo(4) + Length(4) + Data(16)
	public static final int CM_DEFAULT_PSHASH_INFO_LENGTH = 8 + CM_DEFAULT_HASH_LENGTH; // == UserNo(4) + Length(4) + Data(64)
	public static final int CM_DEFAULT_EKEK_INFO_LENGTH = 12 + CM_DEFAULT_SK_KEY_LENGTH; // == KeyId(8) + Length(4) + Data(16)	
	public static final int CM_DEFAULT_CEK_INFO_LENGTH = 12 + CM_DEFAULT_SK_KEY_LENGTH; // == KeyId(8) + Length(4) + Data(16)	
	
	private static NpCryptoJNI npCryptoJni = null;
	private static int handle = -1;
	
	public CryptoMgr() {
		
		if (handle == -1) {
			npCryptoJni = new NpCryptoJNI();
			handle = npCryptoJni.init();
		}
	}
	
	public CryptoStatus init() {		
		/*
		npCryptoJni = new NpCryptoJNI();
		  
		handle = npCryptoJni.init();
		if (0 > handle) return CryptoStatus.CS_UNSUCCESSFUL;
		*/
		return CryptoStatus.CS_SUCCESS;		
	}
	
	public void terminate() {		
		//if (npCryptoJni != null) npCryptoJni.release(handle);
	}
	
	public CryptoStatus generatePK(int[] puk_n, int[] puk_e, int[] prk_n, int[] prk_e, int[] prk_d, int s_key) {
	
		long nRet;
		
		nRet = npCryptoJni.npPKGen(handle, puk_n, puk_e, prk_n, prk_e, prk_d, s_key);
		if (((nRet & 0xffffffff00000000L) >> 32) == NpDefErr.NPCRYPTO_SUCCESS) {
			return CryptoStatus.CS_SUCCESS;
		}
		
		return CryptoStatus.CS_UNSUCCESSFUL;
		
	}
	
	public CryptoStatus generateSK(byte[] keyData) {
	
		long nRet;
		ARIAKey key = new ARIAKey();
		nRet = npCryptoJni.npSKGen(handle, key, keyData.length);
		if (((nRet & 0xffffffff00000000L) >> 32) == NpDefErr.NPCRYPTO_SUCCESS) {
			
			for (int i = 0; i < keyData.length; i++) {
				keyData[i] = key._key[i];
			}
			
			return CryptoStatus.CS_SUCCESS;
		}
		
		return CryptoStatus.CS_UNSUCCESSFUL;
	}
	
	public CryptoStatus generateRandom(byte[] buffer) {
		
		long nRet;
		
		nRet = npCryptoJni.npRandom(handle, buffer, buffer.length);
		if (((nRet & 0xffffffff00000000L) >> 32) == NpDefErr.NPCRYPTO_SUCCESS) {
			return CryptoStatus.CS_SUCCESS;
		}
		
		return CryptoStatus.CS_UNSUCCESSFUL;
	}
	
	public CryptoStatus hashData(byte[] data, int mode, byte[] hashData) {

		long nRet = npCryptoJni.npHash(handle, mode, data, data.length, hashData, hashData.length);
		if (((nRet & ((long)0xffffffff00000000L)) >> 32) == NpDefErr.NPCRYPTO_SUCCESS) {
			
			return CryptoStatus.CS_SUCCESS;
		}
		
		return CryptoStatus.CS_UNSUCCESSFUL;
		
	}
	
	public CryptoStatus hashPassword(byte[] password, byte[] optionalData, byte[] hashData) {
		
		
		byte[] buffer = new byte[password.length + optionalData.length];
		System.arraycopy(password, 0, buffer, 0, password.length);
		System.arraycopy(optionalData, 0, buffer, password.length, optionalData.length);
		
		long nRet = npCryptoJni.npHash(handle, NpCryptoJNI.SHA_512, buffer, buffer.length, hashData, hashData.length);
		if (((nRet & ((long)0xffffffff00000000L)) >> 32) == NpDefErr.NPCRYPTO_SUCCESS) {
			
			return CryptoStatus.CS_SUCCESS;
		
		} else if (((nRet & ((long)0xffffffff00000000L)) >> 32) == NpDefErr.NPERR_FAIL_ERROR_STATE) {
			
			return CryptoStatus.CS_INVALID_HANDLE;
		}
		
		return CryptoStatus.CS_UNSUCCESSFUL;
		
	}
	
	public CryptoStatus encryptData(byte[] keyData, int keySize, byte[] plainText, byte[] cipherText) {
		
		ARIAKey key = new ARIAKey();
		key._keySize = keySize;
		System.arraycopy(keyData, 0, key._key, 0, keySize);
		
		long nRet = npCryptoJni.npEncSymk(handle, key, NpCryptoJNI.OP_MODE_ECB, plainText, plainText.length, cipherText, cipherText.length);
		if (((nRet & ((long)0xffffffff00000000L)) >> 32) != NpDefErr.NPCRYPTO_SUCCESS) {
			
			return CryptoStatus.CS_ENCRYPTION_FAILED;
		}
		
		return CryptoStatus.CS_SUCCESS;
	}
	
	public CryptoStatus decryptData(byte[] keyData, int keySize, byte[] cipherText, byte[] plainText) {
		
		ARIAKey key = new ARIAKey();
		key._keySize = keySize;
		System.arraycopy(keyData, 0, key._key, 0, keySize);
		
		long nRet = npCryptoJni.npDecSymk(handle, key, NpCryptoJNI.OP_MODE_ECB, cipherText, cipherText.length, plainText, plainText.length);
		if (((nRet & ((long)0xffffffff00000000L)) >> 32) != NpDefErr.NPCRYPTO_SUCCESS) {
			
			return CryptoStatus.CS_ENCRYPTION_FAILED;
		}
		
		return CryptoStatus.CS_SUCCESS;
	}
	
	public CryptoStatus encryptKek(byte[] kekData, byte[] psHashData, byte[] password, byte[] ekekData) {
		
		byte[] hashData = new byte[CM_DEFAULT_HASH_LENGTH];		
		
		CryptoStatus status = hashPassword(password, psHashData, hashData);
		if (status != CryptoStatus.CS_SUCCESS) {
			
			return status;
		}
		
		return encryptData(hashData, CM_DEFAULT_SK_KEY_LENGTH, kekData, ekekData);
	}
	
	public CryptoStatus decryptKek(byte[] ekekData, byte[] psHashData, byte[] password, byte[] kekData) {
		
		byte[] hashData = new byte[CM_DEFAULT_HASH_LENGTH];		
		
		CryptoStatus status = hashPassword(password, psHashData, hashData);
		if (status != CryptoStatus.CS_SUCCESS) {
			
			return status;
		}
		
		return decryptData(hashData, CM_DEFAULT_SK_KEY_LENGTH, ekekData, kekData);
	}
	
	public CryptoStatus authenticatePassword(byte[] password, byte[] salt, byte[] psHash) {
		
		byte[] hashData = new byte[CM_DEFAULT_HASH_LENGTH];		
		byte[] psHashData = (new PsHashInfo(psHash)).getPsHashData();
		
		CryptoStatus status = hashPassword(password, (new SaltInfo(salt)).getSaltData(), hashData);
		if (status != CryptoStatus.CS_SUCCESS) {
			
			return status;
		}
		
		if (psHashData.length != CM_DEFAULT_HASH_LENGTH) {
			return CryptoStatus.CS_UNAUTHORIZED;
		}
		
		if (Arrays.equals(psHashData, hashData)) {
			return CryptoStatus.CS_SUCCESS;
		}
		
		return CryptoStatus.CS_UNAUTHORIZED;
		
	}
	
}
