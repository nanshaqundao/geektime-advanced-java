## 9.4 安装Grafana

### 1）下载Grafana镜像：

docker pull grafana/grafana

### 2）启动Grafana容器：

启动Grafana容器，将3000端口映射出来

docker run -d --name grafana -p 3000:3000 grafana/grafana

### 3）验证部署成功

网页端访问[http://101.200.146.199:3000](http://101.200.146.199:3000/)验证部署成功

![image-20220805220250107](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805220250107-16607032169075.png?lastModify=1672961716)

默认账户密码：admin\admin

### 4）选择添加数据源

![image-20220805220506308](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805220506308-16607032169076.png?lastModify=1672961716)

### 5）找到并选择 influxdb :

![image-20220805220532624](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805220532624-16607032169077.png?lastModify=1672961716)

### 6）配置数据源

![image-20220805220843824](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805220843824-16607032169078.png?lastModify=1672961716)

数据源创建成功时会有绿色的提示：

![image-20220805220857119](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805220857119-16607032169079.png?lastModify=1672961716)

### 7）导入模板

![image-20220805220928449](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805220928449-166070321690710.png?lastModify=1672961716)

模板导入分别有以下3种方式：

-   直接输入模板id号
    
-   直接上传模板json文件
    
-   直接输入模板json内容
    

![image-20220805221015894](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805221015894-166070321690711.png?lastModify=1672961716)

### 8）找展示模板

在Grafana的官网找到我们需要的展示模板

-   Apache JMeter Dashboard
    
    -   dashboad-ID：5496
        
-   JMeter Dashboard(3.2 and up)
    
    -   dashboad-ID：3351
        

### 9）导入找到的模板，使用模板id

导入模板，我这里选择输入模板id号，导入后如下，配置好模板名称和对应的数据源，然后 import 即可

![image-20220805222457536](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805222457536-166070321690712.png?lastModify=1672961716)

### 10）查看效果

展示设置，首先选择创建的application

![image-20220805224529225](file:///D:/training/geektime/advancedJava/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01-%E9%A1%B9%E7%9B%AE%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BAv1.0/image-20220805224529225-166070321690713.png?lastModify=1672961716)

**注意：** 如果我们修改过表名，也就是在jmeter的Backend Listener的measurement配置(默认为jmeter)，这个时候就需要去设置中进行修改，我这里使用的就是默认的，所以无需修改。