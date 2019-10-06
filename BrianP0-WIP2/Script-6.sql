create table logincredentials(
	id integer primary key,
	username varchar unique,
	password varchar
);
select * from logincredentials;

select username, password from logincredentials where username = 'bmoney' and password = 'password';

insert into logincredentials (username, password) values ('bmoney', 'password');

CREATE SEQUENCE id_seq;

CREATE TABLE logincredentials (
  id integer NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
  username VARCHAR(255) unique,
  password VARCHAR(255)
 );

truncate table logincredentials;

drop table logincredentials; 

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