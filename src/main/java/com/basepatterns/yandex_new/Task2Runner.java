package com.basepatterns.yandex_new;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Task2Runner {
    static final String input = "input.txt";
    static final String output = "output.txt";
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new FileReader(input));
        bw = new BufferedWriter(new FileWriter(output));

        int n = Integer.parseInt(br.readLine());
        if (n < 1 || n > 100000) return;

        int[][] payments = new int[n][5];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int amount = Integer.parseInt(tokens[0]);
            String[] tokens1 = tokens[1].split("[.]");
            int startDay = Integer.parseInt(tokens1[0]);
            int startMonth = Integer.parseInt(tokens1[1]);
            tokens1 = tokens[2].split("[.]");
            int endDay = Integer.parseInt(tokens1[0]);
            int endMonth = Integer.parseInt(tokens1[1]);
            payments[i] = new int[]{amount, startDay, startMonth, endDay, endMonth};
        }

        double[] sums = new double[4];
        for (var payment : payments) {
            double[] sum = getSumInQuarter(payment);
            for (int i = 0; i < 4; i++) {
                sums[i] = sums[i] + sum[i];
            }
        }
        for (var sum : sums) {
            bw.write(String.format("%.2f", sum) + "\n");
        }

        br.close();
        bw.close();
    }

    private static double[] getSumInQuarter(int[] payment) {
        var sumInQuarter = new double[4];
        double amt = getDayAmount(payment[0], payment[1], payment[2], payment[3], payment[4]);
        var daysInQuarter = getDaysBetweenInQuarter(payment[1], payment[2], payment[3], payment[4]);
        for (int i = 0; i < 4; i++) {
            sumInQuarter[i] = amt * daysInQuarter[i];
        }

        return sumInQuarter;
    }

    private static double getDaysBetween(int startDay, int startMonth, int endDay, int endMonth) {
        var firstDate = LocalDate.of(2022, startMonth, startDay);
        var secondDate = LocalDate.of(2022, endMonth, endDay);
        return (double) ChronoUnit.DAYS.between(firstDate, secondDate) + 1;
    }

    private static double[] getDaysBetweenInQuarter(int startDay, int startMonth, int endDay, int endMonth) {
        int startQuarter = getQuarter(startMonth);
        int endQuarter = getQuarter(endMonth);
        var firstDate = LocalDate.of(2022, startMonth, startDay);
        var secondDate = LocalDate.of(2022, endMonth, endDay);
        double[] daysInQuarter = new double[4];
        for (int i = startQuarter; i <= endQuarter; i++) {
            if (i == 1) {
                firstDate = LocalDate.of(2022, 1, 1);
                secondDate = LocalDate.of(2022, 3, 31);
            }
            if (i == 2) {
                firstDate = LocalDate.of(2022, 4, 1);
                secondDate = LocalDate.of(2022, 6, 30);
            }
            if (i == 3) {
                firstDate = LocalDate.of(2022, 7, 1);
                secondDate = LocalDate.of(2022, 9, 30);
            }
            if (i == 4) {
                firstDate = LocalDate.of(2022, 10, 1);
                secondDate = LocalDate.of(2022, 12, 31);
            }
            if (i == startQuarter) {
                firstDate = LocalDate.of(2022, startMonth, startDay);
            }
            if (i == endQuarter) {
                secondDate = LocalDate.of(2022, endMonth, endDay);
            }
            daysInQuarter[i - 1] = (double) ChronoUnit.DAYS.between(firstDate, secondDate) + 1;
        }
        return daysInQuarter;
    }

    private static Integer getQuarter(int month) {
        return month % 3 == 0 ? (month / 3) : (month / 3) + 1;
    }

    private static double getDayAmount(int amount, int startDay, int startMonth, int endDay, int endMonth) {
        double scale = Math.pow(10, 2);
        double dayAmount = amount / getDaysBetween(startDay, startMonth, endDay, endMonth);
        return Math.floor(dayAmount * scale) / scale;
    }
}
