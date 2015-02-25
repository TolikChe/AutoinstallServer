-- Language of system CMS (possible values are {RUS,ENG})
DEFINE sysLanguage = RUS
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
DEFINE appUser = XXX
-- Application User Password
DEFINE appPswd = CRM_DAILY_APP
-- Role name for CMS API
DEFINE apiRole = CRM_DAILY_API_ROLE
--SCR_COMMON base
DEFINE scrCommonBase = samara
--SCR_COMMON scheme name
DEFINE scrCommonUser = bis
--SCR_COMMON user password
DEFINE scrCommonPswd = bis
-- DbLink from CMS schema To SCR_COMMON schema
DEFINE crmToCommonLink = samara.NET.BILLING.RU
