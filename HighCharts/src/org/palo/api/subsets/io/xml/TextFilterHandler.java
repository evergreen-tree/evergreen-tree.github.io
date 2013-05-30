/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.io.xml;

import java.util.HashSet;

import org.palo.api.Dimension;
import org.palo.api.impl.xml.XMLUtil;
import org.palo.api.subsets.SubsetFilter;
import org.palo.api.subsets.filter.TextFilter;
import org.palo.api.subsets.filter.settings.BooleanParameter;
import org.palo.api.subsets.filter.settings.ObjectParameter;
import org.palo.api.subsets.filter.settings.TextFilterSetting;

/**
 * <code>TextFilterHandler</code>
 * <p>
 * API internal implementation of the {@link SubsetFilterHandler} interface 
 * which handles {@link TextFilter}s.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: TextFilterHandler.java,v 1.7 2008/05/08 10:12:20 ArndHouben Exp $
 **/
class TextFilterHandler extends AbstractSubsetFilterHandler {
	
	//the xpaths:
	public static final String ELEMENT_ID = "text_filter";
	public static final String XPATH = "/subset/text_filter";
	private static final String EXTENDED_VALUE = "/subset/text_filter/extended/value";
	private static final String EXTENDED_PARAMETER = "/subset/text_filter/extended/parameter";	
	private static final String EXPRESSION_VALUE = "/subset/text_filter/regexes/value/expression";
	private static final String EXPRESSION_PARAMETER = "/subset/text_filter/regexes/parameter";
	
	private final TextFilterSetting tfInfo;
	
	public TextFilterHandler() {
		this.tfInfo = new TextFilterSetting();
	}
	
	public final String getXPath() {
		return XPATH;
	}

	public final void enter(String path) {
//		if(path.equals(EXTENDED_PATH_VALUE))
//			tfInfo.setExtended(Boolean.parseBoolean(path));
//		else if(path.equals(EXTENDED_PATH_PARAM))
//			tfInfo.setExtended(new BooleanParameter(path));
	}
	public final void leave(String path, String value) {
		if(path.equals(EXPRESSION_PARAMETER)) {
			ObjectParameter oldExpr = tfInfo.getExpressions();
			ObjectParameter newExpr = new ObjectParameter(value);
			newExpr.setValue(oldExpr.getValue());
			tfInfo.setExpressions(newExpr);
		}
		else if (path.equals(EXPRESSION_VALUE))
			tfInfo.addExpression(XMLUtil.dequote(value));
		else if (path.equals(EXTENDED_VALUE))
			tfInfo.setExtended(Boolean.parseBoolean(value));
		else if (path.equals(EXTENDED_PARAMETER)) {
			BooleanParameter oldParam = tfInfo.getExtended();
			tfInfo.setExtended(new BooleanParameter(value));
			tfInfo.setExtended(oldParam.getValue());
		}
	}

	public SubsetFilter createFilter(Dimension dimension) {
		return new TextFilter(dimension, tfInfo);
	}
	
	public static final String getPersistenceString(TextFilter filter) {		
		TextFilterSetting tfInfo = filter.getSettings();
		ObjectParameter exprParam = tfInfo.getExpressions();
		HashSet<String> expressions = (HashSet<String>) exprParam.getValue();
		if(expressions.isEmpty())
			return null; //nothing to do...
		
		StringBuffer str = new StringBuffer();
		str.append("<text_filter>\r\n");
		str.append("<regexes>\r\n");
		ParameterHandler.addParameter(exprParam, str);
		str.append("<value>\r\n");
		for (String expr : expressions) { 
			expr = expr.length() > 0 ? expr : ".*";
			str.append("<expression>" +XMLUtil.quote(expr) + "</expression>\r\n");
		}
		str.append("</value>\r\n");
		str.append("</regexes>\r\n");
		str.append("<extended>\r\n");
		str.append(ParameterHandler.getXML(tfInfo.getExtended()));
		str.append("</extended>\r\n");
		str.append("</text_filter>\r\n");
		return str.toString();
	}
}
