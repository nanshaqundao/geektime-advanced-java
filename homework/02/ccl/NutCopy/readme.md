# 使用说明
 - 把src/resources下的.txt 文件后缀改为.java后缀
 - 把Test.java放进classloader package 编译
 - 把编译后的Test.class文件放在指定目录下(参考Demo)
 - 把Test.java从项目删除然后重新编译。这样确保Test.class不会在项目默认的target目录下，这样即使运行成功我们也没法确定Test.class是从指定目录加载还是从target加载的
 - 运行Demo，可以看到结果
   ```
   "C:\Program Files\Java\jdk-17.0.5\bin\java.exe" 
   "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.1\lib\idea_rt.jar=59621:C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2022.3.1\bin"
    -Dfile.encoding=UTF-8
    -classpath D:\github\nanshaqundao\geektime-advanced-java\homework\02\ccl\NutCopy\target\classes classloader.Demo
   
   
   Hello HeroClassLoader
   classloader.HeroClassLoader@1b28cdfa
   
   Process finished with exit code 0
   ```