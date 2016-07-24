# db
docker run -d -v /var/lib/postgresql/9.3/main --name c_data --entrypoint bash postgres
# pics
docker run -d -v /opt/tomcat/webapps/ROOT/resources --name c_pic_data --entrypoint bash star
docker run  -d -h dbServer --name dbPostgres -p 5432:5432 --volumes-from c_data postgres

# star
#JO="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -javaagent:/opt/tomcat/bin/jrebel.jar -Xbootclasspath/p:/tmp/hostTmp/jrebelTmp.jar -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false" 
JO="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005  -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false" 
docker run  --name 'star' --link dbPostgres --volumes-from c_pic_data -p 80:8080 -e JAVA_OPTS="$JO" -v /tmp:/tmp/hostTmp -p 2200:22 star
docker logs --tail=20 -f star
