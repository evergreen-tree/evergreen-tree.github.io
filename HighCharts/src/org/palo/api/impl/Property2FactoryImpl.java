package org.palo.api.impl;

import org.palo.api.Connection;
import org.palo.api.Property2;
import org.palo.api.Property2Factory;

public class Property2FactoryImpl extends Property2Factory {
	public final Property2 newProperty(Connection con, String id, String value) {
		return createProperty(con, id, value, null, Property2.TYPE_STRING, false);
	}

	public final Property2 newProperty(Connection con, String id, String value, Property2 parent) {		
		return createProperty(con, id, value, parent, Property2.TYPE_STRING, false);
	}

	public final Property2 newProperty(Connection con, String id, String value, int type) {
		return createProperty(con, id, value, null, type, false);
	}

	public final Property2 newProperty(Connection con, String id, String value, Property2 parent,
			int type) {
		return createProperty(con, id, value, parent, type, false);
	}

	public final Property2 newReadOnlyProperty(Connection con, String id, String value) {
		return createProperty(con, id, value, null, Property2.TYPE_STRING, true);
	}

	public final Property2 newReadOnlyProperty(Connection con, String id, String value,
			Property2 parent) {
		return createProperty(con, id, value, parent, Property2.TYPE_STRING, true);
	}

	public final Property2 newReadOnlyProperty(Connection con, String id, String value, int type) {
		return createProperty(con, id, value, null, type, true);
	}

	public final Property2 newReadOnlyProperty(Connection con, String id, String value,
			Property2 parent, int type) {
		return createProperty(con, id, value, parent, type, true);
	}
	
	private final Property2 createProperty(Connection con, String id, String value, 
			Property2 parent, int type, boolean readOnly) {		
		return Property2Impl.create(con, id, value, parent, type, readOnly);
	}
}
