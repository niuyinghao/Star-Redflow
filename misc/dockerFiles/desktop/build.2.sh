docker build -t xeyes - << __EOF__
FROM desktop
RUN apt-get install -qqy x11-apps
ENV DISPLAY :0
CMD xeyes
__EOF__
XSOCK=/tmp/.X11-unix
XAUTH=/tmp/.docker.xauth
xauth nlist :0 | sed -e 's/^..../ffff/' | xauth -f $XAUTH nmerge -
docker run -ti -v $XSOCK:$XSOCK -v $XAUTH:$XAUTH -e XAUTHORITY=$XAUTH xeyes
