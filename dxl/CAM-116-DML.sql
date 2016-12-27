/**
20161227 PN CAM-112: update query with description with 716 characters.
**/
UPDATE [CAMPUS].[CATEGORY]
   SET [DESCRIPTION] = 'Education in Sri Lanka has a long history that dates back two millennia. The Constitution of Sri Lanka provides for education as a fundamental right. Sri Lanka''s population had an adult literacy rate of 92.63% in 2015, which is above average by world and regional standards.[note 1] Education plays a major part in the life and culture of the country and dates back to 543 BC. Sri Lanka''s modern educational system was brought about by its integration into the British Empire in the 19th century. Education currently falls under the control of both the Central Government and the Provincial Councils, with some responsibilities lying with the Central Government and the Provincial Council having autonomy for others.'
 WHERE [CODE]=1;
GO

/**
20161227 PN CAM-112: select query to check if the data has been updated.
**/
SELECT [CODE]
      ,[NAME]
      ,[DESCRIPTION]
      ,[IMAGE]
      ,[CATEGORYSTRING]
      ,[ISACTIVE]
      ,[CRTON]
      ,[CRTBY]
      ,[MODON]
      ,[MODBY]
  FROM [CAMPUS].[CATEGORY]
GO

