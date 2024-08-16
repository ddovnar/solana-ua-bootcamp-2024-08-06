package com.dovnard.solana;

import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;

/**
 *
 * @author dovnard
 */
public class CheckBalance {
    
    public static final long LAMPORTS_PER_SOL = 1_000_000_000;
    
    public static void main(String[] args) throws RpcException {
        RpcClient client = new RpcClient(Cluster.DEVNET);
        
        String pkEncoded = "56SEgA1FneM5psD8ggWst1fLAZnyWECaWwitYk8QDidz";
        PublicKey publicKey = new PublicKey(pkEncoded);
        
        long balanceInLamports = client.getApi().getBalance(publicKey);
        long balanceInSOL = balanceInLamports / LAMPORTS_PER_SOL;
        System.out.println("The balance for the wallet at address " + pkEncoded + " is: " + balanceInSOL);
    }
}
