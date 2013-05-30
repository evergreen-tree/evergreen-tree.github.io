package com.tensegrity.palo.xmla;


public class XMLAExecuteProperties extends XMLAProperties
{
	private String axisFormat;
	
	public XMLAExecuteProperties() 
	{
		// defaults:
		axisFormat = "TupleFormat";
		setFormat("Multidimensional"); 
		setContent("Data");
	}
	
	public String getAxisFormat() 
	{
		return axisFormat;
	}
	
	public void setAxisFormat(String s) 
	{
		axisFormat = s;
	}
	
	protected String getPropertyListXML(String indent){
		/*
		 <PropertyList>
		 <DataSourceInfo>Provider=Mondrian;Jdbc=jdbc:odbc:MondrianFoodMart;Catalog=file:/C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/mondrian/WEB-INF/queries/FoodMart.xml;JdbcDrivers=sun.jdbc.odbc.JdbcOdbcDriver;</DataSourceInfo>
		 <Catalog>FoodMart</Catalog>
		 <Format>Multidimensional</Format>
		 <AxisFormat>TupleFormat</AxisFormat>
		 </PropertyList>
		 
		 <PropertyList xsi:type="ns1:clsXMLAProx.Execute.PropertyList">
		 <ns1:DataSourceInfo xsi:type="xsd:string">Local Analysis Server</ns1:DataSourceInfo>
		 <ns1:Catalog xsi:type="xsd:string">FoodMart 2000</ns1:Catalog>
		 <ns1:Format xsi:type="xsd:string">Native</ns1:Format>
		 <ns1:Content xsi:type="xsd:string">Data</ns1:Content>
		 <ns1:AxisFormat xsi:type="xsd:string">TupleFormat</ns1:AxisFormat>
		 </PropertyList>
		 
		 
		 */
		StringBuffer sb = new StringBuffer("\n" + indent + "<PropertyList>");
		
		if ( getDataSourceInfo()!= null && getDataSourceInfo().trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<DataSourceInfo>" + getDataSourceInfo().trim() + "</DataSourceInfo>");
		
		if ( getCatalog() != null && getCatalog().trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<Catalog>" + getCatalog().trim() + "</Catalog>");
		
		if ( getFormat() != null && getFormat().trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<Format>" + getFormat().trim() + "</Format>");
		
		if ( getContent() != null && getContent().trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<Content>" + getContent().trim() + "</Content>");
		
		if ( axisFormat!= null && axisFormat.trim().length()>0)
			sb.append("\n" + indent + elementIndent + "<AxisFormat>" + axisFormat.trim() + "</AxisFormat>");
		
		sb.append("\n" + indent + "</PropertyList>");
		return sb.toString();
	}
}
