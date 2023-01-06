## 9.2 安装InfluxDB

1）下载InfluxDB的镜像：

docker pull influxdb:1.8

2）启动InfluxDB的容器，并将端口 8083 和 8086 映射出来：

docker run -d --name influxdb -p 8086:8086 -p 8083:8083 influxdb:1.8

3）进入容器内部，创建名为jmeter的数据库：

进入 jmeter-influx 容器

docker exec -it influxdb /bin/bash

-   输入`influx`命令，即可进入 influx 操作界面
    
-   输入`create database jmeter` 命令，创建名为 jmeter 的数据库
    
-   输入`show databases` 命令，查看数据库创建成功
    

root@517f57017d99:/# influx  
Connected to http://localhost:8086 version 1.7.10  
InfluxDB shell version: 1.7.10  
> create database jmeter  
> show databases

4）使用JMeter 库， select 查看数据，这个时候是没有数据的：

-   输入`use jmeter`命令，应用刚才创建的数据库
    
-   输入`select * from jmeter`命令，查询库中有哪些数据
    

> use jmeter  
> select * from jmeter


想要将 JMeter的测试数据导入 InfluxDB ，就需要在 JMeter中使用 Backend Listener 配置