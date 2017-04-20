/**
* 20170419 JH c135-public-display-tutor-profile-MP-jh  B-1-update-employment-table-ddl.sql created,
*				added scripts to alter VARIFICATIONSTATUS column as CONFIRMATIONSTATUS  
* 20170420 JH c135-public-display-tutor-profile-MP-jh changed the default value of DF_EMPLOYMENT_CONFIRMATIONSTATUS constraint
*				assuming that the application status enum class for status pending is '2', added scirpts to create a new 
*				column INITIATEDBY
*/

-- ------------------------------------------------------------------------
-- To drop the constraint on the column VARIFICATIONSTATUS
-- ------------------------------------------------------------------------
ALTER TABLE CAMPUS.EMPLOYMENT 
DROP CONSTRAINT [DF_EMPLOYMENT_VARIFICATIONSTATUS]

-- ------------------------------------------------------------------------
-- To drop column VARIFICATIONSTATUS
-- ------------------------------------------------------------------------
ALTER TABLE CAMPUS.EMPLOYMENT 
DROP COLUMN VARIFICATIONSTATUS

-- ------------------------------------------------------------------------
-- To add CONFIRMATIONSTATUS column
-- ------------------------------------------------------------------------

/** ALTER TABLE only allows columns to be added that can contain nulls, or have 
	a DEFAULT definition specified, or the column being added is an identity or 
	timestamp column, or alternatively if none of the previous conditions are 
	satisfied the table must be empty to allow addition of this column.

	Therefore if the table has data, it will not allow to insert the 
	column with a not null constratint. Then use script one to add the new
	column. Ohterwise use the second script
 */



/* Script one */
ALTER TABLE CAMPUS.CONFIRMATIONSTATUS 
ADD CONFIRMATIONSTATUS INT;


/* Script two */
ALTER TABLE CAMPUS.CONFIRMATIONSTATUS 
ADD CONFIRMATIONSTATUS INT NOT NULL;


-- ------------------------------------------------------------------------
-- To add the constraint on the column CONFIRMATIONSTATUS
-- ------------------------------------------------------------------------
ALTER TABLE [CAMPUS].[EMPLOYMENT] 
ADD  CONSTRAINT [DF_EMPLOYMENT_CONFIRMATIONSTATUS]  DEFAULT ((2)) FOR [CONFIRMATIONSTATUS]


-- ------------------------------------------------------------------------
-- To add INITIATEDBY column
-- ------------------------------------------------------------------------

/**
* use script one if the EMPLOYMENT table is empty. Else use script two
*/

/* Script one */
ALTER TABLE CAMPUS.EMPLOYMENT 
ADD INITIATEDBY INT NULL;

/* Script two  */
ALTER TABLE CAMPUS.EMPLOYMENT 
ADD INITIATEDBY INT NOT NULL;

-- ------------------------------------------------------------------------
-- To add the constraint on the column INITIATEDBY
-- ------------------------------------------------------------------------
ALTER TABLE [CAMPUS].[EMPLOYMENT] 
ADD  CONSTRAINT [DF_EMPLOYMENT_INITIATEDBY]  DEFAULT ((0)) FOR INITIATEDBY