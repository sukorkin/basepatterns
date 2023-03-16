package com.basepatterns.yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task2Runner {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(r.readLine());
        if (n < 1 || n > 10000) return;

        int maxCountRepeat = 0;
        int countRepeat = 0;

        int currentCount = 0;
        while (currentCount < n) {
            String inputElem = r.readLine();
            currentCount++;
            if (inputElem.equals("1")) {
                countRepeat++;
            } else {
                countRepeat = 0;
            }

            if (countRepeat > maxCountRepeat) {
                maxCountRepeat = countRepeat;
            }
        }

        System.out.println(maxCountRepeat);
    }
}
