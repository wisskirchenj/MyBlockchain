package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.config.BlockchainConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class BlockchainSerializer {

    public void serialize(Blockchain blockchain) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(BlockchainConfig.SERIALIZE_PATH)))) {
            oos.writeObject(blockchain);
        } catch (IOException exception) {
            log.error("cannot serialize to file " + BlockchainConfig.SERIALIZE_PATH +"\n" + exception.getMessage());
        }
    }

    public Blockchain deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(BlockchainConfig.SERIALIZE_PATH)))) {
            return (Blockchain) ois.readObject();
        } catch (FileNotFoundException exception) {
            log.trace("No serialization data found.");
        } catch (IOException exception) {
            log.error("IO-error reading deserialization file " + BlockchainConfig.SERIALIZE_PATH +"!\n"
                    + exception.getMessage());
        } catch (ClassNotFoundException exception) {
            log.error("cannot deserialize file " + BlockchainConfig.SERIALIZE_PATH +". File corrupted!\n"
                    + exception.getMessage());
        }
        return new Blockchain();
    }
}
