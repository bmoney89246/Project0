--Question 2.1.1
SELECT * FROM Employee;

--Question 2.1.2
SELECT * FROM employee where lastname = 'King';

--Question 2.1.3
select * from employee where firstname = 'Andrew' and reportsto isnull;

--Question 2.2.1
select * from Album order by title DESC;

--Question 2.2.2
select firstname from Customer order by city ASC;

--Question 2.3.1
select * from genre;
insert into Genre values (26, 'Some random genre');
insert into Genre values (27, 'Some random genre2');

--Question 2.3.2
select * from Employee;
insert into Employee (employeeid, lastname) values (9, 'Money');
insert into Employee (employeeid, lastname) values (10, 'Money2');

--Question 2.3.3
select * from Customer;
insert into Customer (customerid, lastname, firstname, email) values (60, 'Money', 'Brian', 'bmoney');
insert into Customer (customerid, lastname, firstname, email) values (61, 'Money2', 'Brian', 'bmoney');

--Question 2.4.1
update Customer set firstname = 'Robert' where customerid = 32;
update Customer set lastname = 'Walker' where customerid = 32;

--Question 2.4.2
select * from Artist;
update Artist set name = 'CCR' where name = 'Creedence Clearwater Revival';

--Question 2.5.1
select * from Invoice;
select * from Invoice where billingaddress like 'T%';

--Question 2.6.1
select * from Invoice where total between 15 and 20;

--Question 2.6.2
select * from employee where hiredate between '2003-6-1' and '2004-3-1';

--Question 2.7.1
select * from invoice;
alter table invoice drop constraint fk_invoicecustomerid;
delete from customer where firstname = 'Robert' and lastname = 'Walter';
	--,add constraint fk_invoicecustomerid
	--foreign key (customerid) references customer(customerid) on delete cascade;

--Question 2.7.2
--alter table invoiceline
	--drop constraint fk_invoicelineinvoiceid,
	--add constraint fk_invoicelineinvoiceid
	--foreign key (invoiceid) references invoice(invoiceid) on delete cascade;

--Question 2.7.3
delete from customer
where firstname = 'Robert' and lastname = 'Walter';

--Question 3.1.1
create or replace function currentTime()
returns text as $$
begin
    return current_time;
end;
$$ language plpgsql;

select currentTime();

--Question 3.1.2
create or replace function mediatypeLength(idinput int)
returns int as $$
begin
	return (select length(name) from mediatype where mediatypeid = idinput);
 end;
$$ language plpgsql;

select mediatypeLength(1);

--Question 3.2.1
create or replace function averageTotal()
returns float as $$
begin
	return avg(total) from invoice;
 end;
$$ language plpgsql;

select averageTotal();

--Question 3.2.2
create or replace function mostExpensiveTrack()
returns text as $$
begin
	return (select name from track where unitprice = (select max(unitprice) from track));
 end;
$$ language plpgsql;

select mostExpensiveTrack();

--Question 3.3.1
create or replace function averageInvoicePrice()
returns float as $$
begin
	return (select avg(unitprice) from invoiceline);
 end;
$$ language plpgsql;

select averageInvoicePrice();

--Question 3.4.1
create or replace
	function old_employees()
	returns text[][] as $$
	begin
		return (select array(select array [firstname, lastname] from employee where birthdate > '1968-1-1'));
	end
	$$ language plpgsql;

select old_employeees();

--Question 4.1.1
create procedure selectAllEmployeeNames ()
language sql
as $$
select firstname, lastname from employee; 
$$;

call selectAllEmployeeNames ();

--Question 4.2.1
Create or replace procedure updatePersonalInfo (newEmail varchar(100), oldEmail varchar(100), id integer)
language sql
as $$
update employee set email = newEmail, employeeid = id where email = oldEmail; 
$$;

call updatePersonalInfo('ssoqp', 'robert@chinookcorp.com', 11);

--Quesiton 4.2.2
Create or replace procedure selectManagersOfEmployees (newEmployeeId integer)
language sql
as $$
returns select m.firstname, m.lastname from employee e inner join employee m on e.employeeid = m.reportsto;
$$;

call selectManagersOfEmployees(5);

--Question 4.3.1
CREATE OR REPLACE function name_company () 
   RETURNS TABLE (
      first_name varchar(40),
      last_name varchar(20),
      c_company varchar(80)
) 
AS $$
BEGIN
   RETURN QUERY SELECT
      firstname,
      lastname,
      company
   FROM
      customer;
END; $$ 
LANGUAGE 'plpgsql';

select * from name_company();

--Question 5.1.1
create or replace function deleteInvoice(invoice_id_input int)
returns void as $$
begin
	delete from invoice where invoiceid = invoice_id_input;
commit;
end;
$$ language plpgsql;

alter table invoiceline
drop constraint fk_invoicelineinvoiceid;

alter table invoiceline
add constraint fk_invoicelineinvoiceid
foreign key (invoiceid) references invoice (invoiceid) on delete cascade on update cascade;

select deleteInvoice(407);

--Question 5.1.2
create or replace procedure insertcustomer()
language plpgsql
as $$ 
begin
	insert into customer values
	(998, 'Ricky', 'Wang', 'Revature', '12706 Bruce B Downs Blvd', 'Tampa', 'Florida', 'USA', '33061', '911', '911', 'random@gmail.com', 1);
commit;
end
$$;

call insertcustomer();

--Question 6.1.1
create or replace function do_nothing()
returns trigger as $$
begin
end;
$$ language plpgsql;

create trigger employee_insert
after insert on employee for each row
execute procedure do_nothing();

--Question 6.1.2
create trigger album_insert
after update on album for each row
execute procedure do_nothing();

--Question 6.1.3
create trigger delete_customer
after delete on customer for each row
execute procedure do_nothing();

--Question 7.1.1
SELECT firstname, invoiceid FROM customer 
INNER JOIN invoice on customer.customerid = invoice.invoiceid;

--Question 7.2.1
select customer.customerid, firstname, lastname, invoiceid, total from customer
full outer join invoice on customer.customerid = invoice.invoiceid;

--Question 7.3.1
select name, title from artist right join album on artist.artistid = album.artistid;

--Question 7.4.1
SELECT * FROM album CROSS JOIN artist order by name asc;

--Question 7.5.1
SELECT * FROM employee e1 INNER JOIN employee e2 ON e1.reportsto = e2.reportsto;








