package de.cofinpro.blockchain.model;

import java.io.Serial;
import java.util.List;

/**
 * concrete chat implementation of a DataBlock with data type List<String> to store chat messages
 * created during the creation of the previous block.
 * Instances keep a MagicBlock as a field and "decorate" it with block data - using Decorator pattern.
 */
public class ChatDataBlock implements DataBlock<List<SignedMessage>> {
    @Serial
    private static final long serialVersionUID = 43L;

    private final MagicBlock block;
    private List<SignedMessage> data = null;

    public ChatDataBlock(Block block) {
        this.block = (MagicBlock) block;
    }

    /**
     * message output as desired by project specification (stage 4).
     * Uses protected messages of MagicBlock plus data specific output
     * @return string representation of this block
     */
    @Override
    public String toString() {
        return block.getBlockStateString() + getDataString() + block.getGeneratingString();
    }

    /**
     * creates special empty message or joins the chat messages.
     * @return string representation of the chat block data
     */
    protected String getDataString() {
        if (getData().isEmpty()) {
            return "Block data: no messages\n";
        }
        StringBuilder builder = new StringBuilder("Block data:\n");
        getData().forEach(e -> builder.append(e).append("\n"));
        return builder.toString();
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

    @Override
    public List<SignedMessage> getData() {
        return data;
    }

    @Override
    public void setData(List<SignedMessage> data) {
        this.data = data;
    }
}
