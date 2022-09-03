package de.cofinpro.blockchain.model.signed;

import de.cofinpro.blockchain.model.core.Block;

/**
 * classes implementing this interface carry a list of RSA-signable data objects
 * as block data.
 */
public interface SignedDataBlock extends Block {

    /**
     * method should provide a string representing the block data list, as requested by the output
     * specification.
     * @return string representation of block data list
     */
    String getDataString();

    SerializableList<Signable> getData();
}
