package com.hsqlu.coding.crypto.symmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class SymmetricEncryptionUtils {
	private static final String AES = "AES";
	private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	public static SecretKey createAESKey() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
		keyGenerator.init(256, secureRandom);
		return keyGenerator.generateKey();
	}

	public static byte[] createInitializationVector() {
		byte[] initializationVector = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVector);
		return initializationVector;
	}

	public static byte[] performAESEncryption(String plainText, SecretKey key, byte[] initializationVector) throws Exception {
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		return cipher.doFinal(plainText.getBytes());
	}

	public static String performAESDecryption(byte[] cipherText, SecretKey key, byte[] initializationVector)
			throws Exception {
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
		cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}
}