
--20170221 PN CAM-48 Inserting Course Provider related images/logos details into CAMPUS.SYSTEMCONFIG table.
--20170208 PN CAM-48 incorrect column names DESCRIPTION & SYSTEMCONFIGCODE in table CAMPUS.SYSTEMCONFIG modified.


--Selection Query to check if details exists--
SELECT * FROM CAMPUS.SYSTEMCONFIG;

--Insertion queries--
INSERT INTO CAMPUS.SYSTEMCONFIG (SYSTEMCONFIGCODE,DESCRIPTION,VALUE1,VALUE2,VALUE3,SORTKEY,CRTON,CRTBY,MODON,MODBY) VALUES (
'CP_HEADER_IMAGE','The image is to display on miniweb heager.','C:\sdb\ctxdeploy\education.war\provider\logo','cp_img','0,0',1,'2017-02-21','CAM-48','2017-02-21','');

INSERT INTO CAMPUS.SYSTEMCONFIG (SYSTEMCONFIGCODE,DESCRIPTION,VALUE1,VALUE2,VALUE3,SORTKEY,CRTON,CRTBY,MODON,MODBY) VALUES (
'CP_LARGE_LOGO','The logo to be display on corse details preview. Size 134 x 75 Px.','C:\sdb\ctxdeploy\education.war\provider\logo','cp_img','134,75',1,'2017-02-21','CAM-48','2017-02-21','');

INSERT INTO CAMPUS.SYSTEMCONFIG (SYSTEMCONFIGCODE,DESCRIPTION,VALUE1,VALUE2,VALUE3,SORTKEY,CRTON,CRTBY,MODON,MODBY) VALUES (
'CP_SMALL_LOGO','The logo to be display on institute serch page. 84 x 39 Px','cp_img','cp_img','84,39',1,'2017-02-21','CAM-48','2017-02-21','');
