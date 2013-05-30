/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.favoriteviews;

/**
 * A <code>FavoriteViewsFolder</code> is an object has a name and a position
 * and can be added to a <code>FavoriteViewTree</code>. It acts as a container
 * for <code>FavoriteView</code>s.
 *  
 * Please note that favorite views are saved for each connection, so each
 * favorite view can represent a view from any database of that connection.
 * Thus, the same is true for the <code>FavoriteViewsFolder</code>; it is
 * always bound to exactly one <code>Connection</code> object.
 * 
 * In order to create a <code>FavoriteViewsFolder</code> object, please use the
 * <code>{@link FavoriteViewFactory}</code>. In order to create a new
 * favorite views folder with the name <i>TestFolder</i> in the connection
 * <code>con</code>, you would use:
 * 
 * <code>
 *   FavoriteViewsFolder folder = 
 *       FavoriteViewFactory.getInstance().
 *         createFavoriteView("TestFolder", con);
 * </code>
 * 
 * When creating the folder, you can also specify an optional third
 * argument giving the position of the new folder realtive to its parent.
 * For example, if the parent of the folder (which, again, is a favorite view
 * folder) has five children, you can insert a new folder at any position from 0
 * to 5 (sic!).
 * The new child will be inserted after the specified number and thus, "5" is
 * valid in this case to append the new folder at the end of its parent's
 * children.
 
 * @author Philipp Bouillon
 * @version $Id: FavoriteViewsFolder.java,v 1.1 2007/06/25 13:36:43 PhilippBouillon Exp $
 */
public interface FavoriteViewsFolder extends FavoriteViewObject {
	/**
	 * Sets a new name for the folder.
	 * 
	 * @param newName the new name for the folder.
	 */
	public void setName(String newName);	
	
	/**
	 * Returns the position of the folder.
	 * 
	 * @return the position of this favorite views folder in relation to its
	 * parent.
	 */
	public int getPosition();
	
	/**
	 * Sets a new position for the folder.
	 * 
	 * @param newPosition the new position for the folder.
	 */
	public void setPosition(int newPosition);
}
