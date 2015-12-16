CREATE ROLE "racing_simulator" LOGIN PASSWORD 'racing_simulator'
   VALID UNTIL 'infinity';
CREATE DATABASE racing_simulator
  WITH ENCODING='UTF8'
       OWNER=racing_simulator
       CONNECTION LIMIT=-1;
       
CREATE TABLE IF NOT EXISTS vehicle
(
   id bigserial NOT NULL PRIMARY KEY,
   vehicle_type character varying(255) NOT NULL,
   name character varying(255) NOT NULL, 
   weight double precision NOT NULL, 
   max_speed double precision NOT NULL, 
   acceleration double precision, 
   max_payload_weight double precision, 
   payload_weight double precision, 
   number_of_passengers bigint, 
   max_number_of_passengers bigint, 
   trailer_id bigint
);

CREATE TABLE IF NOT EXISTS racing
(
   id bigserial NOT NULL PRIMARY KEY, 
   name character varying(255) NOT NULL, 
   distance double precision NOT NULL
);

CREATE TABLE IF NOT EXISTS racer
(
   id bigserial NOT NULL PRIMARY KEY, 
   name character varying(255) NOT NULL,
   race_data TIMESTAMP NOT NULL,
   racing_id bigint NOT NULL,
   vehicle_id bigint NOT NULL
);