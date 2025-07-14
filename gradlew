#!/usr/bin/env bash

export JAVA_OPTS="$JAVA_OPTS -Xmx1024m"

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
"$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
