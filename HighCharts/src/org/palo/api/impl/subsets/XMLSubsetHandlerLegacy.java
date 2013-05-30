/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl.subsets;

import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Element;
import org.palo.api.Hierarchy;
import org.palo.api.PaloAPIException;
import org.palo.api.SubsetState;
import org.palo.api.impl.xml.IPaloEndHandler;
import org.palo.api.impl.xml.IPaloStartHandler;
import org.xml.sax.Attributes;

/**
 * <code>XMLSubsetHandlerLegacy</code>
 * Defines <code>{@link IPaloEndHandler}</code>s and 
 * <code>{@link IPaloEndHandler}</code>s to read subsets which are stored using
 * legacy version 
 *
 * @author ArndHouben
 * @version $Id: XMLSubsetHandlerLegacy.java,v 1.2 2008/04/15 08:20:57 PhilippBouillon Exp $
 **/
public class XMLSubsetHandlerLegacy extends XMLSubsetHandler {

	private final String key;

	XMLSubsetHandlerLegacy(Database db, final String key) {
		super(db);
		this.key = key;
	}

	IPaloStartHandler[] getStartHandlers(final Database database) {
		return new IPaloStartHandler[] { new IPaloStartHandler() {
			public String getPath() {
				return "subset";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				subsetBuilder = new SubsetBuilder();
				subsetBuilder.setId(key);
				String name = attributes.getValue("name");
				subsetBuilder.setName(name);
				subsetBuilder
						.setDescription(attributes.getValue("description"));
				subsetBuilder.setActiveState(attributes
						.getValue("activestrategy"));
				String srcDimId = attributes.getValue("sourceDimensionName"); //$NON-NLS-1$
				Dimension srcDim = database.getDimensionByName(srcDimId);
				if (srcDim == null)
					throw new PaloAPIException("Cannot find source dimension '"
							+ srcDimId + "'!!");
				subsetBuilder.setSourceHierarchy(srcDim.getDefaultHierarchy());
			}
		}, new IPaloStartHandler() {
			public String getPath() {
				return "subset/state";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				// get a fresh builder
				stateBuilder = new SubsetStateBuilder();
				stateBuilder.setId(attributes.getValue("id"));
				stateBuilder.setName(attributes.getValue("name"));
			}
		}, new IPaloStartHandler() {
			public String getPath() {
				return "subset/regularexpression";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				// get a fresh builder
				stateBuilder = new SubsetStateBuilder();
				stateBuilder.setId("regularexpression");
				stateBuilder.setName("Regular Expression");
				stateBuilder.setExpression(attributes.getValue("expression"));
			}
		}, new IPaloStartHandler() {
			public String getPath() {
				return "subset/flat";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				// get a fresh builder
				stateBuilder = new SubsetStateBuilder();
				stateBuilder.setId("flat");
				stateBuilder.setName("Flat");
			}
		}, new IPaloStartHandler() {
			public String getPath() {
				return "subset/flat/element";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				if (stateBuilder == null || subsetBuilder == null) {
					throw new PaloAPIException(
							"Cannot add elements to flat subset state!!");
				}
				String elementName = attributes.getValue("name");
				Hierarchy srcHier = subsetBuilder.getSourceHierarchy();
				Element element = srcHier.getElementByName(elementName);
				stateBuilder.addElement(element);
			}
		}, new IPaloStartHandler() {
			public String getPath() {
				return "subset/hierarchical";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				// get a fresh builder
				stateBuilder = new SubsetStateBuilder();
				stateBuilder.setId("hierarchical");
				stateBuilder.setName("Hierarchical");
			}
		}, new IPaloStartHandler() {
			public String getPath() {
				return "subset/hierarchical/element";
			}

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) {
				if (stateBuilder == null || subsetBuilder == null) {
					throw new PaloAPIException(
							"Cannot add elements to hierarchical subset state!!");
				}
				String elementName = attributes.getValue("name");
				Hierarchy srcHier = subsetBuilder.getSourceHierarchy();
				Element element = srcHier.getElementByName(elementName);
				stateBuilder.addElement(element);
			}
		} };
	}

	IPaloEndHandler[] getEndHandlers(Database database) {
		return new IPaloEndHandler[] { new IPaloEndHandler() {
			public String getPath() {
				return "subset/regularexpression";
			}

			public void endElement(String uri, String localName, String qName) {
				if (subsetBuilder == null || stateBuilder == null) {
					throw new PaloAPIException("Cannot create subset state!!");
				}
				SubsetState state = stateBuilder.createState();
				subsetBuilder.addState(state);
				stateBuilder = null;
			}
		}, new IPaloEndHandler() {
			public String getPath() {
				return "subset/flat";
			}

			public void endElement(String uri, String localName, String qName) {
				if (subsetBuilder == null || stateBuilder == null) {
					throw new PaloAPIException("Cannot create subset state!!");
				}
				SubsetState state = stateBuilder.createState();
				subsetBuilder.addState(state);
				stateBuilder = null;
			}
		}, new IPaloEndHandler() {
			public String getPath() {
				return "subset/hierarchical";
			}

			public void endElement(String uri, String localName, String qName) {
				if (subsetBuilder == null || stateBuilder == null) {
					throw new PaloAPIException("Cannot create subset state!!");
				}
				SubsetState state = stateBuilder.createState();
				subsetBuilder.addState(state);
				stateBuilder = null;
			}
		} };
	}
}
