export MAVEN_HOME="D:/z_disk/_dev/apache-maven-3.3.3"
export REBEL_JAR="C:/Users/yinghao_niu/.IntelliJIdea2016.1/config/plugins/jr-ide-idea/lib/jrebel6/jrebel.jar"
export REBEL_BOOT_JAR="C:/Users/yinghao_niu/AppData/Local/Temp//rebelboot.jar"
export MAVEN_OPTS="-Dmaven.home=D:/z_disk/_dev/apache-maven-3.3.3 -Dclassworlds.conf=D:/z_disk/_dev/apache-maven-3.3.3/bin/m2.conf -Dfile.encoding=UTF-8 -server  -Xdebug -Xrunjdwp:transport=dt_socket,address=5555,suspend=n,server=y -javaagent:$REBEL_JAR -Xbootclasspath/p:$REBEL_BOOT_JAR "
export TEMP=""
"$MAVEN_HOME/bin/mvn"  --offline tomcat7:run | tee $TEMP/server-star.log