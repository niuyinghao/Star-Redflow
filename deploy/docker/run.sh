docker run -d -v /var/lib/postgresql/9.3/main --name c_data --entrypoint bash postgres
docker run  -d -h dbServer --name dbPostgres -p 5432:5432 --volumes-from c_data postgres
docker run -d --name 'star' --link dbPostgres -p 80:8080 star 
docker logs --tail=20 -f star
