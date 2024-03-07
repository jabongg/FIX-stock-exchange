package com.staywell.java;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FirstRepeatedCharacter {
    public static void main(String[] args) {
        String str = "abcdefbgahij"; // Example string

        char firstRepeatedChar = findFirstRepeatedCharJava7(str);

        if (firstRepeatedChar != '\0') {
            System.out.println("First repeated character: " + firstRepeatedChar);
        } else {
            System.out.println("No repeated characters found.");
        }
    }

    public static char findFirstRepeatedChar(String str) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        char a = str.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !set.add(c))
                .findFirst()
                .orElse('\0');
        return a;
    }

    public static char findFirstRepeatedCharJava7(String str) {
        Set<Character> set = new HashSet<>();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!set.add(c)) { // ye logic to wahi soch sakta h, jise add method ka internal pta ho
                return c;
            }
        }
        return '\0';
    }
}
