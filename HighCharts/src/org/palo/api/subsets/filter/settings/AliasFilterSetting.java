/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import org.palo.api.subsets.Subset2;
import org.palo.api.subsets.filter.AliasFilter;

/**
 * <code>AliasFilterSetting</code>
 * <p>
 * Manages the settings for the {@link AliasFilter}.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: AliasFilterSetting.java,v 1.5 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class AliasFilterSetting extends AbstractFilterSettings {

	private StringParameter alias1;
	private StringParameter alias2;
	
	public AliasFilterSetting() {
		reset();
	}
	
	/**
	 * Returns the {@link StringParameter} of the n.th alias. The returned
	 * <code>StringParmeter</code> contains the alias identifier as its value
	 * or <code>null</code> if no id was defined.
	 * @param number either 1 or 2 to get the first or second alias 
	 * @return the <code>StringParmeter</code> of the alias to use
	 */
	public final StringParameter getAlias(int number) {
		switch(number) {
		case 1: return alias1;
		case 2: return alias2;
		}
		return null;
	}
	
	/**
	 * Sets the id for the n.th alias
	 * @param number either 1 or 2 to specify the first or second alias
	 * @param id the alias identifier
	 */
	public final void setAlias(int number, String id) {
		switch(number) {
		case 1: alias1.setValue(id); break;
		case 2: alias2.setValue(id); break;
		}
	}

	public final void setAlias(int number, StringParameter alias) {
		switch(number) {
		case 1: alias1 = alias; alias1.bind(subset); break;
		case 2: alias2 = alias; alias2.bind(subset); break;
		}		
	}

	public final void reset() {
		alias1 = new StringParameter();
		alias2 = new StringParameter();
		bind(subset);
	}
	
	public final void bind(Subset2 subset) {
		super.bind(subset);
		//bind internal:
		alias1.bind(subset);
		alias2.bind(subset);
	}
	public final void unbind() {
		super.unbind();
		//unbind internal:
		alias1.unbind();
		alias2.unbind();
	}

	public final void adapt(FilterSetting from) {
		if(!(from instanceof AliasFilterSetting))
			return;
		AliasFilterSetting setting = (AliasFilterSetting) from;
		StringParameter fromAlias = setting.alias1;
		alias1 = new StringParameter(fromAlias.getName());
		alias1.setValue(fromAlias.getValue());
		
		fromAlias = setting.alias2;
		alias2 = new StringParameter(fromAlias.getName());
		alias2.setValue(fromAlias.getValue());
	}
}
