/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl.subsets;

import java.util.HashMap;

import org.palo.api.Database;
import org.palo.api.Subset;
import org.palo.api.impl.xml.IPaloEndHandler;
import org.palo.api.impl.xml.IPaloStartHandler;

/**
 * A <code>XMLSubsetHandler</code> defines an additional abstraction layer to 
 * the persistence handling of subsets. This provides the possibility to
 * write and read different subset versions. Right now we have to support the
 * subset versions which are handled by <code>{@link XMLSubsetHandler1_0}</code>,
 * <code>{@link XMLSubsetHandler1_1}</code> and 
 * <code>{@link XMLSubsetHandlerLegacy}</code> respectively.
 *
 * @author ArndHouben
 * @version $Id: XMLSubsetHandler.java,v 1.1 2007/07/04 13:07:36 ArndHouben Exp $
 **/
abstract class XMLSubsetHandler {

	private final HashMap endHandlers = new HashMap();
	private final HashMap startHandlers = new HashMap();
	protected SubsetBuilder subsetBuilder;
	protected SubsetStateBuilder stateBuilder;
	
	XMLSubsetHandler(Database database) {
		registerHandlers(database);
	}

	/**
	 * Creates and return the read in <code>{@link Subset}</code>
	 * @return read in <code>{@link Subset}</code>
	 */
	final Subset getSubset() {
		if(subsetBuilder != null)
			return subsetBuilder.createSubset();
		return null;
	}
	
	/**
	 * Returns all defined <code>{@link IPaloStartHandler}</code> for reading
	 * in <code>{@link Subset}</code>
	 * @return defined <code>{@link IPaloStartHandler}</code>
	 */
	final IPaloStartHandler[] getStartHandlers() {
		return (IPaloStartHandler[]) startHandlers.values().toArray(
				new IPaloStartHandler[startHandlers.size()]);
	}

	/**
	 * Returns all defined <code>{@link IPaloEndHandler}</code> for reading
	 * in <code>{@link Subset}</code>
	 * @return defined <code>{@link IPaloEndHandler}</code>
	 */
	final IPaloEndHandler[] getEndHandlers() {
		return (IPaloEndHandler[]) endHandlers.values().toArray(
				new IPaloEndHandler[endHandlers.size()]);
	}

	/**
	 * Returns all defined <code>{@link IPaloStartHandler}</code> for reading
	 * in <code>{@link Subset}</code>. This method has to be implemented by
	 * sublcasses.
	 * @return defined <code>{@link IPaloStartHandler}</code>
	 */
	abstract IPaloStartHandler[] getStartHandlers(Database database);
	/**
	 * Returns all defined <code>{@link IPaloEndHandler}</code> for reading
	 * in <code>{@link Subset}</code>. This method has to be implemented by
	 * sublcasses.
	 * @return defined <code>{@link IPaloEndHandler}</code>
	 */
	abstract IPaloEndHandler[] getEndHandlers(Database database);

	
	private final void registerHandlers(Database database) {
		IPaloStartHandler[] stHandlers = getStartHandlers(database);
		for(int i=0;i<stHandlers.length;++i) {
			startHandlers.put(stHandlers[i].getPath(), stHandlers[i]);
		}
		IPaloEndHandler[] enHandlers = getEndHandlers(database);
		for(int i=0;i<enHandlers.length;++i) {
			endHandlers.put(enHandlers[i].getPath(), enHandlers[i]);
		}
	}
}
