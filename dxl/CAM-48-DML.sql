
--20170221 PN CAM-48 Inserting Course Provider related images/logos details into CAMPUS.SYSTEMCONFIG table.

--Selection Query to check if details exists--
SELECT * FROM CAMPUS.SYSTEMCONFIG;

--Insertion queries--
INSERT INTO CAMPUS.SYSTEMCONFIG
(SYSTEMCONFIGCODE, DESCRIPTION, VALUE1, VALUE2, VALUE3, SORTKEY, CRTON, CRTBY, MODON, MODBY)
VALUES(('CP_SMALL_LOGO'), 'The logo to be display on institute serch page. 84 x 39 Px', 'C:\sdb\ctxdeploy\education.war\provider\logo', '3276', '0.13', ((1)), (getdate()), ('CAM-48'), (getdate()), (''));
INSERT INTO CAMPUS.SYSTEMCONFIG
(SYSTEMCONFIGCODE, DESCRIPTION, VALUE1, VALUE2, VALUE3, SORTKEY, CRTON, CRTBY, MODON, MODBY)
VALUES(('CP_LARGE_LOGO'), 'The logo to be display on corse details preview. Size 134 x 75 Px.', 'C:\sdb\ctxdeploy\education.war\provider\logo', '10050', '0.04', ((1)), (getdate()), ('CAM-48'), (getdate()), (''));
INSERT INTO CAMPUS.SYSTEMCONFIG
(SYSTEMCONFIGCODE, DESCRIPTION, VALUE1, VALUE2, VALUE3, SORTKEY, CRTON, CRTBY, MODON, MODBY)
VALUES(('CP_HEADER_IMAGE'), 'The image is to display on miniweb heager.', 'C:\sdb\ctxdeploy\education.war\provider\logo', '', '1', ((1)), (getdate()), ('CAM-48'), (getdate()), (''));