package topics.comparator;

import java.util.Comparator;
import java.util.List;

/**
 * You have a User class with two fields: String name and int age. Implement a method that takes a list of users, List<User>,
 * and sorts it according to the following rule:
 * Users should be sorted by their names in lexicographic order and if two or more users have the same names, they should
 * be sorted by their age in descending order. You don't need to read or write anything from or to the console, just implement the method.
 */
class TwoFieldComparator {

    public static void sortUsers(List<User> users) {
        users.sort(Comparator.comparing(User::getName).thenComparing(Comparator.comparing(User::getAge).reversed()));
    }
}