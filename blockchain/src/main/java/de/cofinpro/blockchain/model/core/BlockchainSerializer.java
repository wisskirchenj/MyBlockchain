package de.cofinpro.blockchain.model.core;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import static de.cofinpro.blockchain.config.BlockchainConfig.SERIALIZE_PATH;

/**
 * BlockchainSerializer class using Buffered Object(In-)OutputStreams. The path to the serialization file is
 * taken from a BlockchainConfig constant.
 */
@Slf4j
public class BlockchainSerializer {

    /**
     * default serialization of the non-transient instance fields via ObjectOutputStream.writeObject
     * @param blockchain blockchain to serialize
     */
    public void serialize(Blockchain blockchain) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(SERIALIZE_PATH)))) {
            oos.writeObject(blockchain);
        } catch (IOException exception) {
            log.error("cannot serialize to file " + SERIALIZE_PATH +"\n" + exception.getMessage());
        }
    }

    /**
     * default deserialization of the non-transient instance fields via ObjectInputStream.readObject
     * all possible exceptions handled properly and logged as error or trace.
     * @return the deserialized blockchain if successful or creates an empty one on error
     */
    public Blockchain deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(SERIALIZE_PATH)))) {
            return (Blockchain) ois.readObject();
        } catch (FileNotFoundException exception) {
            log.trace("No serialization data found.");
        } catch (IOException exception) {
            log.error("IO-error reading deserialization file " + SERIALIZE_PATH +"!\n"
                    + exception.getMessage());
        } catch (ClassNotFoundException exception) {
            log.error("cannot deserialize file " + SERIALIZE_PATH +". File corrupted!\n"
                    + exception.getMessage());
        }
        return new Blockchain();
    }
}
