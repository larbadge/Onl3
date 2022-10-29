package com.mikhalov.homework4;

public class HomeWork4 {

    private static boolean isStringsEquals(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] chArr1 = str1.toCharArray();
        char[] chArr2 = str2.toCharArray();
        for (int i = 0; i < chArr1.length; i++) {
            if (chArr1[i] != chArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void getFirstAndLastChars(String str) {
        char firstChar = str.charAt(0);
        char lastChar = str.charAt(str.length() - 1);
        System.out.printf("First char: %c, last char: %c%n", firstChar, lastChar);

    }

    public static boolean isStringEndsWith(String str, String subStr) {
        //return str.endsWith(subStr);
        int j = str.length() - subStr.length();
        String subEnd = str.substring(j);
        return isStringsEquals(subStr, subEnd);
    }

    public static boolean isStringContains(String str, String subStr) {
        // return str.contains(subStr);
        int j = subStr.length();
        for (int i = 0; i < str.length() - j; i++) {
            if (isStringsEquals(str.substring(i, (i + j)), subStr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStringsEqualsIgnoreCase(String str1, String str2) {
        //return str1.equalsIgnoreCase(str2);
        return isStringsEquals(str1.toLowerCase(), str2.toLowerCase());
    }

    public static boolean isStringStartsWith(String str, String subStr) {
        //return str.startsWith(subStr);
        int j = subStr.length();
        return isStringsEquals(str.substring(0, j), (subStr));
    }
}
