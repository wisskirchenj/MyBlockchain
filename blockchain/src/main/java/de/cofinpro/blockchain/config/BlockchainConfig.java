package de.cofinpro.blockchain.config;

import java.util.List;

/**
 * class that collects configuration constants, chat messages etc.
 * of course would be nicer to read property json / yaml ... -> maybe some day
 */
public class BlockchainConfig {

    private BlockchainConfig() {
        // no instantiation
    }

    /**
     * creation stops after this length constant is reached
     */
    public static final int BLOCKCHAIN_LENGTH = 47;

    /**
     * enum type that also defines a value-dependent serializationPath.
     */
    public enum BlockchainMode  {
        CHAT("./blockchain/src/main/resources/data/blockchain_chat.ser"),
        TRANSACTIONS("./blockchain/src/main/resources/data/blockchain_trans.ser");

        private final String serializationPath;

        BlockchainMode(String serializationPath) {
            this.serializationPath = serializationPath;
        }

        /**
         * serialization path - blockchain is stored to after every new block.
         * depending on blockchain mode two paths are used.
         */
        public String getSerializationPath() {
            return serializationPath;
        }
    }

    /**
     * setting of BlockchainMode defines, if blockchain encrypts chat messages or VC transactions,
     * VC being our virtual coins (as bitcoins).
     */
    public static final BlockchainMode BLOCKCHAIN_MODE = BlockchainMode.TRANSACTIONS;

    /**
     * VC reward a miner gets for providing a new block first
     */
    public static final int BLOCK_REWARD = 100;

    /**
     * path and length information for clients RSA key pairs.
     */
    public static final String KEY_PAIRS_PATH_PREFIX = "./blockchain/src/main/resources/data/";
    public static final String PUBLIC_KEY_SUFFIX = "_rsa.pub";
    public static final String PRIVATE_KEY_SUFFIX = "_rsa";
    public static final int RSA_KEY_LENGTH = 1024;

    /**
     * lower limit for block creation duration: complexity is increased if
     * last creation time was below this constant.
     */
    public static final int BLOCK_MIN_CREATION_SECONDS = 1;

    /**
     * upper limit for block creation duration: complexity is decreased if
     * last creation time was above this constant.
     */
    public static final int BLOCK_MAX_CREATION_SECONDS = 3;

    /**
     * max time interval (i.e. Random.nextInt(MAX_CLIENT_PAUSE_MILLISECONDS) )
     * between next action (message or transaction) of a client thread
     */
    public static final int MAX_CLIENT_PAUSE_MILLISECONDS = 700;

    /**
     * list of the clients with names - each client runs in different thread.
     */
    public static final List<String> CLIENTS = List.of("Peter", "Mary", "Caspar", "Balthazar");
    public static final int CLIENT_COUNT = CLIENTS.size();

    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    /**
     * we create as many miners as we have processors not used by clients - but at least 4 miners:
     * e.g. for 10 processors of MacBook with M1, we get 4 client and 6 miner threads
     */
    public static final int MINER_COUNT = PROCESSORS < CLIENT_COUNT + 4 ? 4 : PROCESSORS - CLIENT_COUNT;

    public static final List<String> CHAT_MESSAGES = List.of("Allow Me to Introduce Myself.",
                "Good afternoon",
                "Good morning",
                "How are you?",
                "Hope this email finds you well",
                "I hope you enjoyed your weekend",
                "I hope you're doing well",
                "I hope you're having a great week",
                "I hope you're having a wonderful day",
                "It's great to hear from you",
                "I'm eager to get your advice on that",
                "I'm reaching out about some urgent matter",
                "Thank you for your help",
                "Thank you for the update",
                "Thanks for getting in touch",
                "Thanks for the quick response",
                "As promised, I'm back",
                "As we discussed on our call, I like you",
                "Can you provide me with an update",
                "I'm checking in on my cat",
                "I'm getting back to you about that",
                "To follow up on our meeting",
                "Congratulations on your recent accomplishment",
                "How did the recent project turn out?",
                "I hope you enjoyed your vacation",
                "I loved your recent social media post",
                "I was just laughing the other day about an inside joke",
                "I was just thinking about you and our shared memory",
                "It was great to see you at the fair",
                "This video made me think of you");
}
