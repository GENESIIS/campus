/*
* 20170417 JH c141-ui-integration-for-add-course-provider dml file CAM-141-ddl.sql created to modify required tables
*/

-- ----------------------------------------------------------------------------------
-- add drop foreign key FK_COURSEPROVIDER_COURSEPROVIDERTOWN 
-- ----------------------------------------------------------------------------------
ALTER TABLE [CAMPUS].[COURSEPROVIDER] DROP CONSTRAINT [FK_COURSEPROVIDER_COURSEPROVIDERTOWN]


-- ----------------------------------------------------------------------------------
-- add address lines to the COURSEPROVIDERTOWN table
-- ----------------------------------------------------------------------------------

ALTER TABLE [CAMPUS].[COURSEPROVIDERTOWN] ADD 
[ADDRESS1] [varchar](50) NULL;
ALTER TABLE [CAMPUS].[COURSEPROVIDERTOWN] ADD 
[ADDRESS2] [varchar](50) NULL;
ALTER TABLE [CAMPUS].[COURSEPROVIDERTOWN] ADD 
[ADDRESS3] [varchar](50) NULL;


ALTER TABLE [CAMPUS].[COURSEPROVIDERTOWN] ADD 
CONSTRAINT [DF_COURSEPROVIDERTOWN_ADDRESS1]  DEFAULT ('') FOR [ADDRESS1];
ALTER TABLE [CAMPUS].[COURSEPROVIDERTOWN] ADD 
CONSTRAINT [DF_COURSEPROVIDERTOWN_ADDRESS2]  DEFAULT ('') FOR [ADDRESS2];
ALTER TABLE [CAMPUS].[COURSEPROVIDERTOWN] ADD 
CONSTRAINT [DF_COURSEPROVIDERTOWN_ADDRESS3]  DEFAULT ('') FOR [ADDRESS3];


-- ----------------------------------------------------------------------------------
-- add removed COURSEPROVDER_COURSEPROVIDERTOWN foreign key
-- ----------------------------------------------------------------------------------
ALTER TABLE [CAMPUS].[COURSEPROVIDER]  WITH NOCHECK ADD  CONSTRAINT [FK_COURSEPROVIDER_COURSEPROVIDERTOWN] FOREIGN KEY([HEADOFFICETOWN])
REFERENCES [CAMPUS].[COURSEPROVIDERTOWN] ([CODE])
GO

ALTER TABLE [CAMPUS].[COURSEPROVIDER] CHECK CONSTRAINT [FK_COURSEPROVIDER_COURSEPROVIDERTOWN]
GO

