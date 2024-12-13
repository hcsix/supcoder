#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/supcoder.sql ./mysql/db
cp ../sql/sc_config.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../supcoder-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy supcoder-gateway "
cp ../supcoder-gateway/target/supcoder-gateway.jar ./supcoder/gateway/jar

echo "begin copy supcoder-auth "
cp ../supcoder-auth/target/supcoder-auth.jar ./supcoder/auth/jar

echo "begin copy supcoder-visual "
cp ../supcoder-visual/supcoder-monitor/target/supcoder-visual-monitor.jar  ./supcoder/visual/monitor/jar

echo "begin copy supcoder-modules-system "
cp ../supcoder-modules/supcoder-system/target/supcoder-modules-system.jar ./supcoder/modules/system/jar

echo "begin copy supcoder-modules-file "
cp ../supcoder-modules/supcoder-file/target/supcoder-modules-file.jar ./supcoder/modules/file/jar

echo "begin copy supcoder-modules-job "
cp ../supcoder-modules/supcoder-job/target/supcoder-modules-job.jar ./supcoder/modules/job/jar

echo "begin copy supcoder-modules-gen "
cp ../supcoder-modules/supcoder-gen/target/supcoder-modules-gen.jar ./supcoder/modules/gen/jar

