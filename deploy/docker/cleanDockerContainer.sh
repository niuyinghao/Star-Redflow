cat  <(docker ps -q)  <(docker ps -aq) | sort | uniq -u | xargs docker rm
