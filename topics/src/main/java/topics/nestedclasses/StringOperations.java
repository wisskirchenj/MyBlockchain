package topics.nestedclasses;

/**
 * There is an outer class StringOperations. Your task is to create a static nested class EngString inside of StringOperations.
 * The class EngString should have:
 * two fields: boolean english and String string
 * a constructor that accepts these fields
 * getters for both fields: isEnglish() and getString()
 */
public class StringOperations {

    // create static nested class EngString
    static class EngString {
        private boolean english;
        private String string;

        EngString(boolean english, String string) {
            this.english = english;
            this.string = string;
        }

        boolean isEnglish() {
            return english;
        }

        String getString() {
            return string;
        }
    }
}
