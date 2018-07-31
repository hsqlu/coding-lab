package com.hsqlu.coding.crypto.hash;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class HashUtilsTest {
	@Test
	public void generateRandomSalt() throws Exception {
		byte[] salt = HashUtils.generateRandomSalt();
		assertNotNull(salt);
		System.out.println(DatatypeConverter.printHexBinary(salt));
	}


	@Test
	public void createSHA2Hash() throws Exception {
		byte[] salt = HashUtils.generateRandomSalt();
		String valueToHash = UUID.randomUUID().toString();

		byte[] hash = HashUtils.createSHA2Hash(valueToHash, salt);
		assertNotNull(hash);
		byte[] hash2 = HashUtils.createSHA2Hash(valueToHash, salt);
		assertNotNull(hash2);
		assertEquals(DatatypeConverter.printHexBinary(hash), DatatypeConverter.printHexBinary(hash2));
	}

	@Test
	public void testPasswordRoutine() {
		String secretPhrase = "Hello world!";

		String passwordHash = HashUtils.hashPassword(secretPhrase);
		System.out.println(passwordHash);
		assertTrue(HashUtils.verifiedPassword(secretPhrase, passwordHash));
	}
}