package thread_lifecycle;

public class BlockedExample {
  private final Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    BlockedExample example = new BlockedExample();

    Thread mainThread = Thread.currentThread(); // Reference to the main thread

    // Thread 1
    Thread thread1 =
        new Thread(
            () -> {
              example.syncMethod("Thread 1");
            });

    // Thread 2
    Thread thread2 =
        new Thread(
            () -> {
              example.syncMethod("Thread 2");
            });

    // Print initial states
    System.out.println("Main thread initial state: " + mainThread.getState());
    System.out.println("Thread 1 state: " + thread1.getState());
    System.out.println("Thread 2 state: " + thread2.getState());

    thread1.start();
    thread2.start();

    // Small delay to ensure Thread 1 likely gets the lock first
    Thread.sleep(100);

    // Print states after starting threads
    System.out.println("Main thread state after starting other threads: " + mainThread.getState());
    System.out.println("Thread 1 state after start: " + thread1.getState());
    System.out.println("Thread 2 state after start: " + thread2.getState());

    // Print states before threads joining
    for (int i = 0; i < 10; i++) {
      Thread.sleep(100);
      System.out.println("Main thread state during looping check: " + mainThread.getState());
      System.out.println("Looping Check: Thread 1 state before join: " + thread1.getState());
      System.out.println("Looping Check: Thread 2 state before join: " + thread2.getState());
    }
    // New monitoring thread that checks the state of the main thread
    Thread monitorThread =
        new Thread(
            () -> {
              while (true) {
                System.out.println("Monitor: Main thread state: " + mainThread.getState());
                try {
                  Thread.sleep(500); // Check every 500ms
                } catch (InterruptedException e) {
                  // Exit loop if interrupted
                  break;
                }
              }
            });
    monitorThread.start(); // Start monitoring

    // Wait for threads to finish
    thread1.join();
    thread2.join();

    monitorThread.interrupt(); // Interrupt the monitoring thread to finish its execution
    monitorThread.join(); // Wait for monitorThread to finish

    // Print states after threads joining
    System.out.println("Main thread state after threads join: " + mainThread.getState());
    System.out.println("Thread 1 state after join: " + thread1.getState());
    System.out.println("Thread 2 state after join: " + thread2.getState());

    // Print final states
    System.out.println("Main thread final state: " + mainThread.getState());
    System.out.println("Thread 1 final state: " + thread1.getState());
    System.out.println("Thread 2 final state: " + thread2.getState());
  }

  public void syncMethod(String threadName) {
    synchronized (lock) {
      String insideThreadName = "Inside Sync Block " + threadName;
      System.out.println(insideThreadName + " entered synchronized block!");

      // Print state inside synchronized block
      System.out.println(
          insideThreadName
              + " state inside synchronized block: "
              + Thread.currentThread().getState());

      try {
        // Simulate some work with sleep
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(insideThreadName + " exiting synchronized block!");
    }
  }
}
