/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api.impl;

import java.util.ArrayList;

import org.palo.api.Condition;

import com.tensegrity.palojava.PaloException;

/**
 * <code>ConditionImpl</code>
 * Implementation of the {@link Condition} interface
 * @author ArndHouben
 * @version $Id: ConditionImpl.java,v 1.3 2007/10/19 09:47:23 ArndHouben Exp $
 */
class ConditionImpl implements Condition {

	private static final ArrayList<String> ALLOWED_CONDITIONS = 
		new ArrayList<String>(6);
	static {
			ALLOWED_CONDITIONS.add(Condition.EQ);
			ALLOWED_CONDITIONS.add(Condition.GT);
			ALLOWED_CONDITIONS.add(Condition.GTE);
			ALLOWED_CONDITIONS.add(Condition.LT);
			ALLOWED_CONDITIONS.add(Condition.LTE);
			ALLOWED_CONDITIONS.add(Condition.NEQ); 
	};
	
	public static final Condition getCondition(String condition) {
		if(isValid(condition))
			return new ConditionImpl(condition);
		else
			throw new PaloException("Unkown condition: \""+condition+"\"");
	}

	private String value;
	private final String condition;
	
	private ConditionImpl(String condition) {
		this.condition = condition;
	}
	
	public final synchronized String getValue() {
		return value;
	}

	public final synchronized void setValue(double value) {
		setValue(Double.toString(value));
	}

	public final void setValue(String value) {
		this.value = value;
	}
	
	public final String toString() {
		StringBuffer str = new StringBuffer();
		str.append(condition);
		str.append(value);
		return str.toString();
	}

	private static final boolean isValid(String condition) {
		return ALLOWED_CONDITIONS.contains(condition);
	}
}
