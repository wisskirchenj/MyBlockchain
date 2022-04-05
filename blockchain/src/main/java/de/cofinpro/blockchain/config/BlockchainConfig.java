package de.cofinpro.blockchain.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class that collects configuration constants, chat messages etc..
 */
public class BlockchainConfig {

    private BlockchainConfig() {
        // no instantiation
    }

    public static final int BLOCKCHAIN_LENGTH = 6;

    public static final String SERIALIZE_PATH = "./blockchain/src/main/resources/data/blockchain.ser";
    public static final String KEY_PAIRS_PATH_PREFIX = "./blockchain/src/main/resources/data/";
    public static final String PUBLIC_KEY_SUFFIX = "_rsa.pub";
    public static final String PRIVATE_KEY_SUFFIX = "_rsa";
    public static final int RSA_KEY_LENGTH = 1024;

    public static final int BLOCK_MIN_CREATION_SECONDS = 1;
    public static final int BLOCK_MAX_CREATION_SECONDS = 5;
    public static final int MAX_CHAT_PAUSE_MILLISECONDS = 300;

    public static final List<String> CLIENTS = List.of("Peter", "Mary", "Caspar", "Balthazar");
    public static final int CHAT_CLIENT_COUNT = CLIENTS.size();
    public static final int MINER_COUNT = Runtime.getRuntime().availableProcessors() - CHAT_CLIENT_COUNT;

    public static final Map<Integer, String> CHAT_MESSAGES;

    static {
        Map<Integer, String> chatMessageMap = new HashMap<>();
        chatMessageMap.putAll(Map.of(
               1, "Allow Me to Introduce Myself.",
                2, "Good afternoon",
                3, "Good morning",
                4, "How are you?",
                5, "Hope this email finds you well",
                6, "I hope you enjoyed your weekend",
                7, "I hope you're doing well",
                8, "I hope you're having a great week",
                9, "I hope you're having a wonderful day",
                10, "It's great to hear from you"
        ));
        chatMessageMap.putAll(Map.of(
                11, "I'm eager to get your advice on that",
                12, "I'm reaching out about some urgent matter",
                13, "Thank you for your help",
                14, "Thank you for the update",
                15, "Thanks for getting in touch",
                16, "Thanks for the quick response",
                17, "As promised, I'm back",
                18, "As we discussed on our call, I like you",
                19, "Can you provide me with an update",
                20, "I'm checking in on my cat"
        ));
        chatMessageMap.putAll(Map.of(
                21, "I'm getting back to you about that",
                22, "To follow up on our meeting",
                23, "Congratulations on your recent accomplishment",
                24, "How did the recent project turn out?",
                25, "I hope you enjoyed your vacation",
                26, "I loved your recent social media post",
                27, "I was just laughing the other day about an inside joke",
                28, "I was just thinking about you and our shared memory",
                29, "It was great to see you at the fair",
                30, "This video made me think of you"
        ));
        CHAT_MESSAGES = Map.copyOf(chatMessageMap);
    }
}
