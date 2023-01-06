## (OLD) Ali Cloud CentOS7.6 配置Docker环境

1）yum 包更新到最新

```sudo yum update```

2）安装需要的软件包， yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的

```
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```

3）设置yum源为阿里云

配置yum源的代理，类似于maven镜像仓库，加速下载软件。

```
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

4）安装docker

```
sudo yum install docker-ce  
# 启动  
systemctl s
```

5）安装后查看docker版本

```
docker -v
```

## (NEW) AWS AMI 2 Linux
The commands are slightly different
```shell
sudo yum update -y 

sudo amazon-linux-extras install docker 

sudo yum install docker 

sudo service docker start 

sudo usermod -a -G docker ec2-user 
```
start the docker service and make it enable during boot time as well.

```shell
sudo service docker start
sudo systemctl enable docker.service
```