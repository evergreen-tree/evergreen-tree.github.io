package com.tensegrity.palojava.http.loader;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.PropertyInfo;
import com.tensegrity.palojava.loader.PropertyLoader;

public class HttpPropertyLoader extends PropertyLoader {
	private final PaloInfo paloObject;
	
	public HttpPropertyLoader(DbConnection paloConnection) {
		super(paloConnection);
		paloObject = null;
	}
	
	public HttpPropertyLoader(DbConnection paloConnection, PaloInfo paloObject) {
		super(paloConnection);
		this.paloObject = paloObject;		
	}
	
	public String[] getAllPropertyIds() {
		// TODO implement me
		return new String[0];
	}

	public PropertyInfo load(String id) {
		// TODO implement me
		return null;
	}
	
	protected void reload() {
		// TODO implement me
	}
}
