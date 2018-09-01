#!/bin/bash
# *********************************************************
# * Author        : huanxi
# * Email         : 1355473748@qq.com
# * Last modified : 2018-09-01 19:52
# * Filename      : setup.sh
# * Description   : 糖果糖系统安装脚本
# * *******************************************************
mvn install:install-file -DgroupId=com.google.code.kaptcha -DartifactId=kaptcha -Dversion=2.3 -Dpackaging=jar -Dfile=jar/kaptcha-2.3.jar
mvn install -DskipTests

