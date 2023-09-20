package vt;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SubmissionTimeOfVTDemo {
  public static void main(String[] args) throws InterruptedException {
    var loops = 100_000;
    System.out.println("Virtual Threads Demo started");
    var start = System.currentTimeMillis();
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, loops)
          .forEach(
              i -> {
                executor.submit(
                    () -> {
                      Thread.sleep(Duration.ofSeconds(1));
                      return i;
                    });
              });
    } // executor.close() is called implicitly, and waits
    var end = System.currentTimeMillis();
    System.out.println("Time taken in milli seconds: " + (end - start));
    System.out.println("Virtual Threads Demo finished");
    Thread.sleep(5000);
    System.out.println("Platform Threads Demo started");
    start = System.currentTimeMillis();
    try (var executor = Executors.newCachedThreadPool()) {
      IntStream.range(0, loops)
          .forEach(
              i -> {
                executor.submit(
                    () -> {
                      Thread.sleep(Duration.ofSeconds(1));
                      return i;
                    });
              });
    }
    end = System.currentTimeMillis();
    System.out.println("Time taken in milli seconds: " + (end - start));
    System.out.println("Platform Threads Demo finished");
  }
}
