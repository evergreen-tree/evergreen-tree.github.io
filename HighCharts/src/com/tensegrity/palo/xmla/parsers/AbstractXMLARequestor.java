package com.tensegrity.palo.xmla.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tensegrity.palo.xmla.XMLAClient;

public abstract class AbstractXMLARequestor {
	private final LinkedHashSet <String> activatedItems = 
		new LinkedHashSet <String>();

	public void deactivateItem(String itemName) {
		activatedItems.remove(itemName);
	}
	
	public void activateItem(String itemName) {
		activatedItems.add(itemName);
	}
	
	public boolean isItemActive(String itemName) {
		return activatedItems.contains(itemName);
	}
	
	protected void parseXMLANodeList(NodeList parentNodeList, 
			String connectionName, XMLAClient xmlaClient) {
		LinkedHashMap <String, String> resultMap = 
			new LinkedHashMap<String, String>();				
		for (int i = 0, n = parentNodeList.getLength(); i < n; i++) {
			NodeList nlRow = parentNodeList.item(i).getChildNodes();
			for (int j = 0; j < nlRow.getLength(); j++) {
				Node itemNode = nlRow.item(j);
				if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
					for (String item: activatedItems) {
						if (itemNode.getNodeName().equals(item))  {	
							resultMap.put(item, 
								XMLAClient.getTextFromDOMElement(itemNode));
						}
					} 
				}
			}
			parseResult(resultMap, connectionName, xmlaClient);
			resultMap.clear();
		}
	}
	
	protected abstract void parseResult(HashMap <String, String> result,
			String connectionName, XMLAClient xmlaClient);
}
