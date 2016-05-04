#_d= `readlink /proc/$$/fd/255 | dirname`
trap '' SIGHUP
d=`dirname $0`
cd $d
d=`pwd`
pid=` ps aux | grep org.apache.catalina.startup.Bootstrap | grep -v grep | awk '{print $2}' `

if [  ! -n "$pid" ] ; then
JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -javaagent:/$d/_r/jrebel.jar -Xbootclasspath/p:/var//rebelboot.jar -Djava.rmi.server.hostname=192.168.1.199 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Xms512m -Xmx2048m -XX:PermSize=256M -XX:MaxPermSize=512M" ./startup.sh
fi
tail -f catalina.out
