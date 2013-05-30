/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl.views;

import org.palo.api.Database;
import org.palo.api.impl.xml.EndHandler;
import org.palo.api.impl.xml.IPaloStartHandler;
import org.palo.api.impl.xml.StartHandler;
import org.xml.sax.Attributes;

/**
 * <code>CubeViewHandler1_2</code>
 * Defines <code>{@link StartHandler}</code>s and 
 * <code>{@link EndHandler}</code>s to read cube views which are stored using
 * version 1.2
 *
 * @author Philipp Bouillon
 * @version $Id: CubeViewHandler1_2.java,v 1.2 2007/05/18 10:18:14 PhilippBouillon Exp $
 **/
class CubeViewHandler1_2 extends CubeViewHandler1_1 {
    /**
     * Creates a new instance of the <code>CubeViewHandler1_2</code> operating
     * on the specified <code>Database</code>.
     * 
     * @param database the <code>Database</code> for which CubeViews are to be
     * loaded.
     */
	CubeViewHandler1_2(Database database) {
    	super(database);
    }
    
    /**
     * Adds the "viw/property" path of the xml description to the loader.
     * Any CubeView can be attributed with any number of arbitrary key/value
     * properties. Those properties are stored in xml files for views starting
     * with version 1.2 and are read in here.
     */
	protected void registerStartHandlers() {
    	super.registerStartHandlers();
    	    	
    	// add property handling:
    	registerStartHandler(new IPaloStartHandler() {
			public String getPath() {
				return "view/property";
			}

			public void startElement(String uri, String localName, String qName, Attributes attributes) {
				try {
					String id = attributes.getValue("id");
					String value = attributes.getValue("value");	
					cubeView.addProperty(id, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
