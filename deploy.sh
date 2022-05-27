
#########################自动化部署脚本#########################

#定义执行jar文件名称
JAR_FILE=shop-0.0.1-SNAPSHOT.jar

cd /root/taoshop/

#获取正在运行的项目进程号
pid=`ps -ef |grep $JAR_FILE |grep -v grep |awk '{print $2}'`
echo '>>>current pid of tao shop application is '$pid

#如果存在pid则kill掉相关进程
if [ -n "$pid" ];
then
    kill -9 $pid
    echo '>>> success to kill pid : '$pid
else
    echo 'application is not running'
fi

#启动springboot项目，后台运行
java -jar $JAR_FILE --spring.profiles.active=prod &