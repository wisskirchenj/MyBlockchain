package topics.enums;

import java.util.Arrays;

/**
 * You are given a hidden enum named Secret with some constants in uppercase.
 * Write a program that counts and prints how many constants in the enumeration start with "STAR".
 */
public class EnumMatchCounter {

    enum Secret {
        STAR, CRASH, START
    }

    public static void main(String[] args) {
        System.out.println(Arrays.stream(Secret.values()).filter(val -> val.name().startsWith("STAR")).count());
    }
}