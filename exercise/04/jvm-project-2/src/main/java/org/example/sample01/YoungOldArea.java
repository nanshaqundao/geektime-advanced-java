package org.example.sample01;

public class YoungOldArea {
    /**
     * 测试：大对象直接进入到老年代
     * -Xmx60m -Xms60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * -XX:PretenureSizeThreshold
     *
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");
        byte[] buffer = new byte[1024 * 1024 * 400]; //400MB
    }
}