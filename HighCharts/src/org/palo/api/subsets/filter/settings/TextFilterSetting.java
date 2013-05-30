/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import java.util.HashSet;

import org.palo.api.subsets.Subset2;
import org.palo.api.subsets.filter.TextFilter;

/**
 * <code>TextFilterSetting</code>
 * <p>
 * Manages the settings for the {@link TextFilter}.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: TextFilterSetting.java,v 1.5 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class TextFilterSetting extends AbstractFilterSettings {
	
	private ObjectParameter expressions;
	private BooleanParameter extended = new BooleanParameter();

	/**
	 * Creates a new <code>TextFilterSetting</code> instance.
	 */
	public TextFilterSetting() {
		expressions = new ObjectParameter();
		extended.setValue(true);
		expressions.setValue(new HashSet<String>());
	}
	
	/**
	 * Adds the given expression to the list of all expressions used for textual
	 * filter
	 * @param expr the expression to add
	 */
	public final void addExpression(String expr) {
		HashSet<String> _expressions = (HashSet<String>) expressions.getValue();
		_expressions.add(expr);
		markDirty();
	}
	/**
	 * Removes the given expression from the list of all expression used for 
	 * textual filter
	 * @param expr the expression to remove
	 */
	public final void removeExpression(String expr) {
		HashSet<String> _expressions = (HashSet<String>) expressions.getValue();
		_expressions.remove(expr);
		markDirty();
	}
	/**
	 * Sets the new expression parameter. Note that the parameter value should
	 * be of type {@link HashSet}, otherwise calling this method has no effect.
	 * The <code>HashSet</code> should contain the <code>String</code>
	 * expressions to use for filtering. 
	 * @param expressions the expression parameter
	 */
	public final void setExpressions(ObjectParameter expressions) {
		Object value = expressions.getValue();
		if(value instanceof HashSet)
			copyExpressions((HashSet<String>)value); //this.expressions = expressions;
		getExpressions().bind(subset);		
	}
	
	/**
	 * Returns the expression parameter which contains all added expressions.
	 * @return the expression parameter
	 */
	public final ObjectParameter getExpressions() {
		return expressions;
	}

	/**
	 * Checks if the expressions should be handled as regular expressions.
	 * @return <code>true</code> if the expressions should be handled as
	 * regulars expressions, <code>false</code> otherwise
	 */
	public final BooleanParameter getExtended() {
		return extended;
	}

	/**
	 * Determines if the expressions should be handled as regular expressions.
	 * @param extended specify <code>true</code> if the expressions should be
	 * handled as regular expressions, <code>false</code> otherwise
	 */
	public final void setExtended(boolean extended) {
		this.extended.setValue(extended);
	}
	/**
	 * Sets the extended flag. 
	 * @param extended the new <code>BooleanParameter</code> to use for the
	 * extended flag
	 */
	public final void setExtended(BooleanParameter extended) {
		this.extended = extended;
		extended.bind(subset);
	}

	
	public final void reset() {
		extended.setValue(false);		
		HashSet<String> _expressions = (HashSet<String>) expressions.getValue();
		_expressions.clear();
		markDirty();
	}

	public final void adapt(FilterSetting from) {
		if(!(from instanceof TextFilterSetting))
			return;
		TextFilterSetting setting = (TextFilterSetting) from;
		//now we take all expressions from from ;)
		HashSet<String> newExpressions =
			(HashSet<String>) setting.getExpressions().getValue();
		copyExpressions(newExpressions);
		setExtended(setting.getExtended().getValue());
	}
	
	public final void bind(Subset2 subset) {
		super.bind(subset);
		//bind internal:
		expressions.bind(subset);
		extended.bind(subset);
	}
	public final void unbind() {
		super.unbind();
		//unbind internal:
		expressions.unbind();
		extended.unbind();
	}

	private final void copyExpressions(HashSet<String> newExpressions) {
		HashSet<String> _expressions = (HashSet<String>) expressions.getValue();
		_expressions.clear();
		_expressions.addAll(newExpressions);
		markDirty();
	}
	
}