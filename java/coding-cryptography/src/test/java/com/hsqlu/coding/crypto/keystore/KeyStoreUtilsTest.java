package com.hsqlu.coding.crypto.keystore;

import com.hsqlu.coding.crypto.symmetric.SymmetricEncryptionUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import java.security.KeyStore;

import static org.junit.Assert.*;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class KeyStoreUtilsTest {
	@Test
	public void createPrivateKeyJavaKeyStore() throws Exception {
		SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
		String secretKeyHex = DatatypeConverter.printHexBinary(secretKey.getEncoded());
		KeyStore keyStore = KeyStoreUtils.createPrivateKeyJavaKeyStore("password", "foo", secretKey, "keyPassword");
		assertNotNull(keyStore);

		keyStore.load(null, "password".toCharArray());
		KeyStore.PasswordProtection entryPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());
		KeyStore.SecretKeyEntry resultEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("foo", entryPassword);
		SecretKey result = resultEntry.getSecretKey();
		String resultHex = DatatypeConverter.printHexBinary(result.getEncoded());
		assertEquals(secretKeyHex, resultHex);


	}

}