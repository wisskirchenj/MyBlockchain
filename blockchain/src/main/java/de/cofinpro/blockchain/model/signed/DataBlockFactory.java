package de.cofinpro.blockchain.model.signed;

import de.cofinpro.blockchain.model.magic.MagicBlockFactory;

import java.security.NoSuchAlgorithmException;

/**
 * factory class for generic data block implementing the SignedDataBlock interface. It extends
 * the MagicBlockFactory and overwrites its createBlock() method to include setting block
 * data - before (!) the computational method call setHashRelatedFields, that is inherited from
 * the MagicBlockFactory and not overridden.
 * @param <L> the type of the block data (List of Signable) to be stored in the created blocks
 */
public class DataBlockFactory<L extends SerializableList<Signable>> extends MagicBlockFactory {

    private final L data;

    public DataBlockFactory(int leadingHashZeros, L data) {
        super(leadingHashZeros);
        this.data = data;
    }

    /**
     * the factory method that creates a new block
     * @param id the id of the block to create
     * @param previousHash previousHash the hash link to the previous created block in a blockchain.
     * @return created block
     */
    @Override
    public SignedDataBlock createBlock(int id, String previousHash) throws NoSuchAlgorithmException {
        DataBlock<L> block = newBlockInstance(id, previousHash);
        setBlockData(block);
        setHashRelatedFields(block);
        return block;
    }

    @Override
    protected DataBlock<L> newBlockInstance(int id, String previousHash) {
        return new DataBlock<>(super.newBlockInstance(id, previousHash));
    }

    /**
     * overrides dummy default implementation in abstract BlockFactory class.
     * since this method is called from the abstract BlockFactory's createBlock, the given block
     * is always of type ChatDataBlock.
     * @param block newly created block instance of type ChatDataBlock
     */
    protected void setBlockData(DataBlock<L> block) {
        block.setData(data);
        String threadName = Thread.currentThread().getName();
        block.setMinerId(Integer.parseInt(threadName.substring(threadName.lastIndexOf('-') + 1)));
    }
}
