package com.tensegrity.palo.xmla;

public class XMLAProperties
{
	public static String elementIndent = "  ";
	private String dataSourceInfo;
	private String catalog;
	private String format;
	private String content;
	private int    compatibility;
	private int    beginRange;
	private int    endRange;
	
	public XMLAProperties() 
	{
		compatibility = 0;
		beginRange = endRange = -1;
		setFormat("Tabular");
		setContent("SchemaData");
	}
	
	public String getDataSourceInfo() 
	{
		return dataSourceInfo;
	}
	
	public void setDataSourceInfo(String s) 
	{
		dataSourceInfo = s;
	}
	
	public String getCatalog() 
	{
		return catalog;
	}
	
	public void setCatalog(String s) 
	{
		catalog = s;
	}
	
	public int getBeginRange()
	{
		return beginRange;
	}
	
	public void setBeginRange(int br)
	{
		beginRange = br;
	}
	
	public int getEndRange()
	{
		return endRange;
	}
	
	public void setEndRange(int er)
	{
		endRange = er;
	}
	
	public String getFormat() 
	{
		return format;
	}
	
	public void setFormat(String s) 
	{
		format = s;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public void setContent(String s) 
	{
		content = s;
	}
	
	public void setCompatibility(int i) {
		compatibility = i;
	}
	
	public int getCompatibility() {
		return compatibility;
	}
	
	protected String getPropertyListXML(String indent)
	{
		/*
		 <Properties>
		 <PropertyList>
		 <ns1:DataSourceInfo>Local Analysis Server</ns1:DataSourceInfo>
		 <ns1:Catalog>FoodMart 2000</ns1:Catalog>
		 <ns1:BeginRange>0</ns1:BeginRange>
		 <ns1:EndRange>0</ns1:EndRange>
		 <Format>Tabular</Format>
		 <Content>SchemaData</Content>
		 </PropertyList>
		 */
		StringBuffer sb = new StringBuffer("\n" + indent + "<PropertyList>");
		
		if ( dataSourceInfo!= null && dataSourceInfo.trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<DataSourceInfo>" + dataSourceInfo.trim() + "</DataSourceInfo>");
		
		if (compatibility > 0) 
			sb.append("\n" + indent + elementIndent + "<DbpropMsmdMDXCompatibility>" + compatibility + "</DbpropMsmdMDXCompatibility>");
		
		if ( catalog!= null && catalog.trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<Catalog>" + catalog.trim() + "</Catalog>");
		
		if ( format!= null && format.trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<Format>" + format.trim() + "</Format>");
		
		if ( content!= null && content.trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<Content>" + content.trim() + "</Content>");
		
		if ( beginRange >= 0)
			sb.append("\n" + indent + elementIndent + "<BeginRange>" + beginRange + "</BeginRange>");
		
		if ( endRange >= 0)
			sb.append("\n" + indent + elementIndent + "<EndRange>" + endRange + "</EndRange>");
		
		sb.append("\n" + indent + "</PropertyList>");
		return sb.toString();
		
	}
	
	public String getXML(String indent)
	{
		/*
		 <Properties>
		 <PropertyList>
		 <ns1:DataSourceInfo>Local Analysis Server</ns1:DataSourceInfo>
		 <ns1:Catalog>FoodMart 2000</ns1:Catalog>
		 <ns1:BeginRange>0</ns1:BeginRange>
		 <ns1:EndRange>0</ns1:EndRange>
		 <Format>Tabular</Format>
		 <Content>SchemaData</Content>
		 </PropertyList>
		 
		 */
		return "\n" + indent + "<Properties>"
		+ getPropertyListXML(indent + elementIndent)
		+ "\n" + indent + "</Properties>";
		
	}
}
