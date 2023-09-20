package vt;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class CompletionTimeofVTDemo {
  public static void main(String[] args) throws InterruptedException {
    var loops = 100_000;

    // Virtual Threads Demo
    System.out.println("Virtual Threads Demo started");
    long startSubmission = System.currentTimeMillis();
    List<Future<Integer>> virtualThreadFutures = new ArrayList<>();
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, loops)
          .forEach(
              i -> {
                Future<Integer> future =
                    executor.submit(
                        () -> {
                          Thread.sleep(Duration.ofSeconds(1));
                          return i;
                        });
                virtualThreadFutures.add(future);
              });
    }
    long endSubmission = System.currentTimeMillis();

    // Waiting for all virtual threads to complete
    virtualThreadFutures.forEach(
        future -> {
          try {
            future.get();
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
    long endCompletion = System.currentTimeMillis();
    System.out.println(
        "Submission time for Virtual Threads: " + (endSubmission - startSubmission) + " ms");
    System.out.println(
        "Total time for Virtual Threads: " + (endCompletion - startSubmission) + " ms");

    // Platform Threads Demo
    System.out.println("Platform Threads Demo started");
    startSubmission = System.currentTimeMillis();
    List<Future<Integer>> platformThreadFutures = new ArrayList<>();
    try (var executor = Executors.newCachedThreadPool()) {
      IntStream.range(0, loops)
          .forEach(
              i -> {
                Future<Integer> future =
                    executor.submit(
                        () -> {
                          Thread.sleep(Duration.ofSeconds(1));
                          return i;
                        });
                platformThreadFutures.add(future);
              });
    }
    endSubmission = System.currentTimeMillis();

    // Waiting for all platform threads to complete
    platformThreadFutures.forEach(
        future -> {
          try {
            future.get();
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
    endCompletion = System.currentTimeMillis();
    System.out.println(
        "Submission time for Platform Threads: " + (endSubmission - startSubmission) + " ms");
    System.out.println(
        "Total time for Platform Threads: " + (endCompletion - startSubmission) + " ms");
  }
}
