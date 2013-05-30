/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api.impl;

import org.palo.api.Consolidation;
import org.palo.api.Element;

/**
 * <code></code>
 *
 * @author Stepan Rutz
 * @version $ID$
 */
class VirtualConsolidationImpl implements Consolidation
{
    private final Element parent;
    private final Element child;
    private final double weight;
    
    VirtualConsolidationImpl(Element parent, Element child, double weight)
    {
        this.parent = parent;
        this.child = child;
        this.weight = weight;
    }
    
    public Element getParent()
    {
        return parent;
    }
    
    public Element getChild()
    {
        return child;
    }
    
    public double getWeight()
    {
        return weight;
    }
    
    public boolean equals(Object obj) {
    	if(obj instanceof VirtualConsolidationImpl) {
    		VirtualConsolidationImpl other = (VirtualConsolidationImpl) obj;
    		return parent.equals(other.parent) && child.equals(other.child);
    	}
    	return false;
    }
    
    public int hashCode() {
    	int hc = 17;
        hc += 31 * parent.hashCode();
        hc += 31 * child.hashCode();
        hc += 31 * weight;
        return hc;

    }
}
