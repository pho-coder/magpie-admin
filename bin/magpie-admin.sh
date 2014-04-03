#!/usr/bin/env bash
MAGPIE_HOME=/export/jrdw/magpie-admin
LOGS_PATH=${MAGPIE_HOME}/logs
VERSION=0.1.0
nohup java -Xmx256M -Xms128M -jar -DLOGS_PATH=${LOGS_PATH} ${MAGPIE_HOME}/target/magpie-admin-${VERSION}-SNAPSHOT-standalone.jar 2>&1 < /dev/null &
