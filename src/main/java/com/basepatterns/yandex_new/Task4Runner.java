package com.basepatterns.yandex_new;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Task4Runner {
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
        if (n < 1 || n > 200000) return;
        int m = Integer.parseInt(tokens[1]);
        if (m < 1 || m > 200000) return;
        int k = Integer.parseInt(tokens[2]);
        if (k < 1 || k > 200000) return;

        int[] x = new int[n];
        int[] y = new int[n];
        int[] z = new int[n];

        String[] results = new String[n];

        Queue<Task>[] queues = new Queue[k];
        for (int i = 0; i < k; i++) {
            queues[i] = new LinkedBlockingQueue<>();
        }
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            x[i] = Integer.parseInt(tokens[0]);
            y[i] = Integer.parseInt(tokens[1]);
            z[i] = Integer.parseInt(tokens[2]);
        }

        ExecutorService consumers = Executors.newFixedThreadPool(m);
        AtomicInteger qNumber = new AtomicInteger(0);
        AtomicInteger taskCount = new AtomicInteger(0);
        final long start = System.currentTimeMillis();
        Runnable rConsumer = () -> {
            long threadId = Thread.currentThread().getId() % m + 1;
            while (n >= taskCount.getAndIncrement()) {
                try {
                    while (Arrays.stream(queues).allMatch(Collection::isEmpty)){}
                    int qMax = 0;
                    int size = queues[0].size();
                    for (int i = 1; i < k; i++) {

                        if (size < queues[i].size()) {
                            qMax = i;
                        }
                    }
                    Task task;
                    if (m > 1) {
                        task = queues[qMax].poll();
                    } else {
                        task = queues[qNumber.getAndIncrement()].poll();
                    }
                    if (qNumber.get() >= k) {
                        qNumber.getAndSet(0);
                    }
                    results[task.taskNumber() - 1] = threadId + ":" + (System.currentTimeMillis() - start) / 1000;
                    Thread.sleep(task.taskTime * 1000L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        for (int i = 0; i < m; i++) {
            consumers.submit(rConsumer);
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        AtomicInteger sekCounter = new AtomicInteger(0);
        AtomicInteger taskReadCount = new AtomicInteger(0);
        Runnable r = () -> {
            while (n >= taskReadCount.getAndIncrement()) {
                try {
                    Thread.sleep(1000);
                    sekCounter.getAndIncrement();
                    for (int i = 0; i < n; i++) {
                        if (sekCounter.get() == x[i]) {
                            queues[y[i] - 1].add(new Task(start + x[i] * 1000L, z[i], (i + 1)));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        executor.submit(r);
        consumers.shutdown();
        executor.shutdown();
        consumers.awaitTermination(14 * 1000L, TimeUnit.MILLISECONDS);
        executor.awaitTermination(14 * 1000L, TimeUnit.MILLISECONDS);

        for (String result : results) {
            var out = result.split(":");
            bw.write(out[0] + " " + out[1] + "\n");
        }

        consumers.shutdownNow();
        executor.shutdownNow();
        br.close();
        bw.close();
    }

    public record Task(long startTime, int taskTime, int taskNumber) {

    }
}