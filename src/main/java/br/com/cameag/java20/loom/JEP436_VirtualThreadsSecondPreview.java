package br.com.cameag.java20.loom;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class JEP436_VirtualThreadsSecondPreview {

    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(i +" -> "+ new Random().nextInt());
                });
            });
        }
    }
}
