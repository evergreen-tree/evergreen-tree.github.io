/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api;

/**
 * A <code>DimensionFilter</code> is used to decide which <code>Element</code>s
 * of a base <code>Dimension</code> are visible in its associated virtual
 * dimension. 
 *
 * @author Stepan Rutz
 * @version $ID$
 * @deprecated use {@link HierarchyFilter} instead.
 */
public interface DimensionFilter
{
    /**
     * Inits the filter and passes a reference to the virtual dimension
     * owning the filter.
     * @param dimension the owning virtual dimension of the filter.
     */
    void init(Dimension dimension);
    
    /**
     * Return <code>true</code> if the given {@link Element} passes
     * the filter, otherwise return <code>false</code>.
     * @param element the {@link Element} that is being filtered.
     * @return <code>true</code> to pass, <code>false</code> to filter out.
     */
    boolean acceptElement(Element element);
    
    /**
     * Turns this dimension into a flat dimension loosing and hierarchies
     * and consolidations.
     * @return <code>true</code> if the dimension is flat.
     */
    boolean isFlat();
    
    /**
     * If and only if the dimension is flat, this method can be used
     * to post-process the root element nodes.
     * 
     * @param rootNodes the original root nodes.
     * @return a new array of root nodes or <code>null</code> if the 
     * original root nodes should be used.
     */
    ElementNode[] postprocessRootNodes(ElementNode rootNodes[]);

}
