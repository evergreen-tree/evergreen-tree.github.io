/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <code></code>
 *
 * @author Stepan Rutz
 * @version $ID$
 */
public class BaseXMLHandler extends DefaultHandler
{
    private Map startHandlers;
    private Map endHandlers;
    private ArrayList stack;
    private boolean prefixPath;
    
    public BaseXMLHandler()
    {
        stack = new ArrayList();
        startHandlers = new HashMap();
        endHandlers = new HashMap();
        prefixPath = false;
    }
    
    public BaseXMLHandler(boolean prefixPath) {
    	this.prefixPath = prefixPath;
        stack = new ArrayList();
        startHandlers = new HashMap();
        endHandlers = new HashMap();    	
    }
    
    public void putStartHandler(String path, StartHandler handler)
    {
        startHandlers.put(path, handler);
    }
    
    public void putEndHandler(String path, EndHandler handler)
    {
        endHandlers.put(path, handler);
    }
    
    private String getPath()
    {
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < stack.size(); ++i)
        {
            bf.append(stack.get(i));
            if (i < stack.size() - 1)
                bf.append("/"); //$NON-NLS-1$
        }
        return bf.toString();
    }
    
    private String getLastPathElement() {
    	String lastElement = (String) stack.get(stack.size() - 1);    	
    	return lastElement;
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        super.startElement(uri, localName, qName, attributes);
        stack.add(qName);
        
        StartHandler starthandler;
        if (prefixPath) {
        	if ((starthandler = (StartHandler) startHandlers.get(getLastPathElement())) != null)
        		starthandler.startElement(uri, localName, qName, attributes);        	
        } else {
        	if ((starthandler = (StartHandler) startHandlers.get(getPath())) != null)
        		starthandler.startElement(uri, localName, qName, attributes);
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        super.endElement(uri, localName, qName);
        EndHandler endhandler;
        if (prefixPath) {
        	if ((endhandler = (EndHandler) endHandlers.get(getLastPathElement())) != null)
        		endhandler.endElement(uri, localName, qName);        	
        } else {
        	if ((endhandler = (EndHandler) endHandlers.get(getPath())) != null)
        		endhandler.endElement(uri, localName, qName);
        }
        
        if (!stack.isEmpty())
            stack.remove(stack.size() - 1);
    }
    
    protected final void clearAllHandlers() {
		endHandlers.clear();
		startHandlers.clear();		
	}
}