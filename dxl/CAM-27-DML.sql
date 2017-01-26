--20161208 PN CAM-27 inserting profile picture details (path to store and size)


--Selection Query to check if details exists--
SELECT [CODE], [SYSTEMCONFIGCODE], [DESCRIPTION]
FROM [CAMPUS].[SYSTEMCONFIG]
WHERE [SYSTEMCONFIGCODE] IN ('USER_PIC_UPLOAD_PATH','USER_PIC_MAX_SIZE','CATEGORY_PIC_PATH');

--Insertion Queries if details not exists--
INSERT [CAMPUS].[SYSTEMCONFIG] ([SYSTEMCONFIGCODE], [DESCRIPTION], [VALUE1], [VALUE2], [VALUE3], [SORTKEY], [CRTON], [CRTBY]) VALUES (N'USER_PIC_UPLOAD_PATH', N'This path defined the place where user uploaded images will store.', N'C:\sdb\ctxdeploy\education.war\student', N'0', N'0', 0, CAST(0x213C0B00 AS Date), N'CAM_27')
INSERT [CAMPUS].[SYSTEMCONFIG] ([SYSTEMCONFIGCODE], [DESCRIPTION], [VALUE1], [VALUE2], [VALUE3], [SORTKEY], [CRTON], [CRTBY]) VALUES (N'USER_PIC_MAX_SIZE', N'The limit of the file size for the profile picture of the user.', N'2', N'0', N'0', 0, CAST(0x223C0B00 AS Date), N'CAM_27')
INSERT [CAMPUS].[SYSTEMCONFIG] ([SYSTEMCONFIGCODE], [DESCRIPTION], [VALUE1], [VALUE2], [VALUE3], [SORTKEY], [CRTON], [CRTBY]) VALUES (N'CATEGORY_PIC_PATH', N'This defines the image folder path for category images.', N'C:\sdb\ctxdeploy\education.war\general\category', 0, 0, 1, CAST(0x233C0B00 AS Date), N'CAM_27')

