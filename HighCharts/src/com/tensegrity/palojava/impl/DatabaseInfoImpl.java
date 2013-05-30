package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.DatabaseInfo;

public class DatabaseInfoImpl implements DatabaseInfo {

	private final String id;
	private final int type;
	private String name;
	
	private int cubeCount;
	private int dimCount;
	private int status;
	private int token;

	public DatabaseInfoImpl(String id, int type) {
		this.id = id;
		this.type = type;
	}

	public final synchronized int getCubeCount() {
		return cubeCount;
	}

	public final synchronized void setCubeCount(int cubeCount) {
		this.cubeCount = cubeCount;
	}

	public final synchronized int getDimensionCount() {
		return dimCount;
	}

	public final synchronized void setDimensionCount(int dimCount) {
		this.dimCount = dimCount;
	}

	public final synchronized int getStatus() {
		return status;
	}

	public final synchronized void setStatus(int status) {
		this.status = status;
	}

	public final synchronized int getToken() {
		return token;
	}

	public final synchronized void setToken(int token) {
		this.token = token;
	}


	public final synchronized void setName(String name) {
		this.name = name;
	}

	
	public final String getId() {
		return id;
	}

	public final synchronized String getName() {
		return name;
	}

	public final int getType() {
		return type;
	}

	public boolean isSystem() {
		return type == TYPE_SYSTEM;
	}

	public boolean canBeModified() {
		return true;
	}

	public boolean canCreateChildren() {
		return true;
	}
	

}
