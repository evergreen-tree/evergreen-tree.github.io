/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.loader;

import java.util.ArrayList;
import java.util.Collection;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.ElementInfo;
import com.tensegrity.palojava.HierarchyInfo;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.loader.ElementLoader;

/**
 * <code>HttpElementInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: HttpElementLoader.java,v 1.6 2008/04/17 18:11:50 ArndHouben Exp $
 **/
public class HttpElementLoader extends ElementLoader {

	public HttpElementLoader(DbConnection paloConnection,
			HierarchyInfo hierarchy) {
		super(paloConnection, hierarchy);
	}

	public String[] getAllElementIds() {
		if(!loaded) {			
			reload();
			loaded = true;
		}
		return getLoadedIds();
	}
	
	public final ElementInfo load(int index) {
		//TODO rewrite since palo supports getElementAt(position)!!!
		String[] elIds = getAllElementIds();
		if (index < 0 || index > elIds.length - 1)
			return null;
		return load(elIds[index]);
	}


	public ElementInfo loadByName(String name) {
		//first check if we have it loaded already
		ElementInfo elInfo = findElement(name);
		if(elInfo == null) {
			//if not, we have to ask server...
			reload();
			elInfo = findElement(name);
		}
		return elInfo;
	}

	public final ElementInfo[] getElementsAtDepth(int depth) {
		String[] ids = getAllElementIds();
		ArrayList<ElementInfo> lvlElements = new ArrayList<ElementInfo>(); 
		for (String id : ids) {
			ElementInfo info = load(id);
			if (info != null && info.getDepth() == depth) {
				lvlElements.add(info);
			}
		}
		return (ElementInfo[]) lvlElements.toArray(new ElementInfo[lvlElements
				.size()]);
	}
	
	public final ElementInfo[] getChildren(ElementInfo el) {
		String[] children = el.getChildren();
		ElementInfo[] _children = new ElementInfo[children.length];
		for(int i=0;i<children.length;++i) {
			_children[i] = load(children[i]);
			if(_children[i] == el) {
				return new ElementInfo[0];
			}
		}
		return _children;
	}

	protected final void reload() {
		reset();
		ElementInfo[] elInfos = paloConnection.getElements(hierarchy);
		for (ElementInfo elInfo : elInfos) {
			loaded(elInfo);
		}
	}
	
	private final ElementInfo findElement(String name) {
		Collection<PaloInfo> infos = getLoaded();
		for(PaloInfo info : infos) {
			if (info instanceof ElementInfo) {
				ElementInfo elInfo = (ElementInfo) info;
				//PALO IS NOT CASESENSETIVE!!
				if (elInfo.getName().equalsIgnoreCase(name))	
					return elInfo;
			}
		}
		return null;
	}
}
