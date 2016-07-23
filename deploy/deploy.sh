mvn -DskipTests=true package
mv /root/p1628/target/project-1.0-SNAPSHOT.war  deploy/docker/
cd deploy/docker && {
	docker build --no-cache -t star -f Dockerfile-star . && rm project-1.0-SNAPSHOT.war  &&{
		#run 
  	(docker rm -f star  || echo ) && docker run -d --name 'star' --link dbPostgres -p 80:8080 -e JAVA_OPTS="$JO" -v /tmp:/tmp/hostTmp -p 2200:22 star
echo		docker logs --tail=20 -f star
	}
}




