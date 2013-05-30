/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api.impl.views;

import java.util.HashMap;

import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Property;
import org.palo.api.impl.PersistenceErrorImpl;
import org.palo.api.persistence.PersistenceError;

/**
 * An internally used builder to create {@link CubeView}s. The main usage of 
 * this builder is during the restore of persistent cube views from their xml
 * representation. Therefore it provides a setter method for each cube view
 * field.
 * 
 * @author ArndHouben
 * @version $Id: CubeViewBuilder.java,v 1.11 2007/08/09 19:27:34 ArndHouben Exp $
 */
class CubeViewBuilder {
	
	//required fields:
	private String id;
	private String name;
	private HashMap properties = new HashMap();
	private Cube cube;
	
	//optional:
	private String description;
	
	final synchronized CubeView createView(CubeViewHandler viewHandler) {
		if (id == null || name == null || cube == null) {
			PersistenceError error = new PersistenceErrorImpl(
					"Could not create cube view, insufficient information", id,
					cube, null, null, PersistenceError.LOADING_FAILED, null,
					PersistenceError.TARGET_GENERAL);
			viewHandler.addError(error);
			return null;
			// throw new PaloAPIException(
			// "Cannot create cube view, insufficient information");
		}
		int n = properties.size();
		Object[] params = new Object[3 + n];
		params[0] = id;
		params[1] = name;
		params[2] = cube;
		Property[] props = null;
		if (n > 0) {
			props = new Property[n];
			String[] keys = (String[]) properties.keySet().toArray(
					new String[properties.size()]);
			for (int i = 0; i < n; i++) {
				props[i] = new Property(keys[i], (String) properties
						.get(keys[i]));
				params[3 + i] = props[i];
			}
		}
		CubeViewManager creator = CubeViewManager.getInstance();
		CubeView view = (CubeView) creator.create(CubeView.class, params);
		// reset view to perform an update:
		((CubeViewImpl) view).reset();

		if (description != null)
			view.setDescription(description);
		return view;
	}
	
	final synchronized void setCube(Cube cube) {
		this.cube = cube;
	}
	final synchronized void setDescription(String description) {
		this.description = description;
	}
	final synchronized void setId(String id) {
		this.id = id;
	}
	final synchronized void setName(String name) {
		this.name = name;
	}
	final synchronized void addProperty(String id, String value) {
		properties.put(id, value);
	}
	final synchronized void addProperty(Property property) {
		if (property == null) {
			return;
		}
		properties.put(property.getId(), property.getValue());
	}
}
