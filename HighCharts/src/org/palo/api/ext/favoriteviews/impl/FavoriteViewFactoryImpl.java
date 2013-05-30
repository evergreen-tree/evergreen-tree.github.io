/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.favoriteviews.impl;

import org.palo.api.Connection;
import org.palo.api.CubeView;
import org.palo.api.ext.favoriteviews.FavoriteView;
import org.palo.api.ext.favoriteviews.FavoriteViewFactory;
import org.palo.api.ext.favoriteviews.FavoriteViewsFolder;

/**
 * <code>FavoriteViewFactoryImpl</code>
 * 
 * <p>The <code>FavoriteViewFactoryImpl</code> class provides factory methods 
 * to create favorite views and favorite view folders. The method calls are
 * translated into constructors of the respective classes, thus the clients
 * will never see the real constructor and so, the implementation is hidden
 * from the clients.
 * </p>
 *
 * @author Philipp Bouillon
 * @version $Id: FavoriteViewFactoryImpl.java,v 1.1 2007/06/25 13:36:43 PhilippBouillon Exp $
 */
public class FavoriteViewFactoryImpl extends FavoriteViewFactory {
	/**
	 * Creates a new favorite view with a default position (0). Can be
	 * used by clients that would like to implement a more complicated ordering
	 * scheme.
	 */
	public FavoriteView createFavoriteView(String name, CubeView view) {
		return new FavoriteViewImpl(name, view);
	}

	/**
	 * Creates a new favorite view.
	 */
	public FavoriteView createFavoriteView(String name, CubeView view,
			int position) {
		return new FavoriteViewImpl(name, view, position);
	}

	/**
	 * Creates a new favorite views folder with a default position (0).
	 */
	public FavoriteViewsFolder createFolder(String name, Connection con) {
		return new FavoriteViewsFolderImpl(name, con);
	}

	/**
	 * Creates a new favorite views folder.
	 */
	public FavoriteViewsFolder createFolder(String name, Connection con,
			int position) {
		return new FavoriteViewsFolderImpl(name, con, position);
	}
}
