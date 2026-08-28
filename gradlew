#!/usr/bin/env bash

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls -ld "$PRG"
    link=$(expr "$PRG" : '.*->\(.*\)$')
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=$(dirname "$PRG")"/"$link"
    fi
done
SAVE="$(pwd)"
cd "$(dirname "$PRG")" >/dev/null
APP_HOME="$(pwd -P)"
cd "$SAVE" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='" -Dfile.encoding=UTF-8"'

# Use the maximum available, or set MAX_FD != unlimited.
MAX_FD="maximum"

# warn on an unusually low amount of available file descriptors.
if [ -n "$DARWIN" ] ; then
    warn()
    {
        echo "$*"
    }
else
    warn()
    {
        :   # noop
    }
fi

# Increase the maximum file descriptors if we can.
if [ "$cygwin" ] ; then
    MAX_FD_LIMIT=$((25 * 1024 - 1))
else
    MAX_FD_LIMIT="unlimited"
fi

# For Darwin, -XX:MaxFD is a noop unless the OS version is 16.1 or greater; see jless(1).
if [ "$DARWIN" ] && [ "$JAVA_VERSION" ] ; then
    warn "Setting the maximum file descriptors on Mac has no effect."
fi

if [ "$CYGWIN" ] ; then
    APP_HOME="$(cygpath --path --mixed "$APP_HOME")"
fi

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
exec "$JAVACMD" "$@"
