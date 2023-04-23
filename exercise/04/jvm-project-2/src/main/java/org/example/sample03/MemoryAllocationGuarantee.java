package org.example.sample03;

public class MemoryAllocationGuarantee {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        memoryAllocation();
    }

    private static void memoryAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[1 * _1MB];
        allocation2 = new byte[1 * _1MB];
        allocation3 = new byte[1 * _1MB];
        allocation4 = new byte[5 * _1MB]; // 出现一次Minor GC

        System.out.println("完毕");
    }


}
