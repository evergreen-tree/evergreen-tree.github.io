/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.builders;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author Arnd Houben
 * @version $Id: InfoBuilderRegistry.java,v 1.2 2008/03/04 08:50:53 ArndHouben Exp $
 */
public class InfoBuilderRegistry {

	
	//--------------------------------------------------------------------------
	// FACTORY
	//
	private static final InfoBuilderRegistry instance = new InfoBuilderRegistry();
	public static final InfoBuilderRegistry getInstance() {
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private final CellInfoBuilder cellBuilder = new CellInfoBuilder();
	private final CubeInfoBuilder cubeBuilder = new CubeInfoBuilder();
	private final DatabaseInfoBuilder databaseBuilder = new DatabaseInfoBuilder();
	private final DimensionInfoBuilder dimensionBuilder = new DimensionInfoBuilder();
	private final ElementInfoBuilder elementBuilder = new ElementInfoBuilder();
	private final ServerInfoBuilder serverBuilder = new ServerInfoBuilder();
	private final RuleInfoBuilder ruleBuilder = new RuleInfoBuilder();
	private final LockInfoBuilder lockBuilder = new LockInfoBuilder();
	
	private InfoBuilderRegistry() {
	}
	
	public final CellInfoBuilder getCellBuilder() {
		return cellBuilder;
	}
	
	public final CubeInfoBuilder getCubeBuilder() {
		return cubeBuilder;
	}
	
	public final DatabaseInfoBuilder getDatabaseBuilder() {
		return databaseBuilder;
	}
	
	public final DimensionInfoBuilder getDimensionBuilder() {
		return dimensionBuilder;
	}
	
	public final ElementInfoBuilder getElementBuilder() {
		return elementBuilder;
	}
	
	public final ServerInfoBuilder getServerBuilder() {
		return serverBuilder;
	}
	
	public final RuleInfoBuilder getRuleBuilder() {
		return ruleBuilder;
	}
	
	public final LockInfoBuilder getLockBuilder() {
		return lockBuilder;
	}
}
