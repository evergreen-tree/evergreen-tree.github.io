/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api;

/**
 * <code>ConnectionListener</code>
 * <p>
 * This interface specifies the methods a <code>ConnectionListener</code>
 * instance must provide.
 * </p>
 * 
 * @author Stepan Rutz
 * @version $ID$
 */
public interface ConnectionListener
{
    /**
     * Invoked when the connection was changed.
     * @param event the event carrying detail information
     * about the change.
     */
    void connectionChanged(ConnectionEvent event);
}
