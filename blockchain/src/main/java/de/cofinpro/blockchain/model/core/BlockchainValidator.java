package de.cofinpro.blockchain.model.core;

import de.cofinpro.blockchain.model.signed.*;
import de.cofinpro.blockchain.security.RSASignerAndValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;

/**
 * validator class, that knows how to validate a complete blockchain - method validateChain(...) as well
 * as to validate a new block before addition to the chain - method isNewBlocValid(...)
 */
@Slf4j
public class BlockchainValidator {

    private static final String ERROR = "Invalid Blockchain !";

    /**
     * validates the whole blockchain regarding hash validation as well as leading zero validation.
     * @param blockchain the chain to validate
     * @throws InvalidBlockchainException extends RuntimeException if blockchain is invalid.
     */
    public void validateChain(Blockchain blockchain) throws InvalidBlockchainException {
        if (blockchain.isEmpty()) {
            return;
        }
        Iterator<Block> currentIterator = blockchain.descendingIterator();
        Iterator<Block> previousIterator = blockchain.descendingIterator();
        Block block = previousIterator.next(); // skip one block
        while (previousIterator.hasNext()) {
            block = currentIterator.next();
            Block previousBlock = previousIterator.next();
            if (!isBlockValid(block, block.getLeadingHashZeros(), previousBlock)) {
                throw new InvalidBlockchainException(ERROR);
            }
            validateBlockData(blockchain, block, previousBlock);
        }
        if (!isBlockValid(block, block.getLeadingHashZeros(), null)) {
            throw new InvalidBlockchainException(ERROR);
        }
    }

    /**
     * validate all the block data regarding signature authenticity as well as validity of their
     * data objects (message id for chat). The method is invoked during the total check of a blockchain
     * after deserialization.
     * @param blockchain the blockchain
     * @param block the block whose messages are to be validated
     * @param previousBlock the previous block for validating ascending message ids
     * @throws InvalidBlockchainException if validation fails
     */
    private void validateBlockData(Blockchain blockchain, Block block, Block previousBlock) {
        if (block instanceof SignedDataBlock dataBlock) {
            SignedDataBlock previous = (SignedDataBlock) previousBlock;
            if (dataBlock.getData().stream()
                    .anyMatch(signable -> !isDataValidInitial(blockchain, signable, previous))) {
                throw new InvalidBlockchainException(ERROR);
            }
        }
    }

    private boolean isDataValidInitial(Blockchain blockchain, Signable signable, SignedDataBlock previous) {
        if (signable instanceof SignedTransaction) {
            return RSASignerAndValidator.isValid(signable);
        }
        return isDataValid(blockchain, signable, previous);
    }

    /**
     * validates a new block - is called by blockchain before adding this block to the chain.
     * @param block the new block to validate
     * @param leadingHashZeros the requested leading hash zeros
     * @param previous the previous block in the chain or null if chain was empty
     * @return validation result
     */
    public boolean isBlockValid(Block block, int leadingHashZeros, Block previous) {
        boolean valid = block.getHash().startsWith("0".repeat(leadingHashZeros));
        if (previous != null) {
            valid &= block.hashMatchesPrevious(previous);
        }
        return valid;
    }

    /**
     * validates a signed message  regarding signature authenticity as well as validity of message
     * id - sub call to messageIdIsValid().
     * @param signable the signed message to be validated
     * @param previous the previous block for validating ascending message ids
     * @throws InvalidBlockchainException only if block instance is of unexpected type. (not if validation fails!)
     */
    public boolean isDataValid(Blockchain blockchain, Signable signable, SignedDataBlock previous) {
        if (signable instanceof SignedMessage signedMessage) {
            return RSASignerAndValidator.isValid(signedMessage)
                    && messageIdIsValid(signedMessage, previous);

        } else if (signable instanceof SignedTransaction signedTransaction) {
            return RSASignerAndValidator.isValid(signable)
                    && transactionIsValid(signedTransaction, blockchain.getLedger());
        } else {
            log.error("Implementation error in BlockChain-Validator: isMessageValid called with wrong block type");
            throw new InvalidBlockchainException(
                    "Implementation error - Blockchain-Validator called with wrong block type.");
        }
    }

    /**
     * checks, if the sender has a sufficient balance for the offered transaction
     * @param transaction the transaction data
     * @param ledger the blockchain's ledger
     * @return the validity check result
     */
    private boolean transactionIsValid(SignedTransaction transaction, Map<String, Integer> ledger ) {
        return ledger.getOrDefault(transaction.getSender(), 0) >= transaction.getAmount();
    }

    /**
     * validates a messageId, i.e. checks whether it is bigger than all the id's of the previous block.
     * @param signedMessage the signed message to be validated
     * @param chatDataBlock the previous block for validating ascending message ids
     */
    private boolean messageIdIsValid(SignedMessage signedMessage, SignedDataBlock chatDataBlock) {
        return chatDataBlock.getData().stream()
                .noneMatch(chatMessage -> ((SignedMessage)chatMessage).getId() >= signedMessage.getId());
    }
}
