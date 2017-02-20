-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Dumani DJ
-- Create date: 20170115
-- Description:	Return course provider codes
-- =============================================
CREATE PROCEDURE campus.sp_getcourseprovider 
	-- Add the parameters for the stored procedure here
	@inPutString varchar(200)
AS
BEGIN
	DECLARE @ResultTable TABLE (CPCODE int);
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	BEGIN
	INSERT INTO @ResultTable
	SELECT COURSEPROVIDER  FROM CAMPUS.PROGRAMME WHERE 
	NAME LIKE  @inPutString
	OR DESCRIPTION LIKE  @inPutString 
	OR EMAIL LIKE  @inPutString
	OR ENTRYREQUIREMENTS LIKE  @inPutString

	INSERT INTO @ResultTable
	SELECT CODE  FROM CAMPUS.COURSEPROVIDER WHERE
	UNIQUEPREFIX LIKE @inPutString
	OR SHORTNAME LIKE @inPutString
	OR NAME  LIKE @inPutString
	OR DESCRIPTION LIKE @inPutString 
	OR SPECIALITY LIKE @inPutString
	OR GENERALEMAIL LIKE @inPutString
	OR WEBLINK LIKE @inPutString
	END

    -- Select statements for procedure here
	SELECT  DISTINCT CPCODE FROM @ResultTable 
  
END
GO