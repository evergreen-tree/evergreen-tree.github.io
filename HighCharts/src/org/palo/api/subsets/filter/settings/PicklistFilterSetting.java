/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.palo.api.Element;
import org.palo.api.subsets.Subset2;
import org.palo.api.subsets.filter.PicklistFilter;


/**
 * <code>PicklistFilterSetting</code>
 * <p>
 * Manages the settings for the {@link PicklistFilter}.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: PicklistFilterSetting.java,v 1.7 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class PicklistFilterSetting extends AbstractFilterSettings {
	
	public static final int INSERT_MODE_SUB = 3; //1 << 0;
	public static final int INSERT_MODE_BACK = 1; //1 << 1;
	public static final int INSERT_MODE_FRONT = 0; //1 << 2;
	public static final int INSERT_MODE_MERGE = 2; //1 << 3;

	private IntegerParameter insertMode;
	private ObjectParameter selection;
	
	public PicklistFilterSetting() {
		insertMode = new IntegerParameter(null);
		insertMode.setValue(INSERT_MODE_FRONT);
		selection = new ObjectParameter();
		selection.setValue(new LinkedHashSet<String>());
	}
	
	/**
	 * Adds the given element id to the picked element list
	 * @param id a valid element id
	 */
	public final void addElement(String id) {
		LinkedHashSet<String> elements = (LinkedHashSet<String>)selection.getValue();
		elements.add(id);
		markDirty();
	}
	
	/**
	 * Removes the given element id from the picked element list
	 * @param id a valid element id
	 */
	public final void removeElement(String id) {
		LinkedHashSet<String> elements = (LinkedHashSet<String>)selection.getValue();
		elements.remove(id);
		markDirty();
	}
	/**
	 * Removes all picked elements.
	 */
	public final void removeAllElements() {
		LinkedHashSet<String> elements = (LinkedHashSet<String>)selection.getValue();
		elements.clear();
		markDirty();
	}
	
	/**
	 * Returns the selection parameter of picked elements.
	 * @return the selection parameter 
	 */
	public final ObjectParameter getSelection() {
		return selection;
	}
	
	/**
	 * Sets the selection parameter. Note that in order to take effect the 
	 * parameter value should be of type {@link LinkedHashSet} 
	 * (to keep adding order). The <code>HashSet</code> 
	 * should contain the {@link Element} ids of the picked elements. 
	 * @param selection
	 */
	public final void setSelection(ObjectParameter selection) {
//		this.selection = selection;
		Object value = selection.getValue();
		if(value instanceof HashSet)
			copySelection((HashSet<String>)value);
		getSelection().bind(subset);
	}
	
	/**
	 * Returns the insert mode to use
	 * @return the insert mode, i.e. one of the predefined insert mode constants
	 */
	public IntegerParameter getInsertMode() {
		return insertMode;
	}
	/**
	 * Sets the insert mode. One of the predefined insert mode constants should
	 * be used.
	 * @param insertMode the new insert mode.
	 */
	public final void setInsertMode(int insertMode) {
		this.insertMode.setValue(insertMode);		
	}
	/**
	 * Sets the insert mode, i.e. the parameter value should be one of the 
	 * predefined insert mode constants.
	 * @param insertMode the new <code>IntegerParameter</code> to use for the
	 * insert mode
	 */
	public final void setInsertMode(IntegerParameter insertMode) {
		this.insertMode = insertMode;
		this.insertMode.bind(subset);
	}

	public final void reset() {
		removeAllElements();
		insertMode.setValue(INSERT_MODE_MERGE);
	}

	public final void bind(Subset2 subset) {
		super.bind(subset);
		//bind internal:
		insertMode.bind(subset);
		selection.bind(subset);
	}
	public final void unbind() {
		super.unbind();
		//unbind internal:
		insertMode.unbind();
		selection.unbind();
	}

	public final void adapt(FilterSetting from) {
		if(!(from instanceof PicklistFilterSetting))
			return;
		PicklistFilterSetting setting = (PicklistFilterSetting) from;
		reset();

		setInsertMode(setting.getInsertMode().getValue());
		HashSet<String> newSelection = 
			(HashSet<String>) setting.getSelection().getValue();
		copySelection(newSelection);
//		setSelection(setting.getSelection());
	}
	
	private final void copySelection(HashSet<String> newSelection) {
		HashSet<String> _selection = (HashSet<String>) selection.getValue();
		_selection.clear();
		_selection.addAll(newSelection);
		markDirty();
	}

}