/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import org.palo.api.Element;
import org.palo.api.subsets.Subset2;
import org.palo.api.subsets.filter.AttributeFilter;

/**
 * <code>AttributeFilterSetting</code>
 * <p>
 * Manages the settings for the {@link AttributeFilter}. 
 * An {@link AttributeConstraintsMatrix} is used to filter out {@link Element}s. 
 * Therefore an element is accepted if it fulfills at least one row of this 
 * matrix.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: AttributeFilterSetting.java,v 1.9 2008/03/07 17:38:26 ArndHouben Exp $
 **/
public class AttributeFilterSetting extends AbstractFilterSettings {
	
	private ObjectParameter constraintsParam;
	
	/**
	 * Creates a new <code>AttributeFilterSetting</code> instance
	 */
	public AttributeFilterSetting() {
		this.constraintsParam = new ObjectParameter();
		this.constraintsParam.setValue(new AttributeConstraintsMatrix());
	}

	/**
	 * Adds all filter constraints from the given parameter to this settings.
	 * Note that the parameter value should be of type 
	 * {@link AttributeConstraintsMatrix}, otherwise calling this method has 
	 * no effect! 
	 * @param constraintParam the new filter constraints
	 */
	public final void setFilterConstraints(ObjectParameter constraintsParam) {		
		Object value = constraintsParam.getValue();
		if(value instanceof AttributeConstraintsMatrix) {
			copyFilterConstraints((AttributeConstraintsMatrix)value);			
		}
	}
	/**
	 * Returns the currently used filter constraint parameter.
	 * @return the filter constraint parameter
	 */
	public final ObjectParameter getFilterConstraints() {
		return constraintsParam;
	}
	
	public final boolean hasFilterConsraints() {
		AttributeConstraintsMatrix filterMatrix = 
			(AttributeConstraintsMatrix) constraintsParam.getValue();
		return filterMatrix.hasConstraints();
	}
	
	public void adapt(FilterSetting from) {
		if (!(from instanceof AttributeFilterSetting))
			return;
		AttributeFilterSetting setting = (AttributeFilterSetting) from;
		copyFilterConstraints((AttributeConstraintsMatrix) 
				setting.getFilterConstraints().getValue());
	}

	public final void bind(Subset2 subset) {
		super.bind(subset);
		//bind internal:
		constraintsParam.bind(subset);
		AttributeConstraintsMatrix filterMatrix = 
			(AttributeConstraintsMatrix)constraintsParam.getValue();
		if(filterMatrix != null)
			filterMatrix.bind(subset);
	}
	public final void unbind() {
		super.unbind();
		//unbind internal:
		constraintsParam.unbind();
		AttributeConstraintsMatrix filterMatrix = 
			(AttributeConstraintsMatrix)constraintsParam.getValue();
		if(filterMatrix != null)
			filterMatrix.unbind();
	}

	public void reset() {
		AttributeConstraintsMatrix filterMatrix = 
			(AttributeConstraintsMatrix)constraintsParam.getValue();
		filterMatrix.clear();
	}
	
	private final void copyFilterConstraints(
			AttributeConstraintsMatrix newConstraintMatrix) {
		AttributeConstraintsMatrix filterMatrix = 
				(AttributeConstraintsMatrix) constraintsParam.getValue();
		filterMatrix.clear(); // resets old matrix
		// add entries from new matrix:
		for (AttributeConstraint constraint : newConstraintMatrix
				.getConstraints()) {
			constraint.bind(subset);
			filterMatrix.addFilterConstraint(constraint);
		}
	}
}
