package com.hsqlu.coding.crypto.symmetric;

import org.junit.Before;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class SymmetricEncryptionUtilsTest {
	@Before
	public void init() {
//		System.setOut();
	}

	@Test
	public void createInitializationVector() throws Exception {
		byte[] initializationVector = SymmetricEncryptionUtils.createInitializationVector();
		assertNotNull(initializationVector);
		System.out.println(DatatypeConverter.printHexBinary(initializationVector));
	}

	@Test
	public void createAESKey() throws Exception {
		SecretKey key = SymmetricEncryptionUtils.createAESKey();
		assertNotNull(key);
		System.out.println(DatatypeConverter.printHexBinary(key.getEncoded()));
	}

	@Test
	public void testAESCryptoRoutine() throws Exception {
		SecretKey key = SymmetricEncryptionUtils.createAESKey();
		byte[] initializationVector = SymmetricEncryptionUtils.createInitializationVector();
		String plainText = "Hello World!";
		byte[] cipherText = SymmetricEncryptionUtils.performAESEncryption(plainText, key, initializationVector);
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));
		String decryptedText = SymmetricEncryptionUtils.performAESDecryption(cipherText, key, initializationVector);
		assertEquals(plainText, decryptedText);
	}
}