package de.cofinpro.blockchain.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * our Blockchain class extends from (i.e. is a) LinkedList to be able to iterate it in both directions.
 * it just offers convenient Methods to add a block and get a reverse iterator. Further it can provide a string
 * to print its content.
 */
public class Blockchain extends LinkedList<Block> {

    public void addBlock(Block block) {
        add(block);
    }

    public Iterator<Block> getBackwardsIterator() {
        return descendingIterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        forEach(builder::append);
        return builder.toString();
    }
}
