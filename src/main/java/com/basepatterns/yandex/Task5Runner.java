package com.basepatterns.yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Task5Runner {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String J = r.readLine();
        String S = r.readLine();

        char[] s = S.toCharArray();
        char[] j = J.toCharArray();
        Arrays.sort(s);
        Arrays.sort(j);

        System.out.println(Arrays.equals(s, j) ? 1 : 0);
    }
}
