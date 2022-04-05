package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.security.RSASignerAndValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

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
            validateBlockMessages(block, previousBlock);
        }
        if (!isBlockValid(block, block.getLeadingHashZeros(), null)) {
            throw new InvalidBlockchainException(ERROR);
        }
    }

    /**
     * validate all messages of a block regarding signature authenticity as well as validity of message
     * id. It is invoked during the total check of a blockchain after deserialization.
     * Calls isMessageValid() to perform result
     * @param block the block whose messages are to be validated
     * @param previousBlock the previous block for validating ascending message ids
     * @throws InvalidBlockchainException if validation fails
     */
    private void validateBlockMessages(Block block, Block previousBlock) {
        if (block instanceof ChatDataBlock chatDataBlock) {
            ChatDataBlock previous = (ChatDataBlock) previousBlock;
            if (chatDataBlock.getData().stream()
                    .anyMatch(signedMessage -> !isMessageValid(signedMessage, previous))) {
                throw new InvalidBlockchainException(ERROR);
            }
        }
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
     * @param signedMessage the signed message to be validated
     * @param previous the previous block for validating ascending message ids
     * @throws InvalidBlockchainException only if block instance is of unexpected type. (not if validation fails!)
     */
    public boolean isMessageValid(SignedMessage signedMessage, Block previous) {
        if (previous instanceof ChatDataBlock chatDataBlock) {
            return RSASignerAndValidator.isValid(signedMessage) && messageIdIsValid(signedMessage, chatDataBlock);
        } else {
            log.error("Implementation error in BlockChain-Validator: isMessageValid called with wrong block type");
            throw new InvalidBlockchainException(
                    "Implementation error - Blockchain-Validator called with wrong block type.");
        }
    }

    /**
     * validates a messageId, i.e. checks whether it is bigger than all the id's of the previous block.
     * @param signedMessage the signed message to be validated
     * @param chatDataBlock the previous block for validating ascending message ids
     */
    private boolean messageIdIsValid(SignedMessage signedMessage, ChatDataBlock chatDataBlock) {
        return chatDataBlock.getData().stream()
                .noneMatch(chatMessage -> chatMessage.getId() >= signedMessage.getId());
    }
}
