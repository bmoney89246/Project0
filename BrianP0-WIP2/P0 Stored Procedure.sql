create procedure deleteFakeUsernamesAndPasswords ()
language sql
as $$
	delete from logincredentials where username like 'test%';	
$$;

call deleteFakeUsernamesAndPasswords ();