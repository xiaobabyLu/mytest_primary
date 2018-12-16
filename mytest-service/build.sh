#!/usr/bin/env bash

set -x

PROFILE="prod"
if [[ $# -eq 1 ]]; then
    PROFILE=$1
fi
SCRIPT="${BASH_SOURCE-$0}"
SCRIPT_DIR="$(dirname $SCRIPT)"
SCRIPT_DIR="$(cd $SCRIPT_DIR; pwd)"
PARENT_DIR="$SCRIPT_DIR/../"
cd $PARENT_DIR

#build
echo "Building Started!"

mvn clean package -P${PROFILE}  -Dthrift=thrift -Dmaven.test.skip=true;

TARGET_DIR="$SCRIPT_DIR/target"
OUTPUT_DIR="$SCRIPT_DIR/output"

mkdir -p $OUTPUT_DIR; rm -rf $OUTPUT_DIR/*;
cp -r $TARGET_DIR/mytest-service-package/* $OUTPUT_DIR/;

if [ ! "$(ls -v $OUTPUT_DIR)" ] ; then
   echo "Building Failed!"; exit 1
fi

echo "Building Finished!"
exit 0