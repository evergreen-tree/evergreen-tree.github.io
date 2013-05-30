/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl.views;

import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Hierarchy;
import org.palo.api.impl.xml.EndHandler;
import org.palo.api.impl.xml.IPaloStartHandler;
import org.palo.api.impl.xml.StartHandler;
import org.palo.api.persistence.PersistenceError;
import org.palo.api.utils.ElementPath;
import org.xml.sax.Attributes;

/**
 * <code>CubeViewHandler1_4</code>
 * Defines <code>{@link StartHandler}</code>s and 
 * <code>{@link EndHandler}</code>s to read cube views which are stored using
 * version 1.4. New in this version is the switch to axis visible paths instead
 * of hidden ones. 
 *
 * @author ArndHouben
 * @version $Id: CubeViewHandler1_4.java,v 1.6 2008/04/21 08:30:34 PhilippBouillon Exp $
 **/
class CubeViewHandler1_4 extends CubeViewHandler1_3 {

	CubeViewHandler1_4(Database database) {
		super(database);
	}

    /**
     * Adds the "view/axis/visible" path of the xml description to the loader.
     */
	protected void registerStartHandlers() {
    	super.registerStartHandlers();
    	
    	registerStartHandler(new IPaloStartHandler() {
			public String getPath() {
				return "view/axis/visible";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				String path = attributes.getValue("path");
				String dimId = attributes.getValue("dimension");
				String hierId = attributes.getValue("hierarchy");
				if (dimId == null && hierId != null) {
					// Old solution had dim~~~hier in hierarchyId.
					// Read it here.
					String [] allIds = 
						hierId.split(CubeViewPersistence.DIM_HIER_DELIMITER);
					dimId = allIds[0];
					hierId = allIds[1];
				}
				Dimension dim = database.getDimensionById(dimId);
				if (dim == null) {
					addError("CubeViewReader: unknown dimension id '" + dimId
							+ "'!!", cubeView.getId(), cubeView, database,
							dimId, PersistenceError.UNKNOWN_DIMENSION,
							currAxis, PersistenceError.TARGET_GENERAL);
				}
				Hierarchy hier = null;
				if (hierId != null && dim != null) {
					hier = dim.getHierarchyById(hierId);
					if (hier == null) {
						addError("CubeViewReader: unknown hierarchy id '" + hierId
								+ "'!!", cubeView.getId(), cubeView, database,
								hierId, PersistenceError.UNKNOWN_DIMENSION,
								currAxis, PersistenceError.TARGET_GENERAL);						
					}
				} else if (dim != null) {
					hier = currAxis.getHierarchy(dim);
				}
				ElementPath elPath; 
				if (hier == null) {
					elPath = ElementPath.restore(new Hierarchy[]{dim.getDefaultHierarchy()}, path);
				} else {
					elPath = ElementPath.restore(new Hierarchy[]{hier}, path);
				}
				currAxis.addVisible(elPath);
			}
			
		});

	}
}
