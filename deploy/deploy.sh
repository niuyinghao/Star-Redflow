cd ..
mvn -DskipTests=true package
mv /root/p1628/target/project-1.0-SNAPSHOT.war  deploy/docker/
cd deploy/docker && {
	docker build --no-cache -t star -f Dockerfile-star . && rm project-1.0-SNAPSHOT.war 
}
