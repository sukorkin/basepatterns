package com.basepatterns.yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task4Runner {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(r.readLine());
        if (n < 0 || n > 11) return;

        dfs(new char[n * 2], 0, 0, n);
    }

    private static void dfs(char[] chars, int left, int right, int n) {
        if (left == n && right == n) {
            System.out.println(chars);
            return;
        }

        if (left < n) {
            chars[left + right] = '(';
            dfs(chars, left + 1, right, n);
        }

        if (right < left) {
            chars[left + right] = ')';
            dfs(chars, left, right + 1, n);
        }
    }
}
