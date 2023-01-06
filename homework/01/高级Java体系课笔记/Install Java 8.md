#1. 查看当前Linux系统是否已经安装java
```
    rpm -qa | grep -i java
```
    
#2. 解压已上传的JDK压缩包，并移动到/usr/local目录下
```
   mkdir /usr/local/hero
   tar -zxvf /root/jdk-8u261-linux-x64.tar.gz -C /usr/local/hero
```

#3. 测试jdk
```
    /usr/local/hero/jdk1.8.0_261/bin/java -version
```
    
#4. 配置环境变量
```
  vim /etc/profile
     G 跳转到最后一行
     i 进入插入模式
     export JAVA_HOME=/usr/local/hero/jdk1.8.0_261
     export PATH=$PATH:$JAVA_HOME/bin
     esc 进入命令行模式
     :wq! 保存
```
  
#5. 更新环境变量
```
    source /etc/profile
```
  
#6. 测试
```
    java -version
```