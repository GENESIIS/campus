/*
* 20170226 JH c95-list-tutor-ddl-changes c95-ddl file created
*/

USE [xeno]
GO

BEGIN TRAN
-- ------------------------------------------
-- Drop [CAMPUS].[TUTORMAJOR] table
-- ------------------------------------------    
IF EXISTS (
    SELECT * FROM sysobjects WHERE id = object_id(N'[CAMPUS].[TUTORMAJOR]') AND OBJECTPROPERTY(id, N'IsTable') = 1
)
BEGIN
    DROP TABLE [CAMPUS].[TUTORMAJOR]
END
GO

-- ------------------------------------------
-- Create [CAMPUS].[TUTORMAJOR] table
-- ------------------------------------------ 

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [CAMPUS].[TUTORMAJOR](
	[CODE] [int] IDENTITY(1,1) NOT NULL,
	[TUTOR] [int] NOT NULL,
	[MAJOR] [int] NOT NULL,
	[ISACTIVE] [bit] NOT NULL,
	[CRTON] [date] NOT NULL,
	[CRTBY] [varchar](20) NOT NULL,
	[MODON] [date] NOT NULL,
	[MODBY] [varchar](20) NOT NULL,
 CONSTRAINT [PK_TUTORMAJOR] PRIMARY KEY CLUSTERED 
(
	[CODE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [CAMPUS].[TUTORMAJOR]  WITH CHECK ADD  CONSTRAINT [FK_TUTORMAJOR_TUTOR] FOREIGN KEY([TUTOR])
REFERENCES [CAMPUS].[TUTOR] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTORMAJOR]  WITH CHECK ADD  CONSTRAINT [FK_TUTORMAJOR_MAJOR] FOREIGN KEY([MAJOR])
REFERENCES [CAMPUS].[MAJOR] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTORMAJOR] ADD  CONSTRAINT [DF_TUTORMAJOR_ISACTIVE]  DEFAULT ((1)) FOR [ISACTIVE]
GO

ALTER TABLE [CAMPUS].[TUTORMAJOR] ADD  CONSTRAINT [DF_TUTORMAJOR_CRTON]  DEFAULT (getdate()) FOR [CRTON]
GO

ALTER TABLE [CAMPUS].[TUTORMAJOR] ADD  CONSTRAINT [DF_TUTORMAJOR_CRTBY]  DEFAULT ('') FOR [CRTBY]
GO

ALTER TABLE [CAMPUS].[TUTORMAJOR] ADD  CONSTRAINT [DF_TUTORMAJOR_MODON]  DEFAULT (getdate()) FOR [MODON]
GO

ALTER TABLE [CAMPUS].[TUTORMAJOR] ADD  CONSTRAINT [DF_TUTORMAJOR_MODBY]  DEFAULT ('') FOR [MODBY]
GO



-- ------------------------------------------
-- Drop [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] table
-- ------------------------------------------    
IF EXISTS (
    SELECT * FROM sysobjects WHERE id = object_id(N'[CAMPUS].[TUTORPROFESSIONALEXPERIENCE]') AND OBJECTPROPERTY(id, N'IsTable') = 1
)
BEGIN
    DROP TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE]
END
GO


-- ------------------------------------------
-- Create [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] table
-- ------------------------------------------ 
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE](
	[CODE] [int] IDENTITY(1,1) NOT NULL,
	[ORGANIZATION] [varchar](20) NOT NULL,
	[TUTOR] [int] NOT NULL,
	[INDUSTRY] [int] NOT NULL,
	[JOBCATEGORY] [int] NOT NULL,
	[DESIGNATION] [varchar](20) NOT NULL,
	[COMMENCEDON] [date] NOT NULL,
	[COMPLETIONON] [date] NOT NULL,
	[DESCRIPTION] [varchar](500) NOT NULL,
	[ISACTIVE] [bit] NOT NULL,
	[CRTON] [date] NOT NULL
) ON [PRIMARY]
SET ANSI_PADDING ON
	ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD [CRTBY] [varchar](20) NOT NULL
	ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD [MODON] [date] NOT NULL
	ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD [MODBY] [varchar](20) NOT NULL
	CONSTRAINT [PK_TUTORPROFESSIONALEXPERIENCE] PRIMARY KEY CLUSTERED 
(
	[CODE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_ORGANIZATION]  DEFAULT ('') FOR [ORGANIZATION]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_DESIGNATION]  DEFAULT ('') FOR [DESIGNATION]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_COMMENCEDON]  DEFAULT (getdate()) FOR [COMMENCEDON]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_COMPLETIONON]  DEFAULT (getdate()) FOR [COMPLETIONON]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_DESCRIPTION]  DEFAULT ('') FOR [DESCRIPTION]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_ISACTIVE]  DEFAULT ((1)) FOR [ISACTIVE]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_CRTON]  DEFAULT (getdate()) FOR [CRTON]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_CRTBY]  DEFAULT ('') FOR [CRTBY]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_MODON]  DEFAULT (getdate()) FOR [MODON]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] ADD  CONSTRAINT [DF_TUTORPROFESSIONALEXPERIENCE_MODBY]  DEFAULT ('') FOR [MODBY]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE]  WITH CHECK ADD  CONSTRAINT [FK_TUTORPROFESSIONALEXPERIENCE_INDUSTRY] FOREIGN KEY([INDUSTRY])
REFERENCES [CAMPUS].[MAJOR] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] CHECK CONSTRAINT [FK_TUTORPROFESSIONALEXPERIENCE_INDUSTRY]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE]  WITH CHECK ADD  CONSTRAINT [FK_TUTORPROFESSIONALEXPERIENCE_JOBCATEGORY] FOREIGN KEY([JOBCATEGORY])
REFERENCES [CAMPUS].[MAJOR] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] CHECK CONSTRAINT [FK_TUTORPROFESSIONALEXPERIENCE_JOBCATEGORY]
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE]  WITH CHECK ADD  CONSTRAINT [FK_TUTORPROFESSIONALEXPERIENCE_TUTOR] FOREIGN KEY([TUTOR])
REFERENCES [CAMPUS].[TUTOR] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTORPROFESSIONALEXPERIENCE] CHECK CONSTRAINT [FK_TUTORPROFESSIONALEXPERIENCE_TUTOR]
GO


-- ------------------------------------------
-- Drop [CAMPUS].[TUTORCATEGORY] table
-- ------------------------------------------    
IF EXISTS (
    SELECT * FROM sysobjects WHERE id = object_id(N'[CAMPUS].[TUTORCATEGORY]') AND OBJECTPROPERTY(id, N'IsTable') = 1
)
BEGIN
    DROP TABLE [CAMPUS].[TUTORCATEGORY]
END
GO


-- ------------------------------------------
-- Create [CAMPUS].[TUTORCATEGORY] table
-- ------------------------------------------ 

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [CAMPUS].[TUTORCATEGORY](
	[CODE] [int] IDENTITY(1,1) NOT NULL,
	[TUTOR] [int] NOT NULL,
	[CATEGORY] [int] NOT NULL,
	[ISACTIVE] [bit] NOT NULL,
	[CRTON] [date] NOT NULL,
	[CRTBY] [varchar](20) NOT NULL,
	[MODON] [date] NOT NULL,
	[MODBY] [varchar](20) NOT NULL,
 CONSTRAINT [PK_TUTORCATEGORY] PRIMARY KEY CLUSTERED 
(
	[CODE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY] ADD  CONSTRAINT [DF_TUTORCATEGORY_ISACTIVE]  DEFAULT ((1)) FOR [ISACTIVE]
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY] ADD  CONSTRAINT [DF_TUTORCATEGORY_CRTON]  DEFAULT (getdate()) FOR [CRTON]
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY] ADD  CONSTRAINT [DF_TUTORCATEGORY_CRTBY]  DEFAULT ('') FOR [CRTBY]
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY] ADD  CONSTRAINT [DF_TUTORCATEGORY_MODON]  DEFAULT (getdate()) FOR [MODON]
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY] ADD  CONSTRAINT [DF_TUTORCATEGORY_MODBY]  DEFAULT ('') FOR [MODBY]
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY]  WITH CHECK ADD  CONSTRAINT [FK_TUTORCATEGORY_TUTOR] FOREIGN KEY([TUTOR])
REFERENCES [CAMPUS].[TUTOR] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY] CHECK CONSTRAINT [FK_TUTORCATEGORY_TUTOR]
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY]  WITH CHECK ADD  CONSTRAINT [FK_TUTORCATEGORY_CATEGORY] FOREIGN KEY([CATEGORY])
REFERENCES [CAMPUS].[CATEGORY] ([CODE])
GO

ALTER TABLE [CAMPUS].[TUTORCATEGORY] CHECK CONSTRAINT [FK_TUTORCATEGORY_CATEGORY]
GO

COMMIT