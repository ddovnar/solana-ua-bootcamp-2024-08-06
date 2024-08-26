import "dotenv/config"
import {
    Cluster,
    Connection,
    Keypair,
    PublicKey,
    clusterApiUrl,
} from '@solana/web3.js'
import {
    getOrCreateAssociatedTokenAccount,
} from '@solana/spl-token'
import "dotenv/config"
import bs58 from 'bs58'

function secretToUint8Array(secretKey: string): Uint8Array {
    return secretKey.startsWith('[') ?
        Uint8Array.from(JSON.parse(secretKey))
        : bs58.decode(secretKey)
}

const cluster = process.env.CONNECTION_CLUSTER as Cluster
const secretKey = secretToUint8Array(process.env.SECRET_KEY)
const tokenMintAddress = 'FLZnJJSaWCQtDDMQUzwXtnQ3oLLvnDkXJoACMpd6GDNP'

async function createTokenAccount(tokenMintAddress: string): Promise<void> {
    const connection = new Connection(clusterApiUrl(cluster), 'confirmed')

    const payer = Keypair.fromSecretKey(secretKey)
    const mint = new PublicKey(tokenMintAddress)

    const tokenAccount = await getOrCreateAssociatedTokenAccount(
        connection,
        payer,
        mint,
        payer.publicKey
    )

    console.log('Associated token account:', tokenAccount.address.toBase58())
}

createTokenAccount(tokenMintAddress).catch((error) => {
    console.error('Error', error)
})