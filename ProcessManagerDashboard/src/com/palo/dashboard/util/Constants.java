package com.palo.dashboard.util;

import java.util.ResourceBundle;

public interface Constants {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	String tomcat_location = resourceBundle.getString("tomcat.location");
	String olap_location = resourceBundle.getString("olap.location");
	String httpd_location = resourceBundle.getString("httpd.location");
	String core_location = resourceBundle.getString("core.location");
	String TASKLIST = "tasklist";
}
