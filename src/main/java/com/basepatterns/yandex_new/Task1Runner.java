package com.basepatterns.yandex_new;

import java.io.*;
import java.util.Arrays;

public class Task1Runner {
    static final String input = "input.txt";
    static final String output = "output.txt";
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(input));
        bw = new BufferedWriter(new FileWriter(output));

        int width = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String[] tokens = line.split(" ");
        int n = Integer.parseInt(tokens[0]);
        int k = Integer.parseInt(tokens[1]);

        double[] heights = new double[n];
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            tokens = line.split("x");
            double w = Integer.parseInt(tokens[0]);
            double h = Integer.parseInt(tokens[1]);
            h = Math.ceil(h * width / w);
            heights[i] = h;
        }

        Arrays.sort(heights);

        double min = 0;
        for (int i = 0; i < k; i++) {
            min = min + heights[i];
        }

        double max = 0;
        for (int i = n - k; i < n; i++) {
            max = max + heights[i];
        }

        bw.write(String.format("%.0f", min) + "\n");
        bw.write(String.format("%.0f", max));

        br.close();
        bw.close();
    }
}
