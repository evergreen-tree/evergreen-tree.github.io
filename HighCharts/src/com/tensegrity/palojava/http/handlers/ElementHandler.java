/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava.http.handlers;

import java.io.IOException;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.ElementInfo;
import com.tensegrity.palojava.http.HttpConnection;
import com.tensegrity.palojava.http.builders.ElementInfoBuilder;
import com.tensegrity.palojava.http.builders.InfoBuilderRegistry;
import com.tensegrity.palojava.impl.ElementInfoImpl;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id: ElementHandler.java,v 1.3 2008/01/11 15:05:29 ArndHouben Exp $
 */
public class ElementHandler extends HttpHandler {

	//have to add the database, dimension and element ids as well as children
	//ids and their weights, e.g.: /element/append?idatabase=0&idimension=0&ielement=29&ichildren=6,7,8&weights=0.1,0.5,0.4
	private static final String APPEND_PREFIX = "/element/append?database=";
	//have to add the database, dimension and element ids, e.g.: /element/delete?idatabase=0&idimension=0&ielement=112
	private static final String DELETE_PREFIX= "/element/destroy?database=";
	//have to add the database and dimension ids, the new element name, its type
	//the ids of its children and their corresponding weight, e.g.:
	//element/create?idatabase=0&idimension=0&new_name=new+element&type=4&ichildren=1,2,3&weights=0.1,0.5,0.4
	private static final String CREATE_PREFIX = "/element/create?database=";	
	//have to add the database, dimension and element ids, e.g.: /element/info?idatabase=0&idimension=0&ielement=0
	private static final String INFO_PREFIX = "/element/info?database=";	
	//have to add the database, dimension and element ids as well as the new
	//position, e.g.:/element/move?idatabase=0&idimension=0&ielement=4&position=2
	private static final String MOVE_PREFIX = "/element/move?database=";	
	//have to add the database, dimension and element ids as well as the new
	//name, e.g.: /element/rename?idatabase=0&idimension=0&ielement=29&new_name=week
	private static final String RENAME_PREFIX = "/element/rename?database=";	
	//have to add the database, dimension and element ids
	///element/replace?idatabase=0&idimension=0&new_name=new+element&type=4&ichildren=6,7,8&weight=0.1,0.5,0.4
	//OR 
	///element/replace?idatabase=0&idimension=0&ielement=30&type=4&ichildren=6,7,8&weight=0.1,0.5,0.4
	private static final String REPLACE_PREFIX = "/element/replace?database=";

	
	
	//--------------------------------------------------------------------------
	// FACTORY
	//
	private static final ElementHandler instance = new ElementHandler();
	static final ElementHandler getInstance(HttpConnection connection) {
		instance.use(connection);
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private final InfoBuilderRegistry builderReg;
	private ElementHandler() {
		builderReg = InfoBuilderRegistry.getInstance();
	}

//	/element/append  	Adds children to consolidated elements.  	dimension
	public final ElementInfo append(ElementInfo element, ElementInfo[] children,
			double[] weights) throws IOException {
		DimensionInfo dimension = element.getDimension();
		DatabaseInfo database = dimension.getDatabase();
		String childrenStr = getIdString(children);
		String weightStr = getWeightString(weights);

		StringBuffer query = new StringBuffer();
		query.append(APPEND_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		query.append("&element=");
		query.append(element.getId());
		query.append("&children=");
		query.append(childrenStr);
		query.append("&weights=");
		query.append(weightStr);
		String[][] response = request(query.toString());
		ElementInfoBuilder elementBuilder = builderReg.getElementBuilder();
		elementBuilder.update((ElementInfoImpl)element, response[0]);
		return element;
	}
	
//	/element/create 	Creates new element. 	dimension
	public final ElementInfo create(DimensionInfo dimension, String name, int type,
			ElementInfo[] children, double[] weights) throws IOException {
		DatabaseInfo database = dimension.getDatabase();
		String childrenStr = getIdString(children);
		String weightStr = getWeightString(weights);
		// TODO adjust type:
		// type = getNewType(type);
		StringBuffer query = new StringBuffer();
		query.append(CREATE_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		// name = name.replaceAll("\\s","+");
		query.append("&new_name=");
		query.append(encode(name));
		query.append("&type=");
		query.append(type);
		query.append("&children=");
		query.append(childrenStr);
		query.append("&weights=");
		query.append(weightStr);
		String[][] response = request(query.toString());
		ElementInfoBuilder elementBuilder = builderReg.getElementBuilder();
		return elementBuilder.create(dimension, response[0]);
	}
	
//	/element/destroy 	Deletes an element. 	dimension
	public final boolean destroy(ElementInfo element) throws IOException {
		DimensionInfo dimension = element.getDimension();
		DatabaseInfo database = dimension.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(DELETE_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		query.append("&element=");
		query.append(element.getId());
		String[][] response = request(query.toString());
		return response[0][0].equals(OK);
	}
	
//	/element/info 	Shows identifer, name, position, level, depth, parents and children of an element. 	dimension
	public final ElementInfo getInfo(DimensionInfo dimension, String id)
			throws IOException {
		DatabaseInfo database = dimension.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(INFO_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		query.append("&element=");
		query.append(id);
		String[][] response = request(query.toString());
		ElementInfoBuilder elementBuilder = builderReg.getElementBuilder();
		return elementBuilder.create(dimension, response[0]);
	}
	
//	/element/move 	Changes position of an element. 	dimension
	public final ElementInfo move(ElementInfo element, int newPosition)
			throws IOException {
		DimensionInfo dimension = element.getDimension();
		DatabaseInfo database = dimension.getDatabase();

		StringBuffer query = new StringBuffer();
		query.append(MOVE_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		query.append("&element=");
		query.append(element.getId());
		query.append("&position=");
		query.append(newPosition);
		String[][] response = request(query.toString());
		ElementInfoBuilder elementBuilder = builderReg.getElementBuilder();
		elementBuilder.update((ElementInfoImpl)element, response[0]);
//		return elementBuilder.create(dimension, response[0]);
		return element;
	}
	
	public final ElementInfo reload(ElementInfo element) throws IOException {
		DimensionInfo dimension = element.getDimension();
		DatabaseInfo database = dimension.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(INFO_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		query.append("&element=");
		query.append(element.getId());
		String[][] response = request(query.toString());
		ElementInfoBuilder elementBuilder = builderReg.getElementBuilder();
		elementBuilder.update((ElementInfoImpl)element, response[0]);
		return element;
	}
//	/element/rename 	Renames an element. 	dimension
	public final ElementInfo rename(ElementInfo element, String newName)
			throws IOException {
		DimensionInfo dimension = element.getDimension();
		DatabaseInfo database = dimension.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(RENAME_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		query.append("&element=");
		query.append(element.getId());
		query.append("&new_name=");
		query.append(encode(newName));
		String[][] response = request(query.toString());
		ElementInfoBuilder elementBuilder = builderReg.getElementBuilder();
		elementBuilder.update((ElementInfoImpl)element, response[0]);
		return element;
	}
	
//	/element/replace 	Changes or creates a new element. Replaces children in consolidated elements. 	dimension
	public final void update(ElementInfo element, int type, String[] children, double[] weights) throws IOException {
		DimensionInfo dimension = element.getDimension();
		DatabaseInfo database = dimension.getDatabase();
		String childrenStr = getIdString(children);
		String weightStr = getWeightString(weights);
		StringBuffer query = new StringBuffer();
		query.append(REPLACE_PREFIX);
		query.append(database.getId());
		query.append("&dimension=");
		query.append(dimension.getId());
		query.append("&element=");
		query.append(element.getId());
//		query.append("&name_element=");
//		query.append(name);
		query.append("&type=");
		query.append(type);
		query.append("&children=");
		query.append(childrenStr);
		query.append("&weights=");
		query.append(weightStr);
		String[][] response = request(query.toString());
		ElementInfoBuilder elementBuilder = builderReg.getElementBuilder();
		elementBuilder.update((ElementInfoImpl)element, response[0]);
	}
}
