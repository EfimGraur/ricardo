package com.ricardo.pmtool.testdata;

import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.data.UserData;


public class TestData {
    //user
    public static final Long TEST_ID = 1L;
    public static final String TEST_EMAIL = "dummy_email";
    public static final String TEST_FIRST_NAME = "dummy_first_name";
    public static final String TEST_LAST_NAME = "dummy_last_name";
    public static final String TEST_USERNAME = "dummy_username";
    public static final String TEST_ADMIN_ROLE = "ADMIN";

    public static UserData TEST_USER1_DTO = new UserData(TEST_ID, TEST_EMAIL, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USERNAME, TEST_ADMIN_ROLE, null);
    public static UserData TEST_USER2_DTO = new UserData(TEST_ID + 1,
            TEST_EMAIL + 2,
            TEST_FIRST_NAME + 2,
            TEST_LAST_NAME + 2,
            TEST_USERNAME + 2,
            TEST_ADMIN_ROLE,
            null);

    //project
    public static final String TEST_CODE = "dummy_code";
    public static final String TEST_PROJECT_NAME = "dummy_project_name";
    public static final String TEST_ASSIGNEE = "dummy_assignee";

    public static ProjectData TEST_PROJECT1_DTO = new ProjectData(TEST_ID, TEST_CODE, TEST_PROJECT_NAME, TEST_ASSIGNEE);
    public static ProjectData TEST_PROJECT2_DTO = new ProjectData(TEST_ID + 1, TEST_CODE + 2, TEST_PROJECT_NAME + 2, TEST_ASSIGNEE + 2);


    //task
    public static final String TEST_DESCRIPTION = "dummy_description";
    public static final String TEST_PM = "dummy_pm";
    public static final int TEST_PROGRESS = 12;
    public static final String TEST_STATUS = "dummy_status";

    public static TaskData TEST_TASK1_DTO = new TaskData(TEST_ID, TEST_DESCRIPTION, TEST_PM, TEST_PROGRESS, TEST_STATUS, null, TEST_CODE, TEST_ASSIGNEE);
    public static TaskData TEST_TASK2_DTO = new TaskData(TEST_ID + 1, TEST_DESCRIPTION + 2, TEST_PM, TEST_PROGRESS, TEST_STATUS, null, TEST_CODE + 2, TEST_ASSIGNEE + 2);
}
