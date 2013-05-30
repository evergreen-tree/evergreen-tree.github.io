package com.tensegrity.palo.xmla;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.PropertyInfo;

public interface XMLAPaloInfo extends PaloInfo {
	PropertyInfo getProperty(DbConnection con, String id);
	String [] getAllKnownPropertyIds(DbConnection con);
}
