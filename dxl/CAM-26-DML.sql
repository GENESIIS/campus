/* 20161125 PN c26-add-student-details: DML file is to insert data into [CAMPUS].[SKILL] TABLE.
*  20170108 PN CAM-28: DML updated with insert statements to insert data into [CAMPUS].[SCHOOLGRADE] TABLE.
*/

INSERT INTO [CAMPUS].[NVQ]
(NAME, DESCRIPTION, ISACTIVE, CRTON, CRTBY, MODON, MODBY)
VALUES('School Education', ('Schood education covers local and international education from grade 1 to grade 13.'), ((1)), (getdate()), ('PN'), (getdate()), ('PN'));

INSERT INTO [CAMPUS].[LEVEL]
(NAME, DESCRIPTION, ISACTIVE, NVQ, CRTON, CRTBY, MODON, MODBY)
VALUES(('School Education'), ('Schood education covers local and international education from grade 1 to grade 13.'), ((1)), 9, (getdate()), ('PN'), (getdate()), ('PN'));

INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'programming', N'skill of programming', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'accounting', N'skills of accounting', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'analysis', N'Financial management', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'logistics', N'Transport and Logistics methods', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'writing', N'Sinhala, English, Tamil , French etc', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'testing', N'Various skills of IT', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'software testing', N'Software Testing methodology', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'hardware maintenance', N'PC repair / improvements', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'design', N'Web designing and development', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'network design and management', N'Network Design and Management', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'speaking', N'Effective Speaking skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'listening', N'Effective Listening skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'estimating', N'Design and Construction Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'planning', N'Planning Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'decision-making', N'Decision-Making Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'diagnosing', N'Diagnostic Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'consulting', N'Effective Consulting Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'counseling', N'Counseling Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'communicating', N'Communicaton Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'managing people', N'Management Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'managing change', N'Management Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'problem solving', N'Problem Solving Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'research', N'Research Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'inventing', N'Inventive Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'observing', N'Observing Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'managing', N'Management Skills', 1);
INSERT [CAMPUS].[SKILL] ([NAME], [DESCRIPTION], [ISACTIVE]) VALUES (N'leadership', N'Effective Leadership Skils', 1);


INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 1'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 2'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 3'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 4'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 5'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 6'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 7'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 8'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Grade 9'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Ordinary Level (Grade 10/11)'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('Local Syllabus - Advanced Level (Grade 12/13)'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 1'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 2'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 3'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 4'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 5'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 6'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 7'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 8'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Grade 9'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Ordinary Level (Grade 10/11)'), (getdate()), ('PN'), (getdate()), ('PN'));
INSERT INTO [CAMPUS].[SCHOOLGRADE] (LEVEL, ISACTIVE, TITLE, CRTON, CRTBY, MODON, MODBY) VALUES(15, ((1)), ('International Syllabus - Advanced Level (Grade 12/13)'), (getdate()), ('PN'), (getdate()), ('PN'));

