/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api.impl;

import org.palo.api.Consolidation;
import org.palo.api.Element;
import org.palo.api.ElementNode;
import org.palo.api.ElementNodeVisitor;

/**
 * <code>DimensionUtil</code>
 *
 * @author Stepan Rutz
 * @version $ID$
 */
class DimensionUtil
{
    public static interface ElementVisitor
    {
        void visit(Element element, Element parent);
    }
    
    public static void traverse(Element e, ElementVisitor v)
    {
    	traverse(e, null, v);
    }
    
    static void traverse(Element e, Element p, ElementVisitor v)
    {
        v.visit(e, p);
        Element children[] = e.getChildren();
        if (children == null)        	
        	return;        
        for (int i = 0; i < children.length; ++i)
        {
            traverse(children[i], e, v);
        }
    }
    
    //-------------------------------------------------------------------------
    
    public static void traverse(ElementNode n, ElementNodeVisitor v)
    {
        traverse(n, null, v);
    }
    
    static void traverse(ElementNode n, ElementNode p, ElementNodeVisitor v)
    {
        v.visit(n, p);
        Element children[] = n.getElement().getChildren();
        Consolidation consolidations[] = n.getElement().getConsolidations();
        if (children == null)
            return;
        for (int i = 0; i < children.length; ++i)
        {
        	if(children[i] == null)
        		continue;
            ElementNode child = new ElementNode(children[i], consolidations[i]);
            n.addChild(child);
            traverse(child, n, v);
        }
    }
    
}
