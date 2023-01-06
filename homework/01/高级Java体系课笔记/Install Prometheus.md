## 9.6 安装Prometheus

### 1）下载解压运行

```
# 下载  
wget -c https://github.com/prometheus/prometheus/releases/download/v2.15.1/prometheus-2.15.1.linux-amd64.tar.gz  
# 解压  
mkdir /usr/local/hero/  
tar zxvf prometheus-2.15.1.linux-amd64.tar.gz -C /usr/local/hero/  
cd /usr/local/hero/prometheus-2.15.1.linux-amd64  
# 运行  
nohup ./prometheus > prometheus.log 2>&1 &
```

### 2）配置prometheus

在prometheus.yml中加入如下配置：

```
  - job_name: 'hero-Linux'  
    static_configs:  
    - targets: ['172.17.187.78:9100','172.17.187.79:9100','172.17.187.81:9100']
```

### 3）测试Prometheus

测试Prometheus是否安装配置成功

[http://101.200.146.199:9090/targets](http://101.200.146.199:9090/targets)

![image-20220805231841589](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805231841589-166070321690715.png?lastModify=1672961716)

### 3）在Grafana中配置Prometheus的数据源:

![image-20220805230507639](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805230507639-166070321690816.png?lastModify=1672961716)

![image-20220805230806532](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805230806532-166070321690817.png?lastModify=1672961716)

### 4）Grafana导入Linux展示模板

导入Linux系统dashboard

-   Node Exporter for Prometheus Dashboard EN 20201010
    
    -   dashboard-ID: 11074
        
-   Node Exporter Dashboard
    
    -   dashboard-ID: 16098
        

![image-20220806120341454](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220806120341454-166070321690818.png?lastModify=1672961716)

![image-20220806121042249](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220806121042249-166070321690819.png?lastModify=1672961716)