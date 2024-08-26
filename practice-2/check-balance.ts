import "dotenv/config";
import {
  Connection,
  LAMPORTS_PER_SOL,
  PublicKey,
  clusterApiUrl,
} from "@solana/web3.js";

const connection = new Connection(clusterApiUrl("devnet"));
console.log(`⚡️ Connected to devnet`);

const publicKey = new PublicKey("2nvvSfQojSTph3VnJSttS2z8Xa9XpuiUFFRuhvgm4cTb");

const balanceInLamports = await connection.getBalance(publicKey);

const balanceInSOL = balanceInLamports / LAMPORTS_PER_SOL;

console.log(
  `The balance for the wallet at address ${publicKey} is: ${balanceInSOL}`
);

/*
pig carry exhibit depth forest divorce dog original verify foot language stereo
*/
