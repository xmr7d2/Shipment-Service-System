package com.example.distribution.utils;

import java.util.Random;

public final class RandomUtil {

    public static String next() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

}
