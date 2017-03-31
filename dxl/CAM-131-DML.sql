--20170331 DN CAM-131 admin-manage-banner-upload-banner-image (path to store and size)

--Selection Query to check if details exists--
SELECT  * 
FROM [CAMPUS].[SYSTEMCONFIG]
WHERE SYSTEMCONFIGCODE  
IN ('BANNER_IMAGE_ABSOLUTE_PATH','BANNER_IMAGE_WAR_PATH','BANNER_IMAGE_TEMPORARY_PATH','BANNER_IMAGE_SIZE' );


--Insertion Queries if details not exists--
INSERT INTO [CAMPUS].[SYSTEMCONFIG] ([SYSTEMCONFIGCODE], [DESCRIPTION], [VALUE1], [SORTKEY], [CRTON], [CRTBY]) 
VALUES ('BANNER_IMAGE_ABSOLUTE_PATH','this path is the physical storage path','C:/sdb/ctxdeploy/education.war/banner',1,getDate(),'CAM_131');

INSERT INTO [CAMPUS].[SYSTEMCONFIG] ([SYSTEMCONFIGCODE], [DESCRIPTION], [VALUE1], [SORTKEY], [CRTON], [CRTBY]) 
VALUES ('BANNER_IMAGE_WAR_PATH','this path is the web storage path','education/banner',1,getDate(),'CAM_131');

INSERT INTO [CAMPUS].[SYSTEMCONFIG] ([SYSTEMCONFIGCODE], [DESCRIPTION], [VALUE1], [SORTKEY], [CRTON], [CRTBY]) 
VALUES ('BANNER_IMAGE_TEMPORARY_PATH','The location where the banner image is stored temporary before it is moved to location given with BANNER_IMAGE_ABSOLUTE_PATH in the physical disk',
'C:/sdb/ctxdeploy/education.war/banner/tempdeleteable',1,getDate(),'CAM_131');

INSERT INTO [CAMPUS].[SYSTEMCONFIG] ([SYSTEMCONFIGCODE], [DESCRIPTION], [VALUE1], [SORTKEY], [CRTON], [CRTBY]) 
VALUES ('BANNER_IMAGE_SIZE','The maximum allowable image size for the banner to be advertized','3',1,getDate(),'CAM_131');