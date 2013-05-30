/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.favoriteviews;

import org.palo.api.CubeView;

/**
 * A <code>FavoriteView</code> is an object which links a
 * <code>{@link CubeView}</code> with a connection and a new name, which can
 * be determined by the user.
 * <code>FavoriteView</code>s are always stored in a
 * <code>{@link FavoriteViewsFolder}</code>, which provides a container for
 * all saved favorite views.
 * 
 * Please note that favorite views are saved for each connection, so each
 * favorite view can represent a view from any database of that connection.
 * 
 * In order to create a <code>FavoriteView</code> object, please use the
 * <code>{@link FavoriteViewFactory}</code>. In order to create a new
 * favorite view with the name <i>TestFavoriteView</i>, representing the
 * <code>CubeView</code>, <i>cubeView</i>, you would use:
 * 
 * <code>
 *   FavoriteView favoriteView = 
 *       FavoriteViewFactory.getInstance().
 *         createFavoriteView("TestFavoriteView", cubeView);
 * </code>
 * 
 * When creating the favorite view, you can also specify an optional third
 * argument giving the position of the new favorite view realtive to its parent.
 * For example, if the parent of the favorite view (a favorite view folder) has
 * five children, you can insert a new view at any position from 0 to 5 (sic!).
 * The new child will be inserted after the specified number and thus, "5" is
 * valid in this case to append the new favorite view at the end of its parent's
 * children.
 
 * @author Philipp Bouillon
 * @version $Id: FavoriteView.java,v 1.2 2007/09/11 11:44:08 PhilippBouillon Exp $
 */
public interface FavoriteView extends FavoriteViewObject {
	/**
	 * Sets a new name for this favorite view. The name of the cube view
	 * attached to this favorite view is not changed.
	 * 
	 * @param newName the new name for this favorite view.
	 */
	void setName(String newName);
	
	/**
	 * Returns the position of this favorite view in relation to its parent. The
	 * position can be used to represent the favorite view tree in any GUI.
	 * With help of the position index, you can create an arbitrary ordering
	 * of the tree's elements.
	 * 
	 * @return the position of this favorite view in relation to its parent.
	 */
	int getPosition();
	
	/**
	 * Sets a new position for this favorite view. The position can be used to
	 * represent the favorite view tree in any GUI. With help of the position
	 * index, you can create an arbitrary ordering of the tree's elements.
	 * 
	 * @param newPosition the new position for this favorite view.
	 */
	void setPosition(int newPosition);
	
	/**
	 * Returns the <code>{@link CubeView}</code> that is attached to this
	 * favorite view.
	 * 
	 * @return the cube view that is attached to this favorite view or null if
	 * the view does not (or no longer) exist.
	 */
	CubeView getCubeView();			
}