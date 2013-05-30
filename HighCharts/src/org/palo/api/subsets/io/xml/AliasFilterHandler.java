package org.palo.api.subsets.io.xml;

import org.palo.api.Dimension;
import org.palo.api.impl.xml.XMLUtil;
import org.palo.api.subsets.SubsetFilter;
import org.palo.api.subsets.filter.AliasFilter;
import org.palo.api.subsets.filter.settings.AliasFilterSetting;
import org.palo.api.subsets.filter.settings.StringParameter;

class AliasFilterHandler extends AbstractSubsetFilterHandler {
	
	public static final String XPATH = "/subset/alias_filter";
	
	private static final String ALIAS1_PARAMETER = "/subset/alias_filter/alias1/parameter";
	private static final String ALIAS1_VALUE = "/subset/alias_filter/alias1/value";
	private static final String ALIAS2_PARAMETER = "/subset/alias_filter/alias2/parameter";
	private static final String ALIAS2_VALUE = "/subset/alias_filter/alias2/value";

	private final AliasFilterSetting setting;
	
	public AliasFilterHandler() {
		setting = new AliasFilterSetting();
	}
	
	public final String getXPath() {
		return XPATH;
	}
	
	public final void enter(String path) {
	}

	public final void leave(String path, String value) {
		value = XMLUtil.dequote(value);
		if(path.equals(ALIAS1_PARAMETER)) {
			StringParameter alias1 = setting.getAlias(1);
			StringParameter alias = new StringParameter(value);
			alias.setValue(alias1.getValue());
			setting.setAlias(1, alias);
		} 
		else if(path.equals(ALIAS1_VALUE)) {
			StringParameter alias1 = setting.getAlias(1);
			alias1.setValue(value);
		}
		if(path.equals(ALIAS2_PARAMETER)) {
			StringParameter alias2 = setting.getAlias(2);
			StringParameter alias = new StringParameter(value);
			alias.setValue(alias2.getValue());
			setting.setAlias(2, alias);
		} 
		else if(path.equals(ALIAS2_VALUE)) {
			StringParameter alias2 = setting.getAlias(2);
			alias2.setValue(value);
		}
	}
	
	public final SubsetFilter createFilter(Dimension dimension) {
		return new AliasFilter(dimension, setting);
	}

	public static final String getPersistenceString(AliasFilter filter) {
		AliasFilterSetting setting = filter.getSettings();
		StringBuffer str = new StringBuffer();
		str.append("<alias_filter>\r\n");
		//first alias exisisting?
		StringParameter alias = setting.getAlias(1);
		String aliasId = alias.getValue();
		if(aliasId != null && !aliasId.equals("")) {
			//store it
			str.append("<alias1>\r\n");
			str.append(ParameterHandler.getXML(alias));
			str.append("</alias1>\r\n");
		}
		//check second alias:		
		alias = setting.getAlias(2);
		aliasId = alias.getValue();
		if(aliasId != null && !aliasId.equals("")) {
			str.append("<alias2>\r\n");
			str.append(ParameterHandler.getXML(alias));
			str.append("</alias2>\r\n");
		}
		str.append("</alias_filter>\r\n");
		return str.toString();
	}
}
