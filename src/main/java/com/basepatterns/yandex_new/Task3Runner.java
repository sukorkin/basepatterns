package com.basepatterns.yandex_new;

import java.io.*;

public class Task3Runner {
    static final String input = "input.txt";
    static final String output = "output.txt";
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new FileReader(input));
        bw = new BufferedWriter(new FileWriter(output));

        String line = br.readLine();
        String[] tokens = line.split(" ");
        int n = Integer.parseInt(tokens[0]);
        if (n < 2 || n > 300) return;
        int s = Integer.parseInt(tokens[1]);
        if (s < 1 || s > 100000) return;

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            x[i] = Integer.parseInt(tokens[0]);
            y[i] = Integer.parseInt(tokens[1]);
        }

        int maxCount = 0;
        int[] maxPoints = new int[5];
        for (int j = s; j >= 1; j--) {
            int b = s / j;
            int count = 0;
            int[] points = new int[5];
            for (int i = 0; i < n; i++) {
                if (x[i] <= j && y[i] <= b) {
                    count++;
                    points[i] = i + 1;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxPoints = points;
            }
        }

        bw.write(maxCount + "\n");
        for (var point : maxPoints) {
            if (point != 0) {
                bw.write(point + " ");
            }
        }

        br.close();
        bw.close();
    }
}