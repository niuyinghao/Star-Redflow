MAVEN_OPTS=" -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:5005,suspend=n,server=y -Drebel.base=C:/Users/yinghao_niu/.jrebel -Drebel.env.ide.plugin.version=6.4.1-idea-13-15 -Drebel.env.ide.version=2016.1.2 -Drebel.env.ide.product=IU -Drebel.env.ide=intellij -Drebel.notification.url=http://localhost:17434 -agentpath:C:/Users/yinghao_niu/.IntelliJIdea2016.1/config/plugins/jr-ide-idea/lib/jrebel6/lib/jrebel64.dll"  mvn tomcat7:run 2>&1>_log.log&

tail -f _log.log | txts -n java
