/*
* 20170427 JH c134-admin-list-new-tutor-requests-MP-jh updated create table query, added scripts to update ISAPPROVED column, changed default values for ISAPPROVED and TUTORSTATUS columns
*/


-- ------------------------------------------------------------------
-- To create the latest TUTOR table
-- ------------------------------------------------------------------

GO

/****** Object:  Table [CAMPUS].[TUTOR]    Script Date: 1/17/2017 12:22:55 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [CAMPUS].[TUTOR](
	[CODE] [int] IDENTITY(1,1) NOT NULL,
	[USERNAME] [varchar](100) NOT NULL,
	[PASSWORD] [varchar](100) NOT NULL,
	[FIRSTNAME] [varchar](35) NOT NULL,
	[MIDDLENAME] [varchar](35) NOT NULL,
	[LASTNAME] [varchar](35) NOT NULL,
	[GENDER] [int] NOT NULL,
	[EMAIL] [varchar](255) NOT NULL,
	[LANDPHONECOUNTRYCODE] [varchar](10) NOT NULL,
	[LANDPHONEAREACODE] [varchar](10) NOT NULL,
	[LANDPHONENUMBER] [varchar](15) NOT NULL,
	[MOBILEPHONECOUNTRYCODE] [varchar](10) NOT NULL,
	[MOBILEPHONENETWORKCODE] [varchar](10) NOT NULL,
	[MOBILEPHONENUMBER] [varchar](15) NOT NULL,
	[DESCRIPTION] [text] NOT NULL,
	[EXPERIENCE] [text] NOT NULL,
	[WEBLINK] [varchar](200) NOT NULL,
	[FACEBOOKURL] [varchar](200) NOT NULL,
	[TWITTERURL] [varchar](100) NOT NULL,
	[MYSPACEURL] [varchar](100) NOT NULL,
	[LINKEDINURL] [varchar](100) NOT NULL,
	[INSTAGRAMURL] [varchar](100) NOT NULL,
	[VIBERNUMBER] [varchar](20) NOT NULL,
	[WHATSAPPNUMBER] [varchar](20) NOT NULL,
	[ISAPPROVED] [int] NOT NULL,
	[ADDRESS1] [varchar](50) NULL,
	[ADDRESS2] [varchar](50) NULL,
	[ADDRESS3] [varchar](50) NULL,
	[TOWN] [bigint] NULL,
	[USERTYPE] [int] NOT NULL,
	[CRTON] [date] NOT NULL,
	[CRTBY] [varchar](20) NOT NULL,
	[MODON] [date] NOT NULL,
	[MODBY] [varchar](20) NOT NULL,
	[TUTORSTATUS] [int] NOT NULL,
 CONSTRAINT [PK_TUTOR] PRIMARY KEY CLUSTERED 
(
	[CODE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_USERNAME]  DEFAULT ('') FOR [USERNAME]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_PASSWORD]  DEFAULT ('') FOR [PASSWORD]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_FIRSTNAME]  DEFAULT ('') FOR [FIRSTNAME]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_MIDDLENAME]  DEFAULT ('') FOR [MIDDLENAME]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_LASTNAME]  DEFAULT ('') FOR [LASTNAME]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_GENDER]  DEFAULT ((0)) FOR [GENDER]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_EMAIL]  DEFAULT ('') FOR [EMAIL]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_LANDPHONECOUNTRYCODE]  DEFAULT ('') FOR [LANDPHONECOUNTRYCODE]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_LANDPHONEAREACODE]  DEFAULT ('') FOR [LANDPHONEAREACODE]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_LANDPHONENUMBER]  DEFAULT ('') FOR [LANDPHONENUMBER]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_MOBILEPHONECOUNTRYCODE]  DEFAULT ('') FOR [MOBILEPHONECOUNTRYCODE]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_MOBILEPHONEAREACODE]  DEFAULT ('') FOR [MOBILEPHONENETWORKCODE]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_MOBILEPHONENUMBER]  DEFAULT ('') FOR [MOBILEPHONENUMBER]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_DESCRIPTION]  DEFAULT ('') FOR [DESCRIPTION]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_EXPERIENCE]  DEFAULT ('') FOR [EXPERIENCE]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_WEBLINK]  DEFAULT ('') FOR [WEBLINK]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_FACEBOOKURL]  DEFAULT ('') FOR [FACEBOOKURL]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_TWITTERURL]  DEFAULT ('') FOR [TWITTERURL]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_MYSPACEURL]  DEFAULT ('') FOR [MYSPACEURL]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_LINKEDINURL]  DEFAULT ('') FOR [LINKEDINURL]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_INSTERGRAMURL]  DEFAULT ('') FOR [INSTAGRAMURL]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_VIBERNUMBER]  DEFAULT ('') FOR [VIBERNUMBER]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_WHATSAPPNUMBER]  DEFAULT ('') FOR [WHATSAPPNUMBER]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_ISAPPROVED]  DEFAULT ((2)) FOR [ISAPPROVED]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  DEFAULT ('') FOR [ADDRESS1]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  DEFAULT ('') FOR [ADDRESS2]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  DEFAULT ('') FOR [ADDRESS3]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_TOWN]  DEFAULT ((0)) FOR [TOWN]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_USERTYPE]  DEFAULT ((0)) FOR [USERTYPE]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_CRTON]  DEFAULT (getdate()) FOR [CRTON]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_CRTBY]  DEFAULT ('') FOR [CRTBY]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_MODON]  DEFAULT (getdate()) FOR [MODON]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_MODBY]  DEFAULT ('') FOR [MODBY]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_TUTORSTATUS]  DEFAULT ((0)) FOR [TUTORSTATUS]
GO

ALTER TABLE [CAMPUS].[TUTOR]  WITH CHECK ADD  CONSTRAINT [FK_TUTOR_TOWN] FOREIGN KEY([TOWN])
REFERENCES [CAMPUS].[TOWN] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTOR] CHECK CONSTRAINT [FK_TUTOR_TOWN]
GO

ALTER TABLE [CAMPUS].[TUTOR]  WITH CHECK ADD  CONSTRAINT [FK_TUTOR_USERTYPE] FOREIGN KEY([USERTYPE])
REFERENCES [CAMPUS].[USERTYPE] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTOR] CHECK CONSTRAINT [FK_TUTOR_USERTYPE]
GO




-- ------------------------------------------------------------------------

-- To update the tutor table from V-16 to the latest ddl execute
-- following queires
-- ------------------------------------------------------------------------

-- ------------------------------------------------------------------------
-- To drop the constraint on the column
-- ------------------------------------------------------------------------
ALTER TABLE CAMPUS.TUTOR 
DROP CONSTRAINT DF_TUTOR_ISACTIVE

-- ------------------------------------------------------------------------
-- To drop ISACTIVE column
-- ------------------------------------------------------------------------
ALTER TABLE CAMPUS.TUTOR 
DROP COLUMN ISACTIVE

-- ------------------------------------------------------------------------
-- To add TUTORSTATUS column

-- It TUTOR table contains records, not null constraint is not allowed. 
-- Then use script two
-- If not use script one
-- ------------------------------------------------------------------------
ALTER TABLE CAMPUS.TUTOR 
ADD TUTORSTATUS INT NOT NULL;

/* Script two */
ALTER TABLE CAMPUS.TUTOR 
ADD TUTORSTATUS INT NULL;


-- ------------------------------------------------------------------------
-- To add the DF_TUTOR_TUTORSTATUS constraint
-- ------------------------------------------------------------------------
USE [xeno]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_TUTORSTATUS]  DEFAULT (1) FOR [TUTORSTATUS]
GO


-- ------------------------------------------------------------------------

-- To update the tutor table column ISAPPROVED to int
-- following queires
-- ------------------------------------------------------------------------

-- ------------------------------------------------------------------------
-- To drop the constraint on the column
-- ------------------------------------------------------------------------
ALTER TABLE CAMPUS.TUTOR 
DROP CONSTRAINT DF_TUTOR_ISAPPROVED

-- ------------------------------------------------------------------------
-- To drop ISAPPROVED column
-- ------------------------------------------------------------------------
ALTER TABLE CAMPUS.TUTOR 
DROP COLUMN ISAPPROVED

-- ------------------------------------------------------------------------
-- To add ISAPPROVED column

-- It TUTOR table contains records, not null constraint is not allowed. 
-- Then use script two
-- ------------------------------------------------------------------------
/* Script one */
ALTER TABLE CAMPUS.TUTOR 
ADD ISAPPROVED INT NOT NULL;


/* Script two */
ALTER TABLE CAMPUS.TUTOR 
ADD ISAPPROVED INT NULL;


-- ------------------------------------------------------------------------
-- To add the DF_TUTOR_ISAPPROVED constraint
-- ------------------------------------------------------------------------
USE [xeno]
GO

ALTER TABLE [CAMPUS].[TUTOR] ADD  CONSTRAINT [DF_TUTOR_ISAPPROVED]  DEFAULT (2) FOR [ISAPPROVED]
GO