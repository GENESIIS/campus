/**
 * 20161205 HF CAM-10 inserting campus inquiry emails 'to address' to systemconfig table
 **/



 --Inserting to email which is dedicated for enquiries--
 INSERT INTO [CAMPUS].[SYSTEMCONFIG]
           ([SYSTEMCONFIGCODE],[DESCRIPTION],[VALUE1],[VALUE2],[VALUE3]
           ,[SORTKEY],[CRTON],[CRTBY])
     VALUES
           ('ENQUIRY_EMAIL_TO', 'the email which is dedicated for enquiries','enquiry@campus.lk',  '','',3,getdate(),'CAM-10')
GO


--Inserting to email which is dedicated for super admin in any case of refering--
INSERT INTO [CAMPUS].[SYSTEMCONFIG]
           ([SYSTEMCONFIGCODE],[DESCRIPTION],[VALUE1],[VALUE2],[VALUE3]
           ,[SORTKEY],[CRTON],[CRTBY])
     VALUES
           ('ENQUIRY_EMIL_ADMIN',   'the email which is dedicated for super admin in any case of refering','enquiry@campus.lk','','',5,getdate(),'CAM-10')
GO

