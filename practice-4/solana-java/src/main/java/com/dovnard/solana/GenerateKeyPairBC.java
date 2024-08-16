package com.dovnard.solana;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.security.SecureRandom;
import java.util.Base64;
import org.bitcoinj.core.Base58;
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters;

/**
 *
 * @author dovnard
 */
public class GenerateKeyPairBC {
    public static void main(String[] args) throws Exception {
        // Add Bouncy Castle as a Security Provider
        Security.addProvider(new BouncyCastleProvider());

        // Generate a new KeyPair using Ed25519
        Ed25519KeyPairGenerator keyPairGenerator = new Ed25519KeyPairGenerator();
        keyPairGenerator.init(new Ed25519KeyGenerationParameters(new SecureRandom()));
        AsymmetricCipherKeyPair keyPair = keyPairGenerator.generateKeyPair();

        Ed25519PrivateKeyParameters privateKeyParams = (Ed25519PrivateKeyParameters) keyPair.getPrivate();
        Ed25519PublicKeyParameters publicKeyParams = (Ed25519PublicKeyParameters) keyPair.getPublic();

        // Encode the private key to a string
        byte[] privateKeyBytes = privateKeyParams.getEncoded();
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKeyBytes);
        String encodedBase58PrivateKey = Base58.encode(privateKeyBytes);
        System.out.println("Private Key Base64: " + encodedPrivateKey);
        System.out.println("Private Key Base58: " + encodedBase58PrivateKey);

        // Encode the public key to a string
        byte[] publicKeyBytes = publicKeyParams.getEncoded();
        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKeyBytes);
        String encodedBase58PublicKey = Base58.encode(publicKeyBytes);
        System.out.println("Public Key Base64: " + encodedPublicKey);
        System.out.println("Public Key Base58: " + encodedBase58PublicKey);
        
//        Private Key Base64: Ih2pZghE5v25y4HAdBfAt9iPSX0x+hzxm2TWi3zVNXQ=
//        Private Key Base58: 3JB6VYouki42fFCovZYtBEgPMRVfD722MVU3Ng3XABFR
//        Public Key Base64: WGzRCMvmH+RvF+B6awg/kefp5a7wcvDAHoPwsQXm0hE=
//        Public Key Base58: 6xB7gt18egrFc7rws2LaEucZioUrzQMKJ5isrbibfSkY
    }

}
