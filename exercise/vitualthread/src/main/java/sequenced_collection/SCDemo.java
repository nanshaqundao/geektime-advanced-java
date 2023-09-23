package sequenced_collection;

import java.util.ArrayList;
import java.util.SequencedCollection;

public class SCDemo {
  public static void main(String[] args) {
    SequencedCollection<Integer> sc = new ArrayList<>();
    sc.add(1);
    sc.add(2);
    sc.add(3);
    var x = sc.reversed();
    x.forEach(System.out::println);
    System.out.println(sc.getFirst());
    System.out.println(sc.getLast());
    sc.addLast(0);
  }
}
