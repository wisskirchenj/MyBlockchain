package de.cofinpro.blockchain.model;

import java.util.List;

/**
 * factory class for creating ChatDataBlocks.
 */
public class ChatDataBlockFactory extends MagicBlockFactory {

    private final List<SignedMessage> chatData;

    public ChatDataBlockFactory(int leadingHashZeros, List<SignedMessage> chatData) {
        super(leadingHashZeros);
        this.chatData = chatData;
    }

    @Override
    protected Block newBlockInstance(int id, String previousHash) {
        return new ChatDataBlock(super.newBlockInstance(id, previousHash));
    }

    /**
     * overrides dummy default implementation in abstract BlockFactory class.
     * since this method is called from the abstract BlockFactory's createBlock, the given block
     * is always of type ChatDataBlock.
     * @param block newly created block instance of type ChatDataBlock
     */
    @Override
    protected void setBlockData(Block block) {
        if (block instanceof ChatDataBlock dataBlock) {
            dataBlock.setData(chatData);
        } else {
            // somebody called this abnormally...
            System.exit(1);
        }
    }
}
