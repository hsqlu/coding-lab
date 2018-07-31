package com.hsqlu.coding.crypto.signature;

import com.hsqlu.coding.crypto.asymmertic.AsymmetricEncryptionUtils;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;

import static org.junit.Assert.*;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class DigitalSignatureUtilsTest {
	@Test
	public void createDigitalSignature() throws Exception {
	}

	@Test
	public void digitalSignatureRoutine() throws Exception {
		URL url = this.getClass().getClassLoader().getResource("demo.txt");
		Path path = Paths.get(url.toURI());
		byte[] input = Files.readAllBytes(path);

		KeyPair keyPair = AsymmetricEncryptionUtils.generateRASKeyPair();
		byte[] signature = DigitalSignatureUtils.createDigitalSignature(input, keyPair.getPrivate());
		System.out.println(DatatypeConverter.printHexBinary(signature));
		assertTrue(DigitalSignatureUtils.verifyDigitalSignature(input, signature, keyPair.getPublic()));
	}

}