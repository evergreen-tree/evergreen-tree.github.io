package org.palo.api;


/**
 * <code>Atribute</code>s are used to provide extra informations for an 
 * {@link Element} of a {@link Dimension}. Each <code>Element</code> can have 
 * several distinct attributes. 
 * <p>
 * With the {@link #setChildren(Attribute[])} method it is possible to build up 
 * an hierarchy of <code>Attributes</code>.
 * </p>
 * 
 * @author ArndHouben
 * @version $Id: Attribute.java,v 1.8 2007/10/24 12:18:22 ArndHouben Exp $
 */
public interface Attribute {
	
	public static final int TYPE_STRING = Element.ELEMENTTYPE_STRING;
	public static final int TYPE_NUMERIC = Element.ELEMENTTYPE_NUMERIC;
	
	
	/**
	 * The unique attribute identifier
	 * @return the unique identifier of the attribute
	 */
	public String getId();
	
	/**
	 * The attribute name 
	 * @return the name of the attribute
	 */
	public String getName();
	
	/**
	 * Sets the attribute name
	 * @param name the new attribute name
	 */
	public void setName(String name);
	
	/**
	 * Returns the attribute value for the given <code>Element</code> instance.
	 * @param element the <code>Element</code> to get the attribute value from
	 * @return the attribute value or null if the value is not specified
	 */
	public Object getValue(Element element);
	
	/**
	 * Sets the attribute value for the given <code>Element</code> instance.
	 * @param element the <code>Element</code> which attribute value to set
	 * @param value the new attribute value
	 */
	public void setValue(Element element, Object value);

	/**
	 * Convenient method to set the values for several <code>Element</code>s
	 * at once, i.e. the attribute value for the i.th element is set to the
	 * i.th object.  
	 * @param elements the elements to set the values for
	 * @param values the new values
	 */
	public void setValues(Element[] elements, Object[] values);
	
	/**
	 * Convenient method to receive the values for several <code>Element</code>s
	 * at once.  
	 * @param elements the elements to get the values from
	 * @return the attribute values for the given elements
	 */
	public Object[] getValues(Element[] elements);
	
	
	/**
	 * Checks if this attribute has any children attributes.
	 * @return true if the attribute has children, false otherwise.
	 */
	public boolean hasChildren();
	
	/**
	 * Sets the children attributes of this attribute. 
	 * <p>
	 * <b>Note:</b> this will remove all previously set children. 
	 * Specifying null is allowed and will remove all children! 
	 * </p>
	 * @param attributes the attribute children
	 */
	public void setChildren(Attribute[] attributes);	
	
	/**
	 * Convenient method to remove children attributes from this attribute
	 * @param attributes attribute children to remove
	 */
	public void removeChildren(Attribute[] attributes);
	
	/**
	 * Returns the children of this attribute
	 * @return an array of children attributes
	 */
	public Attribute[] getChildren();
	
	/**
	 * Returns the parent attributes of this attribute
	 * @return an array of parent attributes
	 */
	public Attribute[] getParents();
	
	/**
	 * Returns the attribute type which is one of the defined type constants
	 * @return attribute type
	 */
	public int getType();
}
