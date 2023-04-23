# survival区互相拷贝

## 配置虚拟机 -Xmx600m -Xms600m -XX:+PrintGCDetails

- 通过每20ms创建一个1MB的对象，不断的创建对象，直到内存溢出、
- 在这个过程中一定会发生gc，这样就可以观察到gc的情况
- 这里主要观察from区和to区的变化
- 可以看出，进行了2次youngGC，然后 full GC数次后最终OOM
- 同时如果观察visualVM中的堆内存情况，可以看到survival区的内存在不断的变化，说明survival区的内存是互相拷贝的

```shell
C:\Users\Nansh\.jdks\azul-1.8.0_372\bin\java.exe -Dvisualvm.id=41901426513400 -Xmx600m -Xms600m -XX:+PrintGCDetails "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.2\lib\idea_rt.jar=56282:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\cat.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\charsets.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\access-bridge-64.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\cldrdata.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\crs-agent.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\dnsns.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\jaccess.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\localedata.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\nashorn.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunec.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunjce_provider.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunmscapi.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\sunpkcs11.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\ext\zipfs.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\jce.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\jfr.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\jsse.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\management-agent.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\resources.jar;C:\Users\Nansh\.jdks\azul-1.8.0_372\jre\lib\rt.jar;D:\github\nanshaqundao\geektime-advanced-java\exercise\04\jvm-project-2\target\classes org.example.sample02.HeapInstance
[GC (Allocation Failure) [PSYoungGen: 152946K->25598K(179200K)] 152946K->137190K(588800K), 0.0155407 secs] [Times: user=0.00 sys=0.02, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 179198K->25584K(179200K)] 290790K->289993K(588800K), 0.0186653 secs] [Times: user=0.03 sys=0.11, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 25584K->0K(179200K)] [ParOldGen: 264409K->289768K(409600K)] 289993K->289768K(588800K), [Metaspace: 3958K->3958K(1056768K)], 0.0286238 secs] [Times: user=0.01 sys=0.00, real=0.03 secs]
[Full GC (Ergonomics) [PSYoungGen: 153600K->33388K(179200K)] [ParOldGen: 289768K->409547K(409600K)] 443368K->442935K(588800K), [Metaspace: 3958K->3958K(1056768K)], 0.0187564 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 153125K->152908K(179200K)] [ParOldGen: 409547K->409547K(409600K)] 562672K->562456K(588800K), [Metaspace: 3958K->3958K(1056768K)], 0.0164428 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
[Full GC (Allocation Failure) [PSYoungGen: 152908K->152908K(179200K)] [ParOldGen: 409547K->409485K(409600K)] 562456K->562394K(588800K), [Metaspace: 3958K->3958K(1056768K)], 0.0149462 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
Heap
PSYoungGen      total 179200K, used 153600K [0x00000000f3800000, 0x0000000100000000, 0x0000000100000000)
eden space 153600K, 100% used [0x00000000f3800000,0x00000000fce00000,0x00000000fce00000)
from space 25600K, 0% used [0x00000000fe700000,0x00000000fe700000,0x0000000100000000)
to   space 25600K, 0% used [0x00000000fce00000,0x00000000fce00000,0x00000000fe700000)
ParOldGen       total 409600K, used 409489K [0x00000000da800000, 0x00000000f3800000, 0x00000000f3800000)
object space 409600K, 99% used [0x00000000da800000,0x00000000f37e4548,0x00000000f3800000)
Metaspace       used 3992K, capacity 4572K, committed 4864K, reserved 1056768K
class space    used 434K, capacity 460K, committed 512K, reserved 1048576K
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
at org.example.sample02.Picture.<init>(HeapInstance.java:32)
at org.example.sample02.HeapInstance.main(HeapInstance.java:23)

Process finished with exit code 1
```