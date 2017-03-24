package com.genesiis.campus.validation;

//20161228 CW c38-view-update-tutor-profile INIT ApplicationStatusBean.java to get the ApplicationStatus ENUM data to JSP

public class ApplicationStatusBean {

    public ApplicationStatus[] getValues() {
        return ApplicationStatus.values();
    }

}