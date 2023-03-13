package com.basepatterns.yandex;

import java.io.*;
import java.util.*;

public class Task6Runner {
    static final String input = "input.txt";
    static final String output = "output.txt";
    static BufferedReader br;
    static BufferedWriter bw;
    static int maxDistance;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(input));
        bw = new BufferedWriter(new FileWriter(output));

        int citiesN = Integer.parseInt(br.readLine());
        int[] x = new int[citiesN];
        int[] y = new int[citiesN];

        for (int i = 0; i < citiesN; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            x[i] = Integer.parseInt(tokens[0]);
            y[i] = Integer.parseInt(tokens[1]);

        }

        maxDistance = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String[] tokens = line.split(" ");
        int startCity = Integer.parseInt(tokens[0]) - 1;
        int endCity = Integer.parseInt(tokens[1]) - 1;

        if (startCity == endCity) return;

        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(startCity, 0);
        List<Integer> visited = new ArrayList<>();
        visited.add(0);
        int minWays = -1;
        while (counts.size() > 0) {
            var currentKey = counts.keySet().stream().findFirst();
            int i = currentKey.get();
            var count = counts.get(i);
            counts.remove(i);
            if (i == endCity) {
                minWays = count;
                break;
            }
            for (int j = 0; j < citiesN; j++) {
                if (i == j) continue;
                int distance = Math.abs(x[j] - x[i]) + Math.abs(y[j] - y[i]);
                if (!visited.contains(j) && distance <= maxDistance) {
                    counts.put(j, count + 1);
                    visited.add(j);
                }
            }
        }

        bw.write(String.valueOf(minWays));
        br.close();
        bw.close();
    }
}