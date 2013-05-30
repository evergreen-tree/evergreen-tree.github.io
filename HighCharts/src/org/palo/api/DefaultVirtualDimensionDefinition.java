package org.palo.api;

/**
 * <code>DefaultVirtualDimensionDefinition</code>.
 *
 * @author Stepan Rutz
 * @version $ID$
 */

public class DefaultVirtualDimensionDefinition implements
		VirtualDimensionDefinition {
	
	private final DimensionFilter filter;
	private final Dimension sourceDimension;
	private final boolean isFlat;
	private final Element[] elements;
	private final ElementNode[] rootNodes;
	private final String activeSubset;
	private Hierarchy activeHierarchy;
	
	/**
	 * Creates a new <code>DefaultVirtualDimensionDefinition</code> based on the
	 * given source <code>Dimension</code> and requires a <code>DimensionFilter</code>
	 * 
	 * @param sourceDimension the <code>Dimension</code> on which this 
	 * virtual dimension is based
	 * @param filter a <code>DimensionFilter</code> 
	 */
	public DefaultVirtualDimensionDefinition(Dimension sourceDimension,
			DimensionFilter filter,String activeSubset) {
		if (sourceDimension == null)
			throw new IllegalArgumentException("sourceDimension cannot be null");
		this.sourceDimension = sourceDimension;
		this.filter = filter;
		this.isFlat = filter.isFlat();
		elements = null;
		rootNodes = null;
		this.activeSubset = activeSubset;
		activeHierarchy = sourceDimension.getDefaultHierarchy();
	}
	
	public DefaultVirtualDimensionDefinition(Dimension sourceDimension,Element[] elements, ElementNode[] rootNodes,boolean isFlat,String activeSubset) {
		this.isFlat = isFlat;
		this.elements = elements;
		this.rootNodes = rootNodes;		
		this.sourceDimension = sourceDimension;
		filter = null;
		this.activeSubset = activeSubset;
		this.activeHierarchy = sourceDimension.getDefaultHierarchy();
	}
	
	public void setActiveHierarchy(Hierarchy hier) {
		activeHierarchy = hier;
	}
	
	public Dimension getSourceDimension() {
		return sourceDimension;
	}

	public DimensionFilter getFilter() {
		return filter;
	}

	public boolean isFlat() {
		return isFlat;
	}

	public Element[] getElements() {
		return elements;
	}

	public ElementNode[] getRootElements() {
		return rootNodes;
	}
	
	public final String getActiveSubset() {
		return activeSubset;
	}
	
	public final Hierarchy getActiveHierarchy() {
		return activeHierarchy;
	}
}
