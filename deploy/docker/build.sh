docker build -t env -f Dockerfile-env .
docker build -t jdk -f Dockerfile-jdk .
docker build -t tomcat -f Dockerfile-tomcat .
docker build -t postgres -f Dockerfile-postgres .
docker build -t data -f Dockerfile-data .
docker build --no-cache -t star -f Dockerfile-star .
