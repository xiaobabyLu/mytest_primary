#!/bin/bash
app="mytest-service"
#project config
pidfile=app.pid
WK_LOG="log/"
JAR_NAME="mytest-service.jar"
CONFIG_HOME="conf/"

## function
function start() {
    # 创建日志目录
    mkdir -p var &>/dev/null
    # 以后台方式 启动程序
    nohup java -Xms2048m -Xmx2048m -Xss256k -XX:+UseConcMarkSweepGC\
           -XX:CMSInitiatingOccupancyFraction=70\
           -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=2\
           -XX:PretenureSizeThreshold=1048576\
           -Dlog4j.home=$WK_LOG -Duser.timezone=GMT+8\
           -Dspring.config.location=$CONFIG_HOME\
           -jar $JAR_NAME >nohup.out 2>&1 &
    # 记录服务pid
    echo $! > $pidfile
    echo "$app start ok, pid=$!"
    # 启动成功, 退出码为 0
    exit 0
}

function stop() {
    # 循环stop服务, 直至60s超时
    for (( i = 0; i < 60; i++ )); do
        # 停止服务
        kill `get_pid` &>/dev/null
        check_pid
        local running=$?
        if [ $running -eq 0 ];then
            # stop服务成功, 返回码为 0
            echo "stoped"
            exit 0
        fi
        # 服务未停止, 继续循环
        sleep 1
    done
    # stop服务失败, 返回码为 非0
    echo "stop timeout(60s)"
    exit 1
}

## internals
function get_pid() {
    if [ -f $pidfile ];then
        cat $pidfile
    fi
}
function check_pid() {
    pid=`get_pid`
    if [ "x_" != "x_$pid" ]; then
        running=`ps -p $pid|grep -v "PID TTY" |wc -l`
        return $running
    fi
    return 0
}

#############################################
## main
## 非托管方式, 启动服务
## control.sh脚本, 必须实现start和stop两个方法
#############################################
action=$1
case $action in
    "start" )
        # 启动服务
        start
        ;;
    "stop" )
        # 停止服务
        stop
        ;;
    * )
        echo "unknown command"
        exit 1
        ;;
esac