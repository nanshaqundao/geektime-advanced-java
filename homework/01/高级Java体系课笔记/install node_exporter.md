## 9.5 安装node_exporter

```
# 下载  
wget -c https://github.com/prometheus/node_exporter/releases/download/v0.18.1/node_exporter-0.18.1.linux-amd64.tar.gz  
# 解压  
tar zxvf node_exporter-0.18.1.linux-amd64.tar.gz -C /usr/local/hero/  
# 启动  
cd /usr/local/hero/node_exporter-0.18.1.linux-amd64  
nohup ./node_exporter > node.log 2>&1 &
```

**注意：在被监控服务器中配置开启端口9100**