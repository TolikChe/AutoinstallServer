set SQLPATH=%~dp0
sqlplus /nolog @remove_old_schemas.sql
exit 1