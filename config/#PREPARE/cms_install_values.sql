
-- Language of system CMS (possible values are {RUS,ENG})
DEFINE sysLanguage = RUS

-- Name of system administrator user
DEFINE sysUser = SYS
-- System administrator password
DEFINE sysPswd = ihtllth
-- Type of connect for System administrator
DEFINE sysCntp = 'AS SYSDBA'
/****************************************************
*	                CRM-CMS                         *
****************************************************/
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

-- DEFINE adapterType = BIS

/****************************************************
*	BASE where SCR_COMMON has installed	 		   *
****************************************************/

-- REP_EDIT base name, TNS name for base there REP_EDIT has installed
DEFINE scrCommonBase		= samara
-- REP_EDIT schema name
DEFINE scrCommonUser		= bis
-- REP_EDIT password
DEFINE scrCommonPswd		= bis
-- DbLink from CMS schema To REP_EDIT schema
DEFINE crmToCommonLink		= samara.NET.BILLING.RU

/**
*	============================= CMS_SRV_DB SECTION ===============================
*/
-- алиас базы данных, где установлен модуль загрузки документов (CDM_LOADER)
DEFINE cdmBase = ???
-- имя схемы с CDM_LOADER
DEFINE cdmUser = ???
-- пароль пользователя cdmUser
DEFINE cdmPswd = ???

-- use time translate parameter (Y,N)
DEFINE timeTranslate = Y

/**
*	============================= DEFAULT DS_ADAPTER SECTION ============================
*/
/****************************************************
*	BASE where ADAPTER has installed	    *
****************************************************/
-- Adapter database name
DEFINE adapterBase = &crmBase 
-- Adapter schema name
DEFINE adapterUser		= &crmUser
-- Adapter password
DEFINE adapterPswd		= &crmPswd

DEFINE sourceKeyName		= BIS

/****************************************************
*	BASE where BIS has installed		    *
****************************************************/
-- BIS database name
DEFINE bisBase = SAMARA

-- BIS schema name
DEFINE bisSchema = BIS

-- BIS password
DEFINE bisPswd = BIS

-- Name of DB Link from ADAPTER to BIS
DEFINE dbLinkToBis = samara.NET.BILLING.RU


