docker build -t env -f Dockerfile-env .
docker build -t jdk-vc -f Dockerfile-jdk-vc .
docker build -t tomcat -f Dockerfile-tomcat .
docker build -t mysql -f Dockerfile-mysql  .
docker build -t maven -f Dockerfile-maven  .
docker build -t dev -f Dockerfile-addition  .
docker build -t dev -f Dockerfile-jrebel .
