/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api;

/**
 * The Property2Factory class can be used to easily create new Property2
 * objects.
 * 
 * @author Philipp Bouillon
 * @version $Id: Property2Factory.java,v 1.2 2007/12/05 10:58:07 PhilippBouillon Exp $
 */

public abstract class Property2Factory {
    private static Property2Factory instance;
    
    static {
        try {
            instance = (Property2Factory)
                Class.forName("org.palo.api.impl.Property2FactoryImpl").newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Property2Factory getInstance() {
        return instance;
    }

    /**
     * Creates a new property2 object that has no parent and is of type string.
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @return a new modifiable string property with no parent.
     */
    public abstract Property2 newProperty(Connection con, String id, String value);
    
    /**
     * Creates a new property2 object that has the given parent and is of type
     * string.
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @param parent the parent of this property.
     * @return a new modifiable string property with the given parent.
     */
    public abstract Property2 newProperty(Connection con, String id, String value, Property2 parent);
    
    /**
     * Creates a new property2 object that has no parent and is of the specified
     * type (one of the constants defined in Property2).
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @param type the type of this property. One of the constants defined 
     * in Property2.
     * @return a new modifiable property (type as specified) with no parent.
     */    
    public abstract Property2 newProperty(Connection con, String id, String value, int type);
    
    /**
     * Creates a new property2 object that has the given parent and is of the
     * specified type (one of the constants defined in Property2).
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @param parent the parent of this property.
     * @param type the type of this property. One of the constants defined 
     * in Property2.
     * @return a new modifiable property (type as specified) with the given
     * parent.
     */
    public abstract Property2 newProperty(Connection con, String id, String value, Property2 parent, int type);
    
    /**
     * Creates a new read-only property2 object that has no parent and is of
     * type string.
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @return a new unmodifiable string property with no parent.
     */
    public abstract Property2 newReadOnlyProperty(Connection con, String id, String value);
    
    /**
     * Creates a new read-only property2 object that has the given parent and is
     * of type string.
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @param parent the parent of this property.
     * @return a new unmodifiable string property with the given parent.
     */
    public abstract Property2 newReadOnlyProperty(Connection con, String id, String value, Property2 parent);
    
    /**
     * Creates a new read-only property2 object that has no parent and is of the
     * specified type (one of the constants defined in Property2).
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @param type the type of this property. One of the constants defined 
     * in Property2.
     * @return a new unmodifiable property (type as specified) with no parent.
     */        
    public abstract Property2 newReadOnlyProperty(Connection con, String id, String value, int type);
    
    /**
     * Creates a new read-only property2 object that has the given parent and is
     * of the specified type (one of the constants defined in Property2).
     * 
     * @param con the connection to which this property belongs.
     * @param id the new id of the property.
     * @param value the value for the property.
     * @param parent the parent of this property.
     * @param type the type of this property. One of the constants defined 
     * in Property2.
     * @return a new unmodifiable property (type as specified) with the given
     * parent.
     */    
    public abstract Property2 newReadOnlyProperty(Connection con, String id, String value, Property2 parent, int type);
}
