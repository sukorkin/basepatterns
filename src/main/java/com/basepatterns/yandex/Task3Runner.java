package com.basepatterns.yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Task3Runner {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(r.readLine());
        if (n < 1) return;

        var els = new ArrayList<Integer>();
        int currentCount = 1;
        int el = Integer.parseInt(r.readLine());
        els.add(el);
        int nextEl;
        while (currentCount < n) {
            nextEl = Integer.parseInt(r.readLine());
            if (nextEl != el) {
                els.add(nextEl);
            }
            el = nextEl;
            currentCount++;
        }

        els.forEach(System.out::println);
    }
}