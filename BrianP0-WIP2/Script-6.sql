create table logincredentials(
	id integer primary key,
	username varchar unique,
	password varchar
);
select * from logincredentials;

select username, password from logincredentials where username = 'bmoney' and password = 'password';

insert into logincredentials (username, password) values ('bmoney', 'password');

CREATE SEQUENCE id_seq;
CREATE SEQUENCE id_seq2;

CREATE TABLE logincredentials (
  l_id integer NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
  username VARCHAR(255) unique,
  password VARCHAR(255)
 );

CREATE TABLE logincredentials (
  l_id serial PRIMARY KEY,
  username VARCHAR(255) unique,
  password VARCHAR(255)
 );

CREATE TABLE carlot (
  c_id serial PRIMARY KEY,
  vin VARCHAR(255) unique,
  make VARCHAR(255),
  model VARCHAR(255),
  year integer
 );

insert into offers (c_id, username, amount) values((select c_id from carlot where vin = 'car1'), 'cuser', 60000);
truncate table offers;
ALTER SEQUENCE offers_c_id_seq RESTART WITH 1;
select * from carlot;
select * from offers;

CREATE TABLE offers (
  c_id integer references carlot,
  o_id serial PRIMARY KEY,
  username VARCHAR(255),
  amount integer,
  accepted BOOLEAN
 );



CREATE TABLE acceptedoffers (
  o_id integer references offers,
  a_id serial PRIMARY KEY
 );

drop table offers;
drop table carlot;
drop table acceptedoffers;
truncate table logincredentials;

drop table logincredentials; 
drop table carlot; 
drop table offers; 

create sequence id_seq start 1;

drop sequence id_seq;

drop trigger id_insert on logincredentials;

create or replace
	function insert_into_logincredentials()
	returns trigger as $$
	begin
		if(TG_OP = 'INSERT') then
			new.id = (select nextval('id_seq'));
		end if;
		return new;
	end;
	$$ language plpgsql;

create trigger id_insert
	before insert on logincredentials
	for each row
	execute procedure insert_into_logincredentials();