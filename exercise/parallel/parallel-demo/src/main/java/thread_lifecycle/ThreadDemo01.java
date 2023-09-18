package thread_lifecycle;

public class ThreadDemo01 {
  public static void main(String[] args) {
    Runnable r =
        new Runnable() {
          @Override
          public void run() {
            System.out.println("thread running...");
          }
        };
    Thread thread = new Thread(r);
    thread.start();
    Object o = new Object();
  }
}
