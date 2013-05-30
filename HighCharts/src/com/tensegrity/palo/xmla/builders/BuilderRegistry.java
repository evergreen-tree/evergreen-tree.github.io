package com.tensegrity.palo.xmla.builders;

public class BuilderRegistry {
	private static BuilderRegistry instance = new BuilderRegistry();
	
	private final DatabaseInfoBuilder databaseInfoBuilder = new DatabaseInfoBuilder();
	private final CubeInfoBuilder cubeInfoBuilder = new CubeInfoBuilder();
	private final DimensionInfoBuilder dimensionInfoBuilder = new DimensionInfoBuilder();
	private final ElementInfoBuilder elementInfoBuilder = new ElementInfoBuilder();
	private final RuleInfoBuilder ruleInfoBuilder = new RuleInfoBuilder();
	private final VariableInfoBuilder variableInfoBuilder = new VariableInfoBuilder();
	
	private BuilderRegistry() {		
	}
	
	public static BuilderRegistry getInstance() {
		return instance;
	}
	
	public DatabaseInfoBuilder getDatabaseInfoBuilder() {
		return databaseInfoBuilder;
	}
	
	public CubeInfoBuilder getCubeInfoBuilder() {
		return cubeInfoBuilder;
	}
	
	public DimensionInfoBuilder getDimensionInfoBuilder() {
		return dimensionInfoBuilder;
	}
	
	public ElementInfoBuilder getElementInfoBuilder() {
		return elementInfoBuilder;
	}
	
	public RuleInfoBuilder getRuleInfoBuilder() {
		return ruleInfoBuilder;
	}
	
	public VariableInfoBuilder getVariableInfoBuilder() {
		return variableInfoBuilder;
	}
}
