package com.tensegrity.palojava.impl;

import java.math.BigInteger;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.DatabaseInfo;

public class CubeInfoImpl implements CubeInfo {

	
	private final String id;
	private final int type;
	private final DatabaseInfo database;
	private final String[] dimensions;  //dimension ids...
	
	private String name;
	private	int dimCount;
//	private int cellCount;
//	private int filledCellCount;
	private BigInteger cellCount;
	private BigInteger filledCellCount;
	private int status;
	private int token;
 
	
	public CubeInfoImpl(DatabaseInfo database, String id, 
			int type, String[] dimensions) {
		this.id = id;
		this.type = type;
		this.database = database;
		this.dimensions = dimensions;
	}

	public final String getId() {
		return id;
	}

	public final String getName() {
		return name;
	}

	public final int getType() {
		return type;
	}
	
	public final DatabaseInfo getDatabase() {
		return database;
	}

	public final String[] getDimensions() {
		return dimensions;
	}

	public final synchronized void setCellCount(BigInteger cellCount) {
		this.cellCount = cellCount;
	}

	public final synchronized void setDimensionCount(int dimCount) {
		this.dimCount = dimCount;
	}

	public final synchronized void setFilledCellCount(BigInteger filledCellCount) {
		this.filledCellCount = filledCellCount;
	}

	public final synchronized void setName(String name) {
		this.name = name;
	}
	
	public final synchronized void setStatus(int status) {
		this.status = status;
	}

	public final synchronized void setToken(int token) {
		this.token = token;
	}

	public final synchronized BigInteger getCellCount() {
		return cellCount;
	}

	public final synchronized int getDimensionCount() {
		return dimCount;
	}

	public final synchronized BigInteger getFilledCellCount() {
		return filledCellCount;
	}

	public final synchronized int getStatus() {
		return status;
	}

	public final synchronized int getToken() {
		return token;
	}

	public boolean canBeModified() {
		return true;
	}

	public boolean canCreateChildren() {
		return true;
	}

}
