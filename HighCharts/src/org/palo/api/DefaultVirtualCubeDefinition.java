/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api;

/**
 * <code>DefaultVirtualCubeDefinition</code>
 *
 * @author Stepan Rutz
 * @version $ID$
 * @deprecated please use <code>CubeView</code>s and <code>Axis</code>s to 
 * persist a certain cube state
 */
public class DefaultVirtualCubeDefinition implements VirtualCubeDefinition {
	private final Cube sourceCube;
	private final String name;
	private final VirtualDimensionDefinition[] virtualDimensionDefinitions;

	/**
	 * Creates a new <code>DefaultVirtualCubeDefinition</code> based on the
	 * given <code>Cube</code> with the given <code>VirtualDimensionDefintion</code>s
	 * 
	 * @param sourceCube the <code>Cube</code> which current view should be made
	 * persistent
	 * @param virtualDimensionDefinitions 
	 */
	public DefaultVirtualCubeDefinition(String name, Cube sourceCube,
			VirtualDimensionDefinition[] virtualDimensionDefinitions) {
		this.name = name;
		this.sourceCube = sourceCube;
		this.virtualDimensionDefinitions = virtualDimensionDefinitions;
	}

	public String getName() {
		return name;
	}
	
	public Cube getSourceCube() {
		return sourceCube;
	}

	public VirtualDimensionDefinition[] getVirtualDimensionDefinitions() {
		return virtualDimensionDefinitions;
	}

}
