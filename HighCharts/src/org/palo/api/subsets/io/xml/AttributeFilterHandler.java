/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.io.xml;

import org.palo.api.Dimension;
import org.palo.api.impl.xml.XMLUtil;
import org.palo.api.subsets.SubsetFilter;
import org.palo.api.subsets.filter.AttributeFilter;
import org.palo.api.subsets.filter.settings.AttributeConstraint;
import org.palo.api.subsets.filter.settings.AttributeConstraintsMatrix;
import org.palo.api.subsets.filter.settings.AttributeFilterSetting;
import org.palo.api.subsets.filter.settings.ObjectParameter;

/**
 * <code>AttributeFilterHandler</code>
 * <p>
 * API internal implementation of the {@link SubsetFilterHandler} interface 
 * which handles {@link AttributeFilter}s.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: AttributeFilterHandler.java,v 1.14 2008/05/08 10:12:20 ArndHouben Exp $
 **/
class AttributeFilterHandler extends AbstractSubsetFilterHandler {
	
	public static final String XPATH = "/subset/attribute_filter";

	private static final String FILTER_PARAMETER =
		"/subset/attribute_filter/attribute_filters/parameter";
//	private static final String FILTER_VALUE_COL =
//		"/subset/attribute_filter/attribute_filters/value/filter_col";
	private static final String FILTER_VALUE_COL_ATTRIBUTE = 
		"/subset/attribute_filter/attribute_filters/value/filter_col/attribute";
	private static final String FILTER_VALUE_COL_ENTRY =
		"/subset/attribute_filter/attribute_filters/value/filter_col/col_entry";

	
	private final AttributeFilterSetting setting;
//	private boolean newFilterColumn;
	private String attrId;
	
	public AttributeFilterHandler() {
		setting = new AttributeFilterSetting();
	}
	
	public final String getXPath() {
		return XPATH;
	}
	
	public final void enter(String path) {
//		if(path.equals(FILTER_VALUE_COL))
//			newFilterColumn = true;
	}

	public final void leave(String path, String value) {
		value = XMLUtil.dequote(value);
		if(path.equals(FILTER_PARAMETER)) {
			ObjectParameter oldFilters = setting.getFilterConstraints();
			ObjectParameter newFilters = new ObjectParameter(value);
			newFilters.setValue(oldFilters.getValue());
			setting.setFilterConstraints(newFilters);
		}
		else if (path.equals(FILTER_VALUE_COL_ATTRIBUTE)) {
			attrId = value;
		}
		else if (path.equals(FILTER_VALUE_COL_ENTRY)) {
			AttributeConstraint constraint = parseEntry(attrId, value);
			//PR 6901 check if we have empty value and equal operator
			if(constraint.getValue().equals("") && 
					constraint.getOperator().equals(AttributeConstraint.EQUAL))
				constraint.setOperator(AttributeConstraint.NONE);
			AttributeConstraintsMatrix constraintMatrix = (AttributeConstraintsMatrix) setting
					.getFilterConstraints().getValue();
			constraintMatrix.addFilterConstraint(constraint);
		}
	}
	
	public final SubsetFilter createFilter(Dimension dimension) {
		return new AttributeFilter(dimension, setting);
	}

	public static final String getPersistenceString(AttributeFilter filter) {
		StringBuffer str = new StringBuffer();
		AttributeFilterSetting setting = filter.getSettings();
		if (setting.hasFilterConsraints()) {
			str.append("<attribute_filter>\r\n<attribute_filters>\r\n");
			// write each filter column:
			ObjectParameter filterConstraints = setting.getFilterConstraints();
			ParameterHandler.addParameter(filterConstraints, str);
			AttributeConstraintsMatrix filterMatrix = 
				(AttributeConstraintsMatrix) filterConstraints.getValue();
			str.append("<value>\r\n");
			String[] attrIds = filterMatrix.getAttributeIDs();
			for (String attrId : attrIds) {
				AttributeConstraint[] columns = filterMatrix.getColumn(attrId);
				writeFilterColumn(str, attrId, columns);
			}
			// Attribute[] attributes = filter.getDimension().getAttributes();
			// for (Attribute attribute : attributes) {
			// AttributeConstraint[] columns =
			// filterMatrix.getColumn(attribute.getId());
			// writeFilterColumn(str, attribute.getId(), columns);
			// }

			str.append("</value>\r\n");
			str.append("</attribute_filters>\r\n</attribute_filter>\r\n");
		}
		return str.toString();
	}
	
	private static final void writeFilterColumn(StringBuffer buf, String attrId, AttributeConstraint[] constraints) {
		buf.append("<filter_col>\r\n");
		//first entry must be the attribute id!
		buf.append("<attribute>");
		buf.append(attrId);
		buf.append("</attribute>\r\n");
		for(AttributeConstraint entry : constraints)
			writeColumnEntry(buf, entry);
		buf.append("</filter_col>\r\n");
	}

	private static final void writeColumnEntry(StringBuffer buf, String entry) {
		buf.append("<col_entry>");
		buf.append(XMLUtil.quote(entry));
		buf.append("</col_entry>\r\n");
	}
	
	private static final void writeColumnEntry(StringBuffer buf,
			AttributeConstraint entry) {
		String operator = entry.getOperator();
		String value = entry.getValue();
		if(value == null) value = "";
		//PR 6901: don't save value if operator is NONE
		if(operator.equals(AttributeConstraint.NONE))
			value = "";
		else if(!operator.equals("="))
			value = XMLUtil.printQuoted(operator)+value;
		writeColumnEntry(buf, value);
	}

	/**
	 * Parses the given entry string
	 * @param attrId the attribute identifier
	 * @param entry the textual attribute constraint representation
	 * @return a new <code>AttributeFilterConstraint</code> instance 
	 */
	private final AttributeConstraint parseEntry(String attrId, String entry) {
		AttributeConstraint constraint = 
			new AttributeConstraint(attrId);
		
		parseInternal(entry, constraint);
		return constraint;
	}
	
	private final void parseInternal(String entry,
			AttributeConstraint constraint) {
		entry = entry.trim();
		int length = entry.length();
		int cutIndex = Math.min(length,2)+1; //operators are at most 2 characters long
		String op = null;
		do {
			cutIndex--;
			op = entry.substring(0,cutIndex);
		} while(cutIndex > 0 && !constraint.isOperator(op));
		if(cutIndex <= 0)
			op = AttributeConstraint.EQUAL;
		constraint.setOperator(op);
		constraint.setValue(entry.substring(cutIndex).trim());
	}

}
