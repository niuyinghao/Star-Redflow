docker exec -it dbPostgres  sh -c "psql --command \"CREATE USER star WITH SUPERUSER PASSWORD 'star'\" &&    createdb -O star star "
docker exec -it dbPostgres  sh -c "psql -U star -W -h dbServer --command \"CREATE SEQUENCE register_sequence START 1 INCREMENT  BY  1 MAXVALUE 999999999999\" star"
docker exec -it dbPostgres sh -c ""
