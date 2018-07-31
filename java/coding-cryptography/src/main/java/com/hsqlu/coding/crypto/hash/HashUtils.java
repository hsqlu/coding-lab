package com.hsqlu.coding.crypto.hash;

import org.mindrot.jbcrypt.BCrypt;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class HashUtils {
	private static final String SHA2_ALGORITHM = "SHA-256";

	public static byte[] generateRandomSalt() {
		byte[] salt = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);
		return salt;
	}

	public static byte[] createSHA2Hash(String input, byte[] salt) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(salt);
		outputStream.write(input.getBytes());
		byte[] valueToHash = outputStream.toByteArray();

		MessageDigest messageDigest = MessageDigest.getInstance(SHA2_ALGORITHM);
		return messageDigest.digest(valueToHash);
	}

	public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static boolean verifiedPassword(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}
}

