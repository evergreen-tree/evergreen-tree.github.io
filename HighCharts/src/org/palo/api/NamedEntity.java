/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api;

/**
 * <code>NamedEntity</code>, defines an interface for domain
 * objects that support the notion of a name.
 *
 * @author Stepan Rutz
 * @version $ID$
 */
public interface NamedEntity
{
    /**
     * Returns the name of the entity.
     * @return the name of the entity.
     */
    String getName();
}
