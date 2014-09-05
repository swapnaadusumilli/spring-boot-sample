#!/usr/bin/env bash

### BEGIN INIT INFO
# Provides:          amascraperd
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: model daemon
# Description:       model daemon
### END INIT INFO

#set -o verbose

# Setup variables
DAEMON_NAME=bootpoc
DAEMON_USER=root
MAIN_CLASS=bootpoc.Main
PID=/var/run/${DAEMON_NAME}.pid
LOG_DIR=/var/log/${DAEMON_NAME}
LOG_OUT=${LOG_DIR}/${DAEMON_NAME}.out
LOG_ERR="&1"
WAIT=10
JAVA_PROPS=
JAVA_OPTS=

do_main() {

    check_root
    create_log_dir
    CLASSPATH=$(populate_classpath)

    case "$1" in
        start)
            exec_jsvc
        ;;
        stop)
            exec_jsvc -stop
        ;;
        restart)
            if [ -f "${PID}" ]; then
                exec_jsvc -stop
                exec_jsvc
            else
                echo "service not running, will do nothing"
                exit 1
            fi
        ;;
        status)
            check_status
        ;;
        *)
            echo "usage: daemon {start|stop|restart|status}" >&2
            exit 3
        ;;
    esac
}

exec_jsvc() {
    /usr/bin/jsvc \
    -cp ${CLASSPATH} \
    -user ${DAEMON_USER} \
    -outfile ${LOG_OUT} \
    -errfile ${LOG_ERR} \
    -pidfile ${PID} \
    -wait ${WAIT} \
    ${JAVA_PROPS} \
    ${JAVA_OPTS} \
    $1 ${MAIN_CLASS}
    ps -ef | grep java
}

check_status() {
    if [ ! -f "${PID}" ]; then
        exit 3
    fi
    PID_NUMBER=`cat ${PID}`
    if [ -e /proc/${PID_NUMBER} -a /proc/${PID_NUMBER}/exe -ef /usr/bin/jsvc ]; then
        echo "Process running with PID ${PID_NUMBER}"
        exit 0
    else
        exit 1
    fi
}
#Returns string including all jars in lib dir
populate_classpath() {
    local CLASSPATH=""
    local DAEMON_HOME=$(dirname $(readlink -f $0))/..
    for JARFILE in ${DAEMON_HOME}/lib/*.jar
    do
      CLASSPATH="${CLASSPATH}:${JARFILE}"
    done
    echo ${CLASSPATH}
}

check_root() {
    if [[ $EUID -ne 0 ]]; then
       echo "This script must be run as root" 1>&2
       exit 1
    fi
}

create_log_dir() {
    mkdir -p ${LOG_DIR}
    chown ${DAEMON_USER}:${DAEMON_USER} ${LOG_DIR}
}

do_main
