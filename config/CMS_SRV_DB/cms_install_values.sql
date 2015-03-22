-- Name of system administrator user
DEFINE sysUser = SYS
-- System administrator password
DEFINE sysPswd = ihtllth
-- Type of connect for System administrator
DEFINE sysCntp = 'AS SYSDBA'
/**
*
CRM-CMS
*/
-- CRM database name
DEFINE crmBase = ntdb10
-- Name of CRM schema
DEFINE crmUser = CRM_DAILY
-- CRM user password
DEFINE crmPswd = CRM_DAILY
-- default tablespace for CRM
DEFINE defTbs = USERS
-- index tablespace for CRM
DEFINE idxTbs = &defTbs
-- Authorization Role name
DEFINE authRole = CRM_DAILY_AUTH_ROLE
-- Application Role name
DEFINE appRole = CRM_DAILY_APP_ROLE
-- Authorization User name
DEFINE authUser = CRM_DAILY_AUTH
-- Authorization User Password
DEFINE authPswd = crmljhjuf06
-- Application User name
DEFINE appUser = CRM_DAILY_APP
-- Application User Password
DEFINE appPswd = CRM_DAILY_APP
-- Role name for CMS API
DEFINE apiRole = CRM_DAILY_API_ROLE
DEFINE cdmBase = ???
DEFINE cdmUser = ???
DEFINE cdmPswd = ???
/**
*	CRM-CMS parameters
*/
-- use time translate parameter (Y,N)
DEFINE timeTranslate = Y
