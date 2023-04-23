# 内存担保

## 运行情况日志分析
 - 伊甸园区域 8M
 - 年轻代 10M
 - 老年代 10M
 - 前三个对象分配在年轻代，第四个对象分配在老年代，出现一次Minor GC。这是因为在第三个对象分配完毕时，年轻代剩余空间只有5M，如果再分配一个5M的对象，就会出现内存不足的情况，
 - 这时候虚拟机会检查老年代的最大连续空间是否大于等于新生代的对象大小，如果大于等于，就会进行一次Minor GC，将年轻代的对象移动到老年代，然后再分配对象

```java
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

```
```shell
"C:\Program Files\Java\jdk-17.0.5\bin\java.exe" -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.2\lib\idea_rt.jar=63682:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.2\bin" -Dfile.encoding=UTF-8 -classpath D:\github\nanshaqundao\geektime-advanced-java\exercise\04\jvm-project-2\target\classes org.example.sample03.MemoryAllocationGuarantee
[0.004s][warning][gc] -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
[0.009s][info   ][gc] Using Serial
[0.009s][info   ][gc,init] Version: 17.0.5+9-LTS-191 (release)
[0.009s][info   ][gc,init] CPUs: 16 total, 16 available
[0.009s][info   ][gc,init] Memory: 65388M
[0.009s][info   ][gc,init] Large Page Support: Disabled
[0.009s][info   ][gc,init] NUMA Support: Disabled
[0.009s][info   ][gc,init] Compressed Oops: Enabled (32-bit)
[0.009s][info   ][gc,init] Heap Min Capacity: 20M
[0.009s][info   ][gc,init] Heap Initial Capacity: 20M
[0.009s][info   ][gc,init] Heap Max Capacity: 20M
[0.009s][info   ][gc,init] Pre-touch: Disabled
[0.010s][info   ][gc,metaspace] CDS archive(s) mapped at: [0x0000000800000000-0x0000000800bd0000-0x0000000800bd0000), size 12386304, SharedBaseAddress: 0x0000000800000000, ArchiveRelocationMode: 0.
[0.010s][info   ][gc,metaspace] Compressed class space mapped at: 0x0000000800c00000-0x0000000840c00000, reserved size: 1073741824
[0.010s][info   ][gc,metaspace] Narrow klass base: 0x0000000800000000, Narrow klass shift: 0, Narrow klass range: 0x100000000
[0.074s][info   ][gc,start    ] GC(0) Pause Young (Allocation Failure)
[0.077s][info   ][gc,heap     ] GC(0) DefNew: 4875K(9216K)->697K(9216K) Eden: 4875K(8192K)->0K(8192K) From: 0K(1024K)->697K(1024K)
[0.077s][info   ][gc,heap     ] GC(0) Tenured: 0K(10240K)->3072K(10240K)
[0.077s][info   ][gc,metaspace] GC(0) Metaspace: 409K(576K)->409K(576K) NonClass: 385K(448K)->385K(448K) Class: 23K(128K)->23K(128K)
[0.077s][info   ][gc          ] GC(0) Pause Young (Allocation Failure) 4M->3M(19M) 3.345ms
[0.077s][info   ][gc,cpu      ] GC(0) User=0.00s Sys=0.00s Real=0.00s
完毕
[0.078s][info   ][gc,heap,exit] Heap
[0.078s][info   ][gc,heap,exit]  def new generation   total 9216K, used 6106K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
[0.078s][info   ][gc,heap,exit]   eden space 8192K,  66% used [0x00000000fec00000, 0x00000000ff1486d8, 0x00000000ff400000)
[0.078s][info   ][gc,heap,exit]   from space 1024K,  68% used [0x00000000ff500000, 0x00000000ff5ae450, 0x00000000ff600000)
[0.078s][info   ][gc,heap,exit]   to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
[0.078s][info   ][gc,heap,exit]  tenured generation   total 10240K, used 3072K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
[0.078s][info   ][gc,heap,exit]    the space 10240K,  30% used [0x00000000ff600000, 0x00000000ff900030, 0x00000000ff900200, 0x0000000100000000)
[0.078s][info   ][gc,heap,exit]  Metaspace       used 415K, committed 576K, reserved 1056768K
[0.078s][info   ][gc,heap,exit]   class space    used 24K, committed 128K, reserved 1048576K

Process finished with exit code 0

```