/* 20161125 PN c26-add-student-details: campus_V13.sql file tables are not compatible with issue requirements.
			Therefore anyone currently using campus_V13.sql please run this alter statements before checkout to the c26 issue branch.
*/

-- ----------------------------
-- Drop [CAMPUS].[MEDIUM] table
-- ----------------------------    
IF EXISTS (
    SELECT * FROM sysobjects WHERE id = object_id(N'[campus].[MEDIUM]') AND OBJECTPROPERTY(id, N'IsTable') = 1
)
BEGIN
    DROP TABLE [campus].[MEDIUM]
END
GO

-- ----------------------------
-- Create [CAMPUS].[MEDIUM] table
-- ----------------------------
CREATE TABLE [CAMPUS].[MEDIUM] (
	CODE INT NOT NULL IDENTITY (1,1),
	DESCRIPTION VARCHAR(255) NOT NULL CONSTRAINT DF_MEDIUM_DESCRIPTION DEFAULT ('') ,
	ISACTIVE BIT NOT NULL CONSTRAINT DF_MEDIUM_ISACTIVE DEFAULT (1),
	CRTON DATE NOT NULL CONSTRAINT DF_MEDIUM_CRTON DEFAULT (getdate()) ,
    CRTBY VARCHAR(20) NOT NULL CONSTRAINT DF_MEDIUM_CRTBY DEFAULT ('') ,
    MODON DATE NOT NULL CONSTRAINT DF_MEDIUM_MODON DEFAULT (getdate()) ,
    MODBY VARCHAR(20) NOT NULL CONSTRAINT DF_MEDIUM_MODBY DEFAULT ('') ,
	CONSTRAINT PK_MEDIUM PRIMARY KEY (CODE)
)

---------------------------------------------------------------
-----Alter Table Statements [CAMPUS].[SCHOOLEDUCATION]---------
---------------------------------------------------------------
ALTER TABLE [CAMPUS].[SCHOOLEDUCATION]
DROP CONSTRAINT DF_SCHOOLEDUCATION_INDEXNO

ALTER TABLE [CAMPUS].[SCHOOLEDUCATION]
ALTER COLUMN INDEXNO VARCHAR(20) NOT NULL 

ALTER TABLE [CAMPUS].[SCHOOLEDUCATION] ADD CONSTRAINT DF_SCHOOLEDUCATION_INDEXNO DEFAULT('') FOR INDEXNO;


ALTER TABLE [CAMPUS].[SCHOOLEDUCATION]
DROP CONSTRAINT DF_SCHOOLEDUCATION_MEDIUM

ALTER TABLE [CAMPUS].[SCHOOLEDUCATION]
DROP COLUMN MEDIUM;

ALTER TABLE [CAMPUS].[SCHOOLEDUCATION]
ADD MEDIUM INT NOT NULL

ALTER TABLE [CAMPUS].[SCHOOLEDUCATION] ADD CONSTRAINT FK_SCHOOLEDUCATION_MEDIUM FOREIGN KEY (MEDIUM) REFERENCES [CAMPUS].[MEDIUM](CODE);

---------------------------------------------------------------
-----Alter Table Statements [CAMPUS].[HIGHERDUCATION]---------
---------------------------------------------------------------
ALTER TABLE [CAMPUS].[HIGHERDUCATION]
DROP CONSTRAINT DF_HIGHERDUCATION_MEDIUM

ALTER TABLE [CAMPUS].[HIGHERDUCATION]
DROP COLUMN MEDIUM;

ALTER TABLE [CAMPUS].[HIGHERDUCATION]
ADD MEDIUM INT NOT NULL

ALTER TABLE [CAMPUS].[HIGHERDUCATION] ADD CONSTRAINT FK_HIGHERDUCATION_MEDIUM FOREIGN KEY (MEDIUM) REFERENCES [CAMPUS].[MEDIUM](CODE);

---------------------------------------------------------------
-----DML [CAMPUS].[MEDIUM] table---------
---------------------------------------------------------------

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('English'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Arabic'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Sinhala'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Russian'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('French'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Tamil'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Dutch'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Spanish'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Japanese'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('German'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Hindi'), ((1)), (getdate()), (''), (getdate()), (''));

INSERT INTO "dev-3".CAMPUS.MEDIUM
(DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES(('Other'), ((1)), (getdate()), (''), (getdate()), (''));

