package com.dovnard.solana;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import org.bitcoinj.core.Base58;

/**
 *
 * @author dovnard
 */
public class GenerateKeyPair {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed25519"); // Solana uses Ed25519
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public key
        PublicKey publicKey = keyPair.getPublic();
        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("Public Key Base64: " + encodedPublicKey);

        String encodedBase58PublicKey = Base58.encode(publicKey.getEncoded());
        System.out.println("Public Key Base58: " + encodedBase58PublicKey);

        // Get the private key
        PrivateKey privateKey = keyPair.getPrivate();
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println("Private Key Base64: " + encodedPrivateKey);

        String encodedBase58PrivateKey = Base58.encode(privateKey.getEncoded());
        System.out.println("Private Key Base58: " + encodedBase58PrivateKey);

//        Public Key Base64: MCowBQYDK2VwAyEA0JZvKI8JIV0XSk+a0vZrYXjR57tRfPbStkmA5LCDfsI=
//        Public Key Base58: GfHq2tTVk9z4eXgyUMe2zE3NZMtoh7PWURsXQwdHV29gsGTLGRnShjUTwWKj
//        Private Key Base64: MC4CAQAwBQYDK2VwBCIEIFGRe6nLQtq9JMbts4phfr4+KyEa1GKePhmgUTPTgsPE
//        Private Key Base58: 2mWNaEKEJrtB1rEvHsFx8aEQ9eqKyizi6XjJwQs2WoJtZ58TQhKsCWvDryJqt45yy5
    }
}
