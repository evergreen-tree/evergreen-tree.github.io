/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <code>ElementNode</code>
 * <p>ElementNodes are used to construct a tree of consolidations for
 * a given dimension. Since elements can be consolidated multiple times, it is 
 * not practicable to use them as is for representing tree-nodes in the
 * consolidation-hierarchy instead ElementNodes are used. Each element-node
 * wraps an element where a single element can be wrapped in multiple
 * ElementNodes. The wrapped element can be retrieved by invoking
 * {@link #getElement()}.
 * </p>
 *
 * @author Stepan Rutz
 * @version $Id: ElementNode.java,v 1.14 2008/05/08 10:17:17 ArndHouben Exp $
 */
public class ElementNode
{
    protected final Element element;
    private final Consolidation consolidation;
    private final int index;
    private ElementNode parent;
    protected ArrayList<ElementNode> children;
//    protected LinkedHashSet<ElementNode> children;
    
    /**
     * Constructs a new <code>ElementNode</code>
     * @param element the {@link Element} to wrap.
     */
    public ElementNode(Element element)
    {
        this(element, null);
    }
    
    /**
     * Constructs a new <code>ElementNode</code>
     * @param element the {@link Element} to wrap.
     * @param consolidation the {@link Consolidation} of this node.
     */
    public ElementNode(Element element, Consolidation consolidation)
    {
        this(element, consolidation, -1);
    }
    
    /**
     * Constructs a new <code>ElementNode</code>
     * @param element the {@link Element} to wrap.
     * @param consolidation the {@link Consolidation} of this node.
     * @param index index in parent (optional)
     */
    public ElementNode(Element element, Consolidation consolidation, int index)
    {
        this.element = element;
        this.consolidation = consolidation;
        this.index = index;
//        this.children = new LinkedHashSet<ElementNode>(1);
        this.children = new ArrayList<ElementNode>(1);
        //System.err.println ("ElementNode.ElementNode " + element + " => " + index);
        
    }
    
    //-------------------------------------------------------------------------
    // basic
    
    /**
     * Returns the optional index of this instance or -1 if no index was set.
     * @param the index of this instance or -1 if none was set
     */
    public final int getIndex() {
    	return index;
    }
    /**
     * Returns the wrapped {@link Element}.
     * @return the wrapped {@link Element}.
     */
    public final Element getElement()
    {
        return element;
    }
    
    /**
     * Returns the {@link Consolidation} of this instance.
     * @return the {@link Consolidation} of this instance.
     */
    public final Consolidation getConsolidation()
    {
        return consolidation;
    }
    
    public final synchronized void setParent(ElementNode parent)
    {
        this.parent = parent;
    }
    
    /**
     * Returns the parent <code>ElementNode</code> or
     * <code>null</code>.
     * @return the parent <code>ElementNode</code> or
     * <code>null</code>.
     */
    public final synchronized ElementNode getParent()
    {
        return parent;
    }
    
    /**
     * Returns the depth of this <code>ElementNode</code>
     * in the consolidation hierarchy.
     * @return the depth of this <code>ElementNode</code>
     */
    public final synchronized int getDepth()
    {
        if (parent == null)
            return 0;
        return 1 + parent.getDepth();
    }
    
    //-------------------------------------------------------------------------
    // children
    
    /**
     * Adds a child to this <code>ElementNode</code>.
     * Note: This is an internal method and it is not
     * required to invoke it under most circumstances.
     * 
     * @param child the child to add.
     */
    public final synchronized void addChild(ElementNode child)
    {
    	if (!children.contains(child)) {
			children.add(child);
			child.setParent(this);
		}
    }
    
    /**
     * Removes a child from this <code>ElementNode</code>.
     * Note: This is an internal method and it is not
     * required to invoke it under most circumstances.
     * 
     * @param child the child to remove.
     */
    public final synchronized void removeChild(ElementNode child)
    {
        if(children.remove(child)) {
//        	System.out.println("removed child!");        
        	child.setParent(null);
        }
    }
    
    /**
     * Removes all children from this <code>ElementNode</code>.
     * Note: This is an internal method and it is not
     * required to invoke it under most circumstances.
     */
    public final synchronized void removeChildren()
    {
    	Iterator<ElementNode> allChildren = children.iterator();
    	while(allChildren.hasNext()) {
    		ElementNode child = allChildren.next();
    		child.setParent(null);
    		allChildren.remove();
    	}
//        for (int i = children.size() - 1; i >= 0; --i)
//        {
//            ElementNode child = (ElementNode) children.get(i);
//            children.remove(child);
//            child.setParent(null);
//        }
    }
    
    /**
     * Returns the children of this <code>ElementNode</code>.
     * @return the children of this <code>ElementNode</code>.
     */
    public synchronized ElementNode[] getChildren()
    {
        return (ElementNode[]) children.toArray(
            new ElementNode[children.size()]);
    }
    
    /**
     * Returns whether this <code>ElementNode</code> has any children.
     * @return whether this <code>ElementNode</code> has any children.
     */
    public synchronized boolean hasChildren()
    {
        return children.size() > 0;
    }
    
    
    //-------------------------------------------------------------------------
    // object overrides
    public final String toString()
    {
        return "ElementNode (" + Integer.toHexString(System.identityHashCode(this)) + "/" + Integer.toHexString(hashCode()) + " " + element.toString() + ")";
    }
    
    public final boolean equals(Object obj)
    {
        if (!(obj instanceof ElementNode))
            return false;
        
        ElementNode other = (ElementNode) obj;
        
        boolean eq = element.equals(other.getElement());
        if (parent != null && other.getParent() != null)
        {
            eq &= parent.getElement().equals(other.getParent().getElement());
        }
        else
        {
            eq &= parent == null && other.getParent() == null;
        }
        
        if (index != -1 && other.index != -1)
        {
            eq &= index == other.index;
        }
        
        return eq;
    }
    
    public final int hashCode() {
		int hc = 3;
		hc += 3 * element.hashCode();
		if (parent != null)
			hc += 3 * parent.hashCode();
		if (index != -1)
			hc += 3 * index;

		return hc;
	}
}

