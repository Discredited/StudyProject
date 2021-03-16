package com.june.studyproject;

import java.util.Scanner;

class Main {

    // 字符串压缩
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            System.out.println(compression(sc.next()));
        }

        System.out.println(compression("abcccca"));
        System.out.println(compression("abbcccc"));
        System.out.println(compression("june"));
        System.out.println(compression("a"));
        System.out.println(compression(""));
        System.out.println(compression(null));
    }

    public static String compression(String string) {
        // 特殊情况判定
        if (null == string || string.length() == 0) return "";
        if (string.length() == 1) return string;

        char current = string.charAt(0);
        int count = 1;

        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < string.length(); i++) {
            if (current == string.charAt(i)) {
                count++;
            } else {
                builder.append(current);
                if (count > 1) {
                    builder.append(count);
                }

                current = string.charAt(i);
                count = 1;
            }
        }

        builder.append(current);
        if (count > 1) {
            builder.append(count);
        }

        return builder.toString();
    }
}
