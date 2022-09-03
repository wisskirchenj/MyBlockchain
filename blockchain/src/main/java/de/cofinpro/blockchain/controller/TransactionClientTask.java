package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.core.Blockchain;
import de.cofinpro.blockchain.model.signed.SignedTransaction;
import de.cofinpro.blockchain.security.RSASignerAndValidator;

import java.security.KeyPair;
import java.util.Map;

import static de.cofinpro.blockchain.config.BlockchainConfig.*;

/**
 * Runnable implementation, that is performed in the chat clients thread pool.
 * Creates random digitally signed transaction among miners and clients in random time intervals all
 * configurable in the BlockchainConfig.
 */
public class TransactionClientTask extends ClientTask {

    public TransactionClientTask(Blockchain blockchain, String name, KeyPair keyPair) {
        super(blockchain, name, keyPair);
    }

    /**
     * the transaction client task is to send a random transaction digitally signed to the blockchain, which in
     * turn provides the ledger to choose possible money sender's
     * The amount is generated in a way, that it intentionally may exceed the balance (1.02 * possible amount) in rare
     * cases. In that case, the blockchain must reject the transaction.
     */
    @Override
    protected void performClientTask() {
        Map<String, Integer> ledger = blockchain.getLedger();
        if (ledger.isEmpty()) {
            return;
        }
        String moneySender = ledger.keySet().stream().toList()
                .get(RANDOM.nextInt(ledger.size()));
        if (ledger.get(moneySender) == 0) {
            return;
        }

        int tryAmount = findRandomAmount(ledger.get(moneySender));
        String moneyReceiver = findRandomReceiver(moneySender);
        SignedTransaction transaction =
                new SignedTransaction(moneySender, tryAmount, moneyReceiver, keyPair.getPublic());
        transaction.setSigned(RSASignerAndValidator.sign(transaction.toString(), keyPair.getPrivate()));
        blockchain.offerTransaction(transaction);
    }

    /**
     * The amount is generated in a way, that it intentionally may exceed the balance (1.02 * possible amount) in rare
     * cases.
     * @param senderBalance the balance of the sender
     * @return the random amount - which may be slightly too high.
     */
    private int findRandomAmount(int senderBalance) {
        int amount = RANDOM.nextInt((int) (senderBalance * 1.02));
        return amount == 0 ? 1 : amount;
    }

    /**
     * randomly finds a money receiver different form the sender given. While the sender is randomly chosen only
     * among the participants who have a positive account balance in the blockchain's ledger, the receiver is chosen
     * over all miners and clients.
     * @param sender the money sender previously chosen
     * @return the name of the money receiver
     */
    private String findRandomReceiver(String sender) {
        String receiver;
        do {
            int random = RANDOM.nextInt(MINER_COUNT + CLIENT_COUNT);
            if (random < MINER_COUNT) {
                receiver =  String.format("miner%d", random + 1);
            } else {
                receiver = CLIENTS.get(random - MINER_COUNT);
            }
        } while (sender.equals(receiver));
        return receiver;
    }
}
