# 老年代年轻代区域对象和内存分配

## 需要在jdk版本8下实验
 - 因为从11开始，jvm都使用G1收集器，所以不需要再去关注年轻代和老年代的区别了
 - 但是在jdk8下，还是需要去关注年轻代和老年代的区别的
 - 为了方便理解，我们可以把年轻代和老年代看成是两个区域，这两个区域都是堆内存的一部分
 - 但是年轻代和老年代的区别在于，年轻代的对象存活时间比较短，老年代的对象存活时间比较长


## 例子
 - 例子1：大对象直接进入到老年代
 - 其中，-XX:PretenureSizeThreshold=1024768，表示大于1MB的对象直接进入到老年代
 - 还有一个参数，-XX:MaxTenuringThreshold=1，表示对象在年轻代的最大存活时间，这个参数默认是15，表示对象在年轻代最多存活15次，如果超过15次，就会进入到老年代
 - 但是，如果我们设置了-XX:MaxTenuringThreshold=1，表示对象在年轻代最多存活1次，如果超过1次，就会进入到老年代
 - 但是，如果我们设置了-XX:PretenureSizeThreshold=1024768，表示大于1MB的对象直接进入到老年代，那么，-XX:MaxTenuringThreshold=1这个参数就没有意义了
 - 我们设置的-XX:SurvivorRatio=8，表示年轻代中Eden区域和Survivor区域的比例是8:1。输出的结果中，我们可以看到，Eden区域的大小是279040K，Survivor区域的大小是34816K
```java
package org.example;

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
```
```shell
C:\Users\Nansh\.jdks\azul-1.8.0_372\bin\java.exe -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:PretenureSizeThreshold=10247678 "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.2\lib\idea_rt.jar=53798:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\cat.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\charsets.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\access-bridge-64.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\cldrdata.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\crs-agent.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\dnsns.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\jaccess.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\localedata.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\nashorn.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunec.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunjce_provider.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunmscapi.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunpkcs11.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\zipfs.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\jce.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\jfr.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\jsse.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\management-agent.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\resources.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\rt.jar;D:\github\nanshaqundao\geektime-advanced-java\exercise\04\jvm-project-2\target\classes org.example.YoungOldArea
Hello world!
Heap
 PSYoungGen      total 313856K, used 16742K [0x000000066b700000, 0x0000000680b80000, 0x00000007c0000000)
  eden space 279040K, 6% used [0x000000066b700000,0x000000066c759b30,0x000000067c780000)
  from space 34816K, 0% used [0x000000067e980000,0x000000067e980000,0x0000000680b80000)
  to   space 34816K, 0% used [0x000000067c780000,0x000000067c780000,0x000000067e980000)
 ParOldGen       total 697856K, used 409600K [0x00000003c2400000, 0x00000003ecd80000, 0x000000066b700000)
  object space 697856K, 58% used [0x00000003c2400000,0x00000003db400010,0x00000003ecd80000)
 Metaspace       used 3406K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 368K, capacity 388K, committed 512K, reserved 1048576K

Process finished with exit code 0
```