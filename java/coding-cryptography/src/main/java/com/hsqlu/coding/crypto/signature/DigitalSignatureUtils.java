package com.hsqlu.coding.crypto.signature;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * @date 26/07/2018.
 * @author Qiannan Lu
 */
public class DigitalSignatureUtils {
	private static final String SIGNING_ALGORITHM = "SHA256withRSA";

	public static byte[] createDigitalSignature(byte[] input, PrivateKey privateKey) throws Exception {
		Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
		signature.initSign(privateKey);
		signature.update(input);
		return signature.sign();
	}

	public static boolean verifyDigitalSignature(byte[] input, byte[] signatureToVerify, PublicKey publicKey) throws Exception {
		Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
		signature.initVerify(publicKey);
		signature.update(input);
		return signature.verify(signatureToVerify);
	}
}
