package com.dovnard.solana;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.Base64;
import org.bitcoinj.core.Base58;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;


/**
 *
 * @author dovnard
 */
public class LoadKeyPair {
    public static void main(String[] args) throws Exception {
        // Add Bouncy Castle as a Security Provider
        Security.addProvider(new BouncyCastleProvider());
        
        // Decode the private key from the string
        String encodedPrivateKey = "3JB6VYouki42fFCovZYtBEgPMRVfD722MVU3Ng3XABFR";
        byte[] decodedPrivateKey = Base58.decode(encodedPrivateKey);
        
        // Reconstruct the PrivateKey object
        Ed25519PrivateKeyParameters reconstructedPrivateKeyParams = 
                new Ed25519PrivateKeyParameters(decodedPrivateKey, 0);

        // Derive the public key from the private key
        Ed25519PublicKeyParameters derivedPublicKeyParams = reconstructedPrivateKeyParams.generatePublicKey();

        String encodedDerivedPublicKey = Base64.getEncoder().encodeToString(derivedPublicKeyParams.getEncoded());
        String encodeDerivedPublicKey = Base58.encode(derivedPublicKeyParams.getEncoded());
        System.out.println("Derived Public Key: " + encodedDerivedPublicKey);
    }
    
}
