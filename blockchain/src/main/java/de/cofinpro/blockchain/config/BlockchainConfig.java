package de.cofinpro.blockchain.config;

import java.util.List;

/**
 * class that collects configuration constants, chat messages etc..
 */
public class BlockchainConfig {

    private BlockchainConfig() {
        // no instantiation
    }

    public static final int BLOCKCHAIN_LENGTH = 10;

    public enum Mode  {
        CHAT, TRANSACTIONS
    }
    public static final Mode BLOCKCHAIN_MODE = Mode.TRANSACTIONS;
    public static final String SERIALIZE_PATH = BLOCKCHAIN_MODE == Mode.CHAT
            ? "./blockchain/src/main/resources/data/blockchain_chat.ser"
            : "./blockchain/src/main/resources/data/blockchain_trans.ser";
    public static final int BLOCK_REWARD = 100;

    public static final String KEY_PAIRS_PATH_PREFIX = "./blockchain/src/main/resources/data/";
    public static final String PUBLIC_KEY_SUFFIX = "_rsa.pub";
    public static final String PRIVATE_KEY_SUFFIX = "_rsa";
    public static final int RSA_KEY_LENGTH = 1024;

    public static final int BLOCK_MIN_CREATION_SECONDS = 1;
    public static final int BLOCK_MAX_CREATION_SECONDS = 3;
    public static final int MAX_CLIENT_PAUSE_MILLISECONDS = 500;

    public static final List<String> CLIENTS = List.of("Peter", "Mary", "Caspar", "Balthazar");
    public static final int CLIENT_COUNT = CLIENTS.size();
    public static final int MINER_COUNT = Runtime.getRuntime().availableProcessors() - CLIENT_COUNT;

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
