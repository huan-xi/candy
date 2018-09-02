#!/bin/bash
# *********************************************************
# * Author        : huanxi
# * Email         : 1355473748@qq.com
# * Last modified : 2018-09-01 20:08
# * Filename      : start.sh
# * Description   : 服务启动脚本
# * *******************************************************
echo "正在启动user-service"
nohup java -jar candy-user-service/target/candy-user-service-0.0.1-SNAPSHOT.jar & > /dev/null
sleep 50
echo "正在启动user-web"
nohup java -jar candy-user-web/target/candy-user-web-0.0.1-SNAPSHOT.jar & > /dev/null
