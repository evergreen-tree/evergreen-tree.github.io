/*
 * (c) Copyright 2002 Tensegrity Software GmbH
 * All Rights Reserved.
 */
package org.palo.api.impl.utils;

/**
 * {@<describe>}
 * <p>
 * Thrown if an <code>ArrayList</code> iterator detects that someone else has 
 * concurrently modified a container. The concurrent modification checking is 
 * fail-fast and not synchronized. Thus it is not reliable.
 * </p>
 * {@</describe>}
 * 
 * @author  S. Rutz
 * @version $Id: ListModifiedException.java,v 1.1 2007/01/31 14:02:13 ArndHouben Exp $
 */
public class ListModifiedException extends RuntimeException
{
    /**
     * Constructs a <code>ListModified</code> exception.
     */
    public ListModifiedException ()
    {
        super ("array modified concurrently while iterating over contents");
    }

    /**
     * Constructs an ArrayListModified exception.
     * @param what a detailed message for the exception.
     */
    public ListModifiedException (String what)
    {
        super (what);
    }
}
