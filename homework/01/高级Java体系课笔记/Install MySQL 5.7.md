（1）拉取mysql镜像

```
docker pull mysql:5.7
```

（2）创建容器

```
docker run -id --name=c_mysql -p 3306:3306 \  
-v /root/mysql/logs:/logs \  
-v /root/mysql/data:/var/lib/mysql \  
-v /root/mysql/conf:/etc/mysql/conf.d \  
-e MYSQL_ROOT_PASSWORD=123456 mysql:5.7  
​  
​  
docker cp c_mysql:/etc/mysql/conf.d .
```

-p 代表端口映射，格式为  宿主机映射端口:容器运行端口

-e 代表添加环境变量  MYSQL_ROOT_PASSWORD 是root用户的登陆密码

（3）设置容器开机自动启动

```
docker ps -a 获取容器ID
docker update --restart=always 容器ID

docker restart $(docker ps -q) 重启动all
```

（4）远程登录mysql

连接宿主机的IP ,指定端口为3306