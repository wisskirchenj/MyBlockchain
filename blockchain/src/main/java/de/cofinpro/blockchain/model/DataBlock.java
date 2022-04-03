package de.cofinpro.blockchain.model;

/**
 * generic interface extension of the block interface. Implementations of a DataBlock "decorate" a
 * block with some data storage - in accordance with the Decorator pattern.
 * @param <T> the type of the block data to be stored in the block
 */
public interface DataBlock<T> extends Block {

    /**
     * @return the block data of generic type T
     */
    T getData();

    /**
     * @param data the block data of generic type T
     */
    void setData(T data);
}
