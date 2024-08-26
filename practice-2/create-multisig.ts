import "dotenv/config";
import { Connection, Keypair, PublicKey, Transaction, SystemProgram, TransactionInstruction, LAMPORTS_PER_SOL } from '@solana/web3.js';
import { createInitializeMintInstruction, createMintToInstruction, getMinimumBalanceForRentExemptMint, getAssociatedTokenAddress, createAssociatedTokenAccountInstruction, TOKEN_PROGRAM_ID, MINT_SIZE, createMultisig, createMint, getOrCreateAssociatedTokenAccount, mintTo, getMint } from '@solana/spl-token';

// Підключення до Solana Devnet
const connection = new Connection('https://api.devnet.solana.com');

let privateKey = process.env["SECRET_KEY"];
if (privateKey === undefined) {
    console.log("Add SECRET_KEY to .env!");
    process.exit(1);
}
const asArray = Uint8Array.from(JSON.parse(privateKey));
const owner1 = Keypair.fromSecretKey(asArray);

let privateKey2 = process.env["SECRET_KEY2"];
const asArray2 = Uint8Array.from(JSON.parse(privateKey2));
const owner2 = Keypair.fromSecretKey(asArray2);
// console.log('owner2 publicKey:', owner2.publicKey);

let privateKey3 = process.env["SECRET_KEY3"];
const asArray3 = Uint8Array.from(JSON.parse(privateKey3));
const owner3 = Keypair.fromSecretKey(asArray3);
// console.log('owner3 publicKey:', owner3.publicKey);

console.log('Signer1:', owner1.publicKey.toBase58());
console.log('Signer2:', owner2.publicKey.toBase58());
console.log('Signer3:', owner3.publicKey.toBase58());

const payer = owner1;

async function createMultisigToken() {
    // Отримання recentBlockhash
    const { blockhash } = await connection.getLatestBlockhash();

    // Перевірка балансу акаунтів
    const balance1 = await connection.getBalance(owner1.publicKey);
    const balance2 = await connection.getBalance(owner2.publicKey);
    const balance3 = await connection.getBalance(owner3.publicKey);

    console.log('Balance of owner1:', balance1);
    console.log('Balance of owner2:', balance2);
    console.log('Balance of owner3:', balance3);

    // Якщо баланс менше 1 SOL, запит на airdrop
    if (balance1 < LAMPORTS_PER_SOL) {
        const airdropSignature1 = await connection.requestAirdrop(owner1.publicKey, LAMPORTS_PER_SOL);
        await connection.confirmTransaction(airdropSignature1);
    }

    if (balance2 < LAMPORTS_PER_SOL) {
        const airdropSignature2 = await connection.requestAirdrop(owner2.publicKey, LAMPORTS_PER_SOL);
        await connection.confirmTransaction(airdropSignature2);
    }

    if (balance3 < LAMPORTS_PER_SOL) {
        const airdropSignature3 = await connection.requestAirdrop(owner3.publicKey, LAMPORTS_PER_SOL);
        await connection.confirmTransaction(airdropSignature3);
    }

    const multisigKey = await createMultisig(
        connection,
        payer,
        [
            owner1.publicKey,
            owner2.publicKey,
            owner3.publicKey
        ],
        2
    );

    console.log(`Created 2/3 multisig ${multisigKey.toBase58()}`);

    const mint = await createMint(
        connection,
        payer,
        multisigKey,
        multisigKey,
        9
    );

    const associatedTokenAccount = await getOrCreateAssociatedTokenAccount(
        connection,
        payer,
        mint,
        owner1.publicKey
    );

    // try {
    //     await mintTo(
    //       connection,
    //       payer,
    //       mint,
    //       associatedTokenAccount.address,
    //       multisigKey,
    //       1
    //     )
    // } catch (error) {
    //     console.log(error);
    // }

    await mintTo(
        connection,
        payer,
        mint,
        associatedTokenAccount.address,
        multisigKey,
        1,
        [
          owner1,
          owner2
        ]
      )
      
      const mintInfo = await getMint(
        connection,
        mint
      )
      
      console.log(`Minted ${mintInfo.supply} token`);
      // Minted 1 token
}

createMultisigToken().catch(console.error);
