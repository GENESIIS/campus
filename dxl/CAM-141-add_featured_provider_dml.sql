/*
* 20170206 JH c141-add-course-provider-issue-improvements INIT 
* 20170222 JH c141-add-course-provider-issue-improvements check and validate the user type code before inserting course provider data to the database in campus.add_featured_provider_main_branch
* 				and add_featured_provider_sub_branch stored procedures
*/




-- ----------------------------------------------------------------------------------
-- create  featured course provider main branch
-- ----------------------------------------------------------------------------------
/**
* created to insert data into course provider details when a main company 
* profile is created.
*/

DROP PROCEDURE campus.add_featured_provider_main_branch; 

USE [xeno-4]
GO


CREATE PROCEDURE campus.add_featured_provider_main_branch(
@prefix varchar(20),
@shortName varchar(30),
@name varchar(200),
@description text,
@generalEmail varchar(255),
@inquireyEmail varchar(255),
@dialCode varchar(20),
@areaCode varchar(20),
@phone1 varchar(20),
@phone2 varchar(20),
@faxNo varchar(20),
@networkCode varchar(20),
@mobile varchar(20),
@speciality varchar(100),
@weblink varchar(200),
@facebook varchar(200),
@twitter varchar(100),
@myspace varchar(100),
@linkedin varchar(100),
@instagram varchar(100),
@viber varchar(20),
@whatsapp varchar(20),
@expirationDate date,
@address1 varchar(50),
@address2 varchar(50),
@address3 varchar(50),
@accountType int,
@isTutorRelated bit,
@isAdminAllowed bit,
@courseProviderStatus int,
@courseProviderType int,
@crtBy varchar(20),
@accountName varchar(100),
@username varchar(100),
@password varchar(100),
@contactEmail varchar(255),
@accountDescription varchar(4000),
@accountStatus bit,
@userTypeString varchar(30),
@accountContactNumber varchar(30),
@isActiveTown bit,
@town bigint,
@townContactNumber varchar(30),
@acitve bit,
@headOffice int,
@userTypeCode int,
@id int OUTPUT
)

AS 
BEGIN

	SET NOCOUNT ON;
	
	SELECT @userTypeCode = CODE FROM [CAMPUS].[USERTYPE] WHERE USERTYPESTRING = @userTypeString
	
	IF @@ROWCOUNT = 1 

	INSERT INTO [CAMPUS].[COURSEPROVIDER] (UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO ,
	 LANDPHONE2NO ,FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK,FACEBOOKURL, TWITTERURL, MYSPACEURL ,
	 LINKEDINURL, INSTAGRAMURL ,VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE,
	 ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE,CRTON, CRTBY)
	 VALUES ( @prefix, @shortName, @name, @description , @generalEmail, @inquireyEmail, @dialCode , @areaCode, @phone1, @phone2, @faxNo, @dialCode, @networkCode, @mobile, @speciality, @weblink, 
	 @facebook, @twitter, @myspace, @linkedin, @instagram, @viber, @whatsapp, @expirationDate, @address1, @address2, @address3, @accountType, @isTutorRelated, @isAdminAllowed, @courseProviderStatus, @courseProviderType, getDate(), @crtBy )

	SET @id = SCOPE_IDENTITY()
	
	IF @id >0

	INSERT INTO [CAMPUS].[COURSEPROVIDERACCOUNT] (NAME, USERNAME, PASSWORD, EMAIL, DESCRIPTION, ISACTIVE, COURSEPROVIDER, USERTYPE ,CONTACTNUMBER, CRTON, CRTBY)
	VALUES(  @accountName, @username, @password, @contactEmail, @accountDescription, @accountStatus, @id, (@userTypeCode), @accountContactNumber, getDate(), @crtBy)

	IF @id >0
	
	INSERT INTO [CAMPUS].[COURSEPROVIDERTOWN] (ISACTIVE, COURSEPROVIDER, TOWN, CRTON, CRTBY, ADDRESS1, ADDRESS2, ADDRESS3, CONTACTNUMBER)
	VALUES(@acitve, @id, @town, getDate(), @crtBy, @address1, @address2, @address3, @townContactNumber) 
	
	SET @headOffice = SCOPE_IDENTITY()

	UPDATE [CAMPUS].[COURSEPROVIDER] 
	SET HEADOFFICETOWN = @headOffice
	WHERE CODE = @id


END

GO




-- ----------------------------------------------------------------------------------
-- create  featured course provider sub branch
-- ----------------------------------------------------------------------------------

/**
* used when creating a course provider under a main course provider
*/

DROP PROCEDURE  campus.add_featured_provider_sub_branch; 

USE [xeno-4]
GO


CREATE PROCEDURE campus.add_featured_provider_sub_branch(
@prefix varchar(20),
@shortName varchar(30),
@name varchar(200),
@description text,
@generalEmail varchar(255),
@inquireyEmail varchar(255),
@dialCode varchar(20),
@areaCode varchar(20),
@phone1 varchar(20),
@phone2 varchar(20),
@faxNo varchar(20),
@networkCode varchar(20),
@mobile varchar(20),
@speciality varchar(100),
@weblink varchar(200),
@facebook varchar(200),
@twitter varchar(100),
@myspace varchar(100),
@linkedin varchar(100),
@instagram varchar(100),
@viber varchar(20),
@whatsapp varchar(20),
@expirationDate date,
@address1 varchar(50),
@address2 varchar(50),
@address3 varchar(50),
@accountType int,
@isTutorRelated bit,
@isAdminAllowed bit,
@courseProviderStatus int,
@courseProviderType int,
@crtBy varchar(20),
@accountName varchar(100),
@username varchar(100),
@password varchar(100),
@contactEmail varchar(255),
@accountDescription varchar(4000),
@accountStatus bit,
@userTypeString varchar(30),
@accountContactNumber varchar(30),
@isActiveTown bit,
@town bigint,
@townContactNumber varchar(30),
@acitve bit,
@headOffice int,
@userTypeCode int,
@id int OUTPUT,
@principal int
)

AS 
BEGIN

	SET NOCOUNT ON;
	
	SELECT @userTypeCode = CODE FROM [CAMPUS].[USERTYPE] WHERE USERTYPESTRING = @userTypeString
	
	IF @@ROWCOUNT = 1 

	INSERT INTO [CAMPUS].[COURSEPROVIDER] (UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO ,
	 LANDPHONE2NO ,FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK,FACEBOOKURL, TWITTERURL, MYSPACEURL ,
	 LINKEDINURL, INSTAGRAMURL ,VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE,
	 ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE, PRINCIPAL, CRTON, CRTBY)
	 VALUES ( @prefix, @shortName, @name, @description , @generalEmail, @inquireyEmail, @dialCode , @areaCode, @phone1, @phone2, @faxNo, @dialCode, @networkCode, @mobile, @speciality, @weblink, 
	 @facebook, @twitter, @myspace, @linkedin, @instagram, @viber, @whatsapp, @expirationDate, @address1, @address2, @address3, @accountType, @isTutorRelated, @isAdminAllowed, @courseProviderStatus, @courseProviderType, @principal, getDate(), @crtBy )

	SET @id = SCOPE_IDENTITY()
	
	IF @id >0

	INSERT INTO [CAMPUS].[COURSEPROVIDERACCOUNT] (NAME, USERNAME, PASSWORD, EMAIL, DESCRIPTION, ISACTIVE, COURSEPROVIDER, USERTYPE ,CONTACTNUMBER, CRTON, CRTBY)
	VALUES(  @accountName, @username, @password, @contactEmail, @accountDescription, @accountStatus, @id, @userTypeCode, @accountContactNumber, getDate(), @crtBy)

	IF @id >0
		
	INSERT INTO [CAMPUS].[COURSEPROVIDERTOWN] (ISACTIVE, COURSEPROVIDER, TOWN, CRTON, CRTBY, ADDRESS1, ADDRESS2, ADDRESS3, CONTACTNUMBER)
	VALUES(@acitve, @id, @town, getDate(), @crtBy, @address1, @address2, @address3, @townContactNumber) 
	
	SET @headOffice = SCOPE_IDENTITY()

	UPDATE [CAMPUS].[COURSEPROVIDER] 
	SET HEADOFFICETOWN = @headOffice
	WHERE CODE = @id


END

GO




-- --------------------------------------------------------
-- one off course provier
-- --------------------------------------------------------



DROP PROCEDURE campus.add_one_off_provider; 

USE [xeno-4]
GO


CREATE PROCEDURE campus.add_one_off_provider(
@prefix varchar(20),
@shortName varchar(30),
@name varchar(200),
@description text,
@generalEmail varchar(255),
@inquireyEmail varchar(255),
@dialCode varchar(20),
@areaCode varchar(20),
@phone1 varchar(20),
@phone2 varchar(20),
@faxNo varchar(20),
@networkCode varchar(20),
@mobile varchar(20),
@speciality varchar(100),
@weblink varchar(200),
@facebook varchar(200),
@twitter varchar(100),
@myspace varchar(100),
@linkedin varchar(100),
@instagram varchar(100),
@viber varchar(20),
@whatsapp varchar(20),
@expirationDate date,
@address1 varchar(50),
@address2 varchar(50),
@address3 varchar(50),
@accountType int,
@isTutorRelated bit,
@isAdminAllowed bit,
@courseProviderStatus int,
@courseProviderType int,
@crtBy varchar(20),
@isActiveTown bit,
@town bigint,
@townContactNumber varchar(30),
@acitve bit,
@headOffice int,
@id int OUTPUT
)


AS 
BEGIN

	SET NOCOUNT ON;

	INSERT INTO [CAMPUS].[COURSEPROVIDER] (UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO ,
	 LANDPHONE2NO ,FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK,FACEBOOKURL, TWITTERURL, MYSPACEURL ,
	 LINKEDINURL, INSTAGRAMURL ,VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE,
	 ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE,CRTON, CRTBY)
	 VALUES ( @prefix, @shortName, @name, @description , @generalEmail, @inquireyEmail, @dialCode , @areaCode, @phone1, @phone2, @faxNo, @dialCode, @networkCode, @mobile, @speciality, @weblink, 
	 @facebook, @twitter, @myspace, @linkedin, @instagram, @viber, @whatsapp, @expirationDate, @address1, @address2, @address3, @accountType, @isTutorRelated, @isAdminAllowed, @courseProviderStatus, @courseProviderType, getDate(), @crtBy )

	SET @id = SCOPE_IDENTITY()
	
	IF @id >0

	INSERT INTO [CAMPUS].[COURSEPROVIDERTOWN] (ISACTIVE, COURSEPROVIDER, TOWN, CRTON, CRTBY, ADDRESS1, ADDRESS2, ADDRESS3, CONTACTNUMBER)
	VALUES(@acitve, @id, @town, getDate(), @crtBy, @address1, @address2, @address3, @townContactNumber) 
	
	SET @headOffice = SCOPE_IDENTITY()

	UPDATE [CAMPUS].[COURSEPROVIDER] 
	SET HEADOFFICETOWN = @headOffice
	WHERE CODE = @id


END

GO
