## Create Database and Database User (Postgres)
create role "wgs" login;

create database wgs with owner = wgs encoding = 'UTF8' tablespace = pg_default lc_collate = 'C' lc_ctype = 'C' template = 'template0' connection limit = -1;