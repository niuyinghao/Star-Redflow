set MAVEN_HOME = D:/soft/_tools/_dev/apache-maven-3.3.3
set REBEL_JAR = D:/soft/_tools/_dev/ideaConfigDir/.IntelliJIdea15/config/plugins/jr-ide-idea/lib/jrebel6/jrebel.jar
set REBEL_BOOT_JAR = C:/Users/NIUYIN~1/AppData/Local/Temp//rebelboot.jar
set MAVEN_OPTS = -Dmaven.home=D:/z_disk/_dev/apache-maven-3.3.3 -Dclassworlds.conf=D:/z_disk/_dev/apache-maven-3.3.3/bin/m2.conf -Dfile.encoding=UTF-8 -server  -Xdebug -Xrunjdwp:transport=dt_socket,address=5555,suspend=n,server=y -javaagent:$REBEL_JAR -Xbootclasspath/p:$REBEL_BOOT_JAR

"%MAVEN_HOME%/bin/mvn"  --offline tomcat7:run 2>&1 | tee %temp%/server-star-out.log