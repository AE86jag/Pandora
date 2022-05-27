# TaoShop

#### 介绍
我想加入车队

#### 软件架构
一、	MySQL数据库安装
自定义mysql版本为8.0.18
用户名：root；密码：Jay-123456!
二、	Jenkins
用户名：ae86
密码：jay-123456!
三、	SFTPD文件服务器
101.200.74.208
FTP文件夹是/ftpfile
FTP用户是jay，密码是Jay-123456!
location / {
    root   /ftpfile;
}

四、	NGINX
监听520端口，
img. huijinshi88.com下的请求都会转发到FTP文件服务器文件夹下
源码包在/root/nginx文件夹下
安装目录是在/usr/local/nginx/

五、	JDK
rpm安装包在/root/jdk下；
六、	Gradle
Zip包在/root/gradle下
七、	git
/usr/local/git
八、	Jenkins
端口8088
默认安装目录是/var/lib/Jenkins
默认git拉取代码目录/var/lib/jenkins/workspace/
部署的工作目录
服务器接收jar包的路径/root/taoshop
服务器用户名root；密码jay-123456!
应用日志在/root/taoshop/log目录下
架构总览
项目是个单体应用，部署在Linux机器上，前后端分离；
技术栈是SpringBoot、Mybatis、MySQL；
使用Git进行版本管理，Flyway进行数据库版本管理；
项目中的图片使用VSFTPD文件服务器；
图片请求通过Nginx转发，实现图片预览；
项目使用Jenkins持续构建持续集成；

Jenkins下载源
http://mirror.esuni.jp/jenkins/updates/update-center.json
http://mirror.xmission.com/jenkins/updates/update-center.json
http://localhost:8080/pluginManager/advanced

java -jar PropertiesSpringBoot-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

http://有读权限的用户名:该用户名密码@jenkis地址/generic-webhook-trigger/invoke 
Jenkins日志空间不够用
df -h查看空间占用
进入日志目录
cd /var/log/jenkins/
查看文件大小
du -sh *



自己阿里云配置
数据库
用户名：root
密码 ：Jay-123456!



#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
