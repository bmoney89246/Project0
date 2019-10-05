create table logincredentials(
	id integer primary key,
	username varchar unique,
	password varchar
);
select * from logincredentials;

insert into logincredentials (username, password) values ('bmoney', 'password');

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