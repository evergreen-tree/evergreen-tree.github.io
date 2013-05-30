/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.favoriteviews;

import org.palo.api.Connection;
import org.palo.api.NamedEntity;

/**
 * A <code>FavoriteViewObject</code> represents a
 * <code>{@link FavoriteView}</code> or a
 * <code>{@link FavoriteViewsFolder}</code> object. Since both objects are
 * connection specific, they both have a connection attached to them. This
 * connection can be retrieved by using the <code>getConnection</code> method  
 * declared in this interface.
 *  
 * @author Philipp Bouillon
 * @version $Id: FavoriteViewObject.java,v 1.1 2007/06/25 13:36:43 PhilippBouillon Exp $
 */
public interface FavoriteViewObject extends NamedEntity {	
	/**
	 * Returns the <code>Connection</code> object that is attached to this
	 * favorite view or favorite views folder.
	 * 
	 * @return the attached Connection object.
	 */
	public Connection getConnection();
}
