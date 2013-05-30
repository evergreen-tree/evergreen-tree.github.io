/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api;

/**
 * <code>Consolidation</code>. <p>A instance of this class represents
 * a consolidation of a PALO {@link org.palo.api.Element}.
 * </p>
 * <p>
 * <code>Consolidation</code>s can be constructed by invoking
 * {@link org.palo.api.Dimension#newConsolidation(Element, Element, double)}
 * and are also returned from consolidated elements when calling
 * {@link org.palo.api.Element#getConsolidations()} or
 * {@link org.palo.api.Element#getConsolidationAt(int)}.
 * </p>
 *
 * @author Stepan Rutz
 * @version $ID$
 * 
 * @see org.palo.api.PaloAPIException
 */
public interface Consolidation
{
    /**
     * Returns the parent {@link Element} of this consolidation.
     * @return the parent {@link Element} of this consolidation.
     */
    Element getParent();
    
    /**
     * Returns the child {@link Element} of this consolidation.
     * @return the child {@link Element} of this consolidation.
     */
    Element getChild();
    
    /**
     * The consolidation weight. This weight is used as a factor on the 
     * element's value when the consolidated value is computed.
     * @return the consolidation weight.
     */
    double getWeight();
}
