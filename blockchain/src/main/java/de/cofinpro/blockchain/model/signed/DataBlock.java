package de.cofinpro.blockchain.model.signed;

import de.cofinpro.blockchain.model.core.Block;
import de.cofinpro.blockchain.model.magic.MagicBlock;

import java.io.Serial;
import java.util.List;

import static de.cofinpro.blockchain.config.BlockchainConfig.*;

/**
 * generic data block class implementing the SignedDataBlock interface. A DataBlock "decorates" a
 * block with some data storage - in accordance with the Decorator pattern.
 * @param <T> the type of the block data (List of Signable) to be stored in the block
 */
public class DataBlock<T extends List<? extends Signable>> implements SignedDataBlock {

    @Serial
    private static final long serialVersionUID = 71L;

    protected final MagicBlock block;
    private T data = null;

    /**
     * package-private access since creation should be done by the factory
     */
    DataBlock(Block block) {
        this.block = (MagicBlock) block;
    }

    /**
     * @return the block data of generic type T
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the block data of generic type T
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String getHash() {
        return block.getHash();
    }

    @Override
    public void setHash(String hash) {
        block.setHash(hash);
    }

    @Override
    public String getPreviousHash() {
        return block.getPreviousHash();
    }

    @Override
    public long getTimestamp() {
        return block.getTimestamp();
    }

    @Override
    public long getElapsedTimeInSeconds() {
        return block.getElapsedTimeInSeconds();
    }

    @Override
    public void setElapsedTimeInSeconds(long elapsedTimeInSeconds) {
        block.setElapsedTimeInSeconds(elapsedTimeInSeconds);
    }

    @Override
    public int getLeadingHashZeros() {
        return block.getLeadingHashZeros();
    }

    @Override
    public int getMinerId() {
        return block.getMinerId();
    }

    @Override
    public void setLeadingHashZeros(int leadingHashZeros) {
        block.setLeadingHashZeros(leadingHashZeros);
    }

    @Override
    public void setMagicNumber(int magicNumber) {
        block.setMagicNumber(magicNumber);
    }

    @Override
    public void setMinerId(int minerId) {
        block.setMinerId(minerId);
    }

    /**
     * creates special empty message or joins the chat messages.
     * @return string representation of the chat block data
     */
    @Override
    public String getDataString() {
        if (getData().isEmpty()) {
            return BLOCKCHAIN_MODE == BlockchainMode.CHAT ?
                    "Block data: no messages\n": "Block data:\nNo transactions\n";
        }
        StringBuilder builder = new StringBuilder("Block data:\n");
        getData().forEach(e -> builder.append(e).append("\n"));
        return builder.toString();
    }

    /**
     * message output as desired by project specification (stage 4).
     * Uses protected messages of MagicBlock plus data specific output
     * @return string representation of this block
     */
    @Override
    public String toString() {
        return getBlockStateString() + getDataString() + block.getGeneratingString();
    }

    private String getBlockStateString() {
        if (BLOCKCHAIN_MODE == BlockchainMode.CHAT) {
            return block.getBlockStateString();
        }
        return String.format("Block:%nCreated by miner%d%nminer%d gets 100 VC %nId: %d%n" +
                        "Timestamp: %d%nMagic number: %d%n" +
                        "Hash of the previous block:%n%s%nHash of the block:%n%s%n"
                ,block.getMinerId(), block.getMinerId(), block.getId(), block.getTimestamp(),
                block.getMagicNumber(), block.getPreviousHash(), block.getHash());
    }
}
