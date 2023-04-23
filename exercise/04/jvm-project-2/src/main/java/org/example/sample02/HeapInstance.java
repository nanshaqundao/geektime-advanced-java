package org.example.sample02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
-Xmx600m -Xms600m -XX:+PrintGCDetails
通过每20ms创建一个1MB的对象，不断的创建对象，直到内存溢出、
在这个过程中一定会发生gc，这样就可以观察到gc的情况
这里主要观察from区和to区的变化

 */
public class HeapInstance {
    public static void main(String[] args) {
        List<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
}

class Picture {
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
