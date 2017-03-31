//Admin tabale alter

ALTER TABLE CAMPUS.ADMIN ADD
EMAIL varchar(255) DEFAULT ('');

//Admin Login Query
SELECT CODE, USERNAME, PASSWORD, EMAIL, ISACTIVE, USERTYPE FROM CAMPUS.ADMIN  WHERE USERNAME= '' AND ISACTIVE = '1' OR EMAIL ='anuradha@genesiis.com' AND ISACTIVE = '1'; 

//Tabale modification 

//added defult value for userType table
SET IDENTITY_INSERT [CAMPUS].[USERTYPE] ON
GO
INSERT INTO [CAMPUS].[USERTYPE] (CODE, NAME, DESCRIPTION, COURSEPROVIDER, CRTON, CRTBY, MODON, MODBY, USERTYPESTRING) VALUES (-1, 'DEFAULT', '', -1, GETDATE(), '', GETDATE(), '', '')
GO
SET IDENTITY_INSERT [CAMPUS].[USERTYPE] OFF
GO

//added defult key for tutor tabale
select * from campus.tutor
SET IDENTITY_INSERT [CAMPUS].[TUTOR] ON
GO
INSERT INTO [CAMPUS].[TUTOR] ([CODE]
      ,[USERNAME]
      ,[PASSWORD]
      ,[FIRSTNAME]
      ,[MIDDLENAME]
      ,[LASTNAME]
      ,[GENDER]
      ,[EMAIL]
      ,[LANDPHONECOUNTRYCODE]
      ,[LANDPHONEAREACODE]
      ,[LANDPHONENUMBER]
      ,[MOBILEPHONECOUNTRYCODE]
      ,[MOBILEPHONENETWORKCODE]
      ,[MOBILEPHONENUMBER]
      ,[DESCRIPTION]
      ,[EXPERIENCE]
      ,[WEBLINK]
      ,[FACEBOOKURL]
      ,[TWITTERURL]
      ,[MYSPACEURL]
      ,[LINKEDINURL]
      ,[INSTAGRAMURL]
      ,[VIBERNUMBER]
      ,[WHATSAPPNUMBER]
      ,[ISAPPROVED]
	  ,[ISACTIVE]
      ,[ADDRESS1]
      ,[ADDRESS2]
      ,[ADDRESS3]
      ,[TOWN]
      ,[USERTYPE]
      ,[CRTON]
      ,[CRTBY]
      ,[MODON]
      ,[MODBY] ) VALUES (-1, '', '', '', '', '', 0, '', '', '', '', '', '', '', 'DEFAULT', '', '', '', '', '', '', '', '', '', 0, 0, '','', '', -1, -1, GETDATE(), '', GETDATE(), '')
GO
SET IDENTITY_INSERT [CAMPUS].[TUTOR] OFF
GO

//added defult key for admin table
SET IDENTITY_INSERT [CAMPUS].[ADMIN] ON
GO
INSERT INTO [CAMPUS].[ADMIN] ([CODE]
      ,[NAME]
      ,[USERNAME]
      ,[PASSWORD]
      ,[ISACTIVE]
      ,[USERTYPE]
      ,[DESCRIPTION]
      ,[CRTON]
      ,[CRTBY]
      ,[MODON]
      ,[MODBY]) VALUES (-1, 'DEFAULT', '', '', 0, -1, '', GETDATE(), '', GETDATE(), '')
GO
SET IDENTITY_INSERT [CAMPUS].[ADMIN] OFF
GO

//loging details update 

UPDATE CAMPUS.LOGINHISTORY
SET USERAGENT=?, SESSIONID=?, LOGGEDINDATE=(getdate()), LOGGEDINTIME=(CONVERT([time],getdate())), IPADDRESS=?, AUTHENTICATEDBY=?, LOGINSOURCE=?, MODON=(getdate()), MODBY=?
WHERE ADMIN=? AND ISACTIVE =? ;