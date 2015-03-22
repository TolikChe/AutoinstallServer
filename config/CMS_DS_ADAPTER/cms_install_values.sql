/****************************************************
*	BASE SYS identification 		    *
****************************************************/
-- Name of system administrator user
DEFINE sysUser = SYS
-- System administrator password
DEFINE sysPswd = ihtllth
-- Type of connect for System administrator
DEFINE sysCntp = 'AS SYSDBA'

/****************************************************
*	BASE where CMS has installed		    *
****************************************************/
-- CRM database name
DEFINE crmBase = ntdb10
-- Name of CRM schema
DEFINE crmUser = CRM_DAILY
-- CRM user password
DEFINE crmPswd = CRM_DAILY

-- Application Role name
DEFINE appRole = CRM_DAILY_APP_ROLE
-- Application User name
DEFINE appUser = CRM_DAILY_APP
-- Application User Password
DEFINE appPswd = CRM_DAILY_APP

/****************************************************
*	BASE where ADAPTER has installed	    *
****************************************************/
-- Adapter database name
DEFINE adapterBase = &crmBase 
-- Adapter schema name
DEFINE adapterUser	= CRM_DAILY
-- Adapter password
DEFINE adapterPswd	= CRM_DAILY

DEFINE sourceKeyName	= BIS

-- default tablespace for ADAPTER
DEFINE defTbs = USERS
-- index tablespace for ADAPTER
DEFINE idxTbs = &defTbs

/****************************************************
*	BASE where SCR_COMMON has installed	 		   *
****************************************************/
--SCR_COMMON base
DEFINE scrCommonBase = samara
--SCR_COMMON scheme name
DEFINE scrCommonUser = bis
--SCR_COMMON user password
DEFINE scrCommonPswd = bis
-- DbLink from CMS schema To SCR_COMMON schema
DEFINE crmToCommonLink = samara.NET.BILLING.RU

/****************************************************
*	BASE where BIS has installed		    *
****************************************************/
-- BIS database name
DEFINE bisBase = samara

-- BIS schema name
DEFINE bisSchema = bis

-- BIS password
DEFINE bisPswd = bis

-- Name of DB Link from ADAPTER to BIS
DEFINE dbLinkToBis = samara.NET.BILLING.RU