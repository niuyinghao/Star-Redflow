docker run --name 'mysql' --add-host="willbe-lxc:172.17.8.101" -h 'dbServer' -d -v `pwd`/data:/var/lib/mysql/data -p 3306:3306 mysql
RebelPath=$HOME$HOME/_t/_r/
JO="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -javaagent:$HOME/_t/_r/jrebel.jar -Xbootclasspath/p:$HOME/_t/_r/rebelboot.jar -Djava.rmi.server.hostname=192.168.1.199 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false" 
 
# JO="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -javaagent:$HOME/_t/_r/jrebel.jar -Xbootclasspath/p:$HOME/_t/_r/rebelboot.jar -Djava.rmi.server.hostname=192.168.1.199 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Xms512m -Xmx2048m -XX:PermSize=256M -XX:MaxPermSize=512M"
while(true)
do
nc -w1 127.0.0.1 3306 <<E > /dev/null
E
if [ $? == 0 ] 
then 
docker run  --name 'tomcat' --add-host="willbe-lxc:172.17.8.101" -d -e JAVA_OPTS="$JO" -v "`pwd`/target:/opt/tomcat/webapps" -v /my:/my -v `pwd`/_t:/_t -p 8080:8080 -p 5005:5005 tomcat
  if [ $? != 0 ] 
  # then exit 100
  then 
	echo run tomcat failed
	break
  fi
 break
else echo wait 3306 ; sleep 5
fi
done
