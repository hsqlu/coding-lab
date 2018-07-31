package com.hsqlu.coding.crypto.asymmertic;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class AsymmetricEncryptionUtilsTest {
	@Test
	public void generateRASKeyPair() throws Exception {
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRASKeyPair();
		assertNotNull(keyPair);
		System.out.println("Private key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
		System.out.println("Public key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
	}

	@Test
	public void testRSACryptoRoutine() throws Exception {
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRASKeyPair();
		String plainText = "Hello World!";
		byte[] cipherText = AsymmetricEncryptionUtils.performRASEncryption(plainText, keyPair.getPrivate());
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));
		String decryptedText = AsymmetricEncryptionUtils.performRSADecryption(cipherText, keyPair.getPublic());
		assertEquals(plainText, decryptedText);
	}

}