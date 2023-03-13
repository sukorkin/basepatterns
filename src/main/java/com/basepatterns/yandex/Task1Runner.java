package com.basepatterns.yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task1Runner {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String J = r.readLine();
        String S = r.readLine();

        int result = 0;

        for (char ch : S.toCharArray()) {
            if (J.indexOf(ch) >= 0) {
                ++result;
            }
        }

        System.out.println(result);
    }
}
