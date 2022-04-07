package de.cofinpro.blockchain.model.signed;

import de.cofinpro.blockchain.model.magic.MagicBlockFactory;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DataBlockFactory<T extends List<? extends Signable>> extends MagicBlockFactory {

    private final T data;

    public DataBlockFactory(int leadingHashZeros, T chatData) {
        super(leadingHashZeros);
        this.data = chatData;
    }

    /**
     * the factory method that creates a new block
     * @param id the id of the block to create
     * @param previousHash previousHash the hash link to the previous created block in a blockchain.
     * @return created block
     */
    @Override
    public SignedDataBlock createBlock(int id, String previousHash) throws NoSuchAlgorithmException {
        DataBlock<T> block = newBlockInstance(id, previousHash);
        setBlockData(block);
        setHashRelatedFields(block);
        return block;
    }

    @Override
    protected DataBlock<T> newBlockInstance(int id, String previousHash) {
        return new DataBlock<>(super.newBlockInstance(id, previousHash));
    }

    /**
     * overrides dummy default implementation in abstract BlockFactory class.
     * since this method is called from the abstract BlockFactory's createBlock, the given block
     * is always of type ChatDataBlock.
     * @param block newly created block instance of type ChatDataBlock
     */
    protected void setBlockData(DataBlock<T> block) {
        block.setData(data);
        String threadName = Thread.currentThread().getName();
        block.setMinerId(Integer.parseInt(threadName.substring(threadName.lastIndexOf('-') + 1)));
    }
}
