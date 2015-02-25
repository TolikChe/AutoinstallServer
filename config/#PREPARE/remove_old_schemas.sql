@.\cms_install_values.sql

connect &&sysUser/&&sysPswd@&&crmBase &&sysCntp

spool remove_old_schemas.log
set verify off
set echo off
set serveroutput on size 1000000
set linesize 1500

declare 

   nCount number;

   PROCEDURE safe_execute (for_exec IN VARCHAR2)
   AS
   BEGIN
      EXECUTE IMMEDIATE for_exec;
      DBMS_OUTPUT.put_line ('(OK)     : ' || for_exec);
   EXCEPTION
      WHEN OTHERS THEN
         DBMS_OUTPUT.put_line ('(ERROR)    : ' || for_exec || '   ' ||  SQLERRM);
   END safe_execute;
    
    

begin
    dbms_output.put_line (CHR(13) || CHR(10));
    dbms_output.put_line ('## Prepare DB for installation new schemas ##');
    dbms_output.put_line (CHR(13) || CHR(10));
-------------------------------------------------------------------------------
--       Удаляем существующие подключения
-------------------------------------------------------------------------------
    dbms_output.put_line ('Killing sessions to schema "&&crmUser" ... ');
	for x in (select SID, SERIAL#, osuser, machine from v$session where username='&&crmUser')
	loop
        dbms_output.put_line ('sid: ' || x.SID || ', osuser: ' || x.osuser);
		safe_execute( 'alter system kill session ''' || x.SID ||','|| x.SERIAL# || '''');
	end loop;
    --
    dbms_output.put_line(CHR(13) || CHR(10));
    --
    dbms_output.put_line ('Killing sessions to schema "&&appUser" ... ');
    for x in (select SID, SERIAL#, osuser, machine from v$session where username='&&appUser')
    loop
        dbms_output.put_line ('sid: ' || x.SID || ', osuser: ' || x.osuser);
        safe_execute( 'alter system kill session ''' || x.SID ||','|| x.SERIAL# || '''');
    end loop;
    --
    dbms_output.put_line(CHR(13) || CHR(10));
    --
    dbms_output.put_line ('Killing sessions to schema "&&authUser" ... ');
    for x in (select SID, SERIAL#, osuser, machine from v$session where username='&&authUser')
    loop
        dbms_output.put_line ('sid: ' || x.SID || ', osuser: ' || x.osuser);
        safe_execute( 'alter system kill session ''' || x.SID ||','|| x.SERIAL# || '''' );
    end loop;
    
-------------------------------------------------------------------------------
--       Удаляем существующие схемы 
-------------------------------------------------------------------------------
    --
    dbms_output.put_line(CHR(13) || CHR(10));    
    dbms_output.put_line('Drop users ...');
    --
    safe_execute('DROP USER &&crmUser CASCADE');
    safe_execute('DROP USER &&authUser CASCADE');
    safe_execute('DROP USER &&appUser CASCADE');
    
-------------------------------------------------------------------------------
--       Удаляем существующие роли 
-------------------------------------------------------------------------------
    --
    dbms_output.put_line(CHR(13) || CHR(10));
    dbms_output.put_line('Drop roles ...');
    --    
    safe_execute('DROP ROLE &&appRole');
    safe_execute('DROP ROLE &&authRole');
    safe_execute('DROP ROLE &&apiRole');
    
---------------------------------------------------------------------------------
--         Проверяем что все схемы и роли удалились успешно 
---------------------------------------------------------------------------------    
    
    dbms_output.put_line(CHR(13) || CHR(10));
    dbms_output.put_line('Result:');
    select count(*) into nCount from dba_users where username in ( '&&crmUser', '&&authUser', '&&appUser' );
    if nCount != 0 then 
        dbms_output.put_line('Users deleted FAIL!!!');
    else        
        dbms_output.put_line('Users deleted SUCCESSFULL');
    end if; 
    
    select count(*) into nCount from dba_roles where role in ( '&&appRole', '&&authRole', '&&apiRole' );
    if nCount != 0 then 
        dbms_output.put_line('Roles deleted FAIL!!!');
    else
        dbms_output.put_line('Roles deleted SUCCESSFULL');
    end if;

end;
/

disconnect
spool off
exit 1
