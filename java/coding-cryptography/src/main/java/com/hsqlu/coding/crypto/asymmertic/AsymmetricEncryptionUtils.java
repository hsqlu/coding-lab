package com.hsqlu.coding.crypto.asymmertic;

import javax.crypto.Cipher;
import java.security.*;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class AsymmetricEncryptionUtils {
	private static final String RSA = "RSA";

	public static KeyPair generateRASKeyPair() throws Exception {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		keyPairGenerator.initialize(4096, secureRandom);
		return keyPairGenerator.generateKeyPair();
	}

	public static byte[] performRASEncryption(String plainText, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(plainText.getBytes());
	}

	public static String performRSADecryption(byte[] cipherText, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}
}
