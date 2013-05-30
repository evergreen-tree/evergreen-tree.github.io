package com.tensegrity.palo.xmla;

import java.io.PrintWriter;
import java.util.StringTokenizer;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class XMLDocumentWriter 
{
	PrintWriter out; // the stream to send output to

	/** Initialize the output stream */
	public XMLDocumentWriter(PrintWriter out) 
	{
		this.out = out;
	}

	/** Close the output stream. */
	public void close() 
	{
		if (out != null) {
			out.close();
		}
	}

	/** Output a DOM Node (such as a Document) to the output stream */
	public String write(Node node) 
	{
		return write(node, "");
	}

	/**
	 * Output the specified DOM Node object, printing it using the specified
   	 * indentation string
   	 */
	public String write(Node node, String indent) 
	{	
		StringBuffer resultString = new StringBuffer(); 
		// The output depends on the type of the node
		switch (node.getNodeType()) 
		{
		case Node.DOCUMENT_NODE: 
		{ 
		    Document doc = (Document) node;
		    if (out != null) {
		    	out.println(indent + "<?xml version='1.0'?>"); // Output header
		    }
		    resultString.append(indent + "<?xml version='1.0'?>\n");
		    Node child = doc.getFirstChild(); // Get the first node
		    while (child != null) 
		    { 
		    	resultString.append(write(child, indent) + "\n"); // Output node
		    	child = child.getNextSibling(); // Get next node
		    }
		    break;
		}
		case Node.DOCUMENT_TYPE_NODE: 
		{ 
			DocumentType doctype = (DocumentType) node;
			if (out != null) {
				out.println("<!DOCTYPE " + doctype.getName() + ">");
			}
			resultString.append("<!DOCTYPE " + doctype.getName() + ">\n");
			break;
		}
		case Node.ELEMENT_NODE: 
		{ 
			Element elt = (Element) node;
			if (out != null) {
				out.print(indent + "<" + elt.getTagName()); // Begin start tag
			}
			resultString.append(indent + "<" + elt.getTagName());
			NamedNodeMap attrs = elt.getAttributes(); // Get attributes
			for (int i = 0; i < attrs.getLength(); i++) 
			{ 
				Node a = attrs.item(i);
				if (out != null) {
					out.print(" " + a.getNodeName() + "='" + // Print attr. name				
							fixup(a.getNodeValue()) + "'"); // Print attr. value
				}
				resultString.append(" " + a.getNodeName() + "='" + fixup(a.getNodeValue()) + "'");
			}
			if (out != null) {
				out.println(">"); // Finish start tag
			}
			resultString.append(">\n");
			String newindent = indent + "    "; // Increase indent
			Node child = elt.getFirstChild(); // Get child
			while (child != null) 
			{
				resultString.append(write(child, newindent) + "\n"); // Output child
				child = child.getNextSibling(); // Get next child
			}

			if (out != null) {
				out.println(indent + "</" + // Output end tag
						elt.getTagName() + ">");
			}
			resultString.append(indent + "</" + elt.getTagName() + ">\n");
			break;
		}
		case Node.TEXT_NODE: 
		{ 
			Text textNode = (Text) node;
			String text = textNode.getData().trim(); // Strip off space
			if ((text != null) && text.length() > 0) { // If non-empty
				if (out != null) {
					out.println(indent + fixup(text)); // print text
				}
				resultString.append(indent + fixup(text) + "\n");
			}
			break;
		}
		case Node.PROCESSING_INSTRUCTION_NODE: 
		{ 
			ProcessingInstruction pi = (ProcessingInstruction) node;
			if (out != null) {
				out.println(indent + "<?" + pi.getTarget() + " " + pi.getData() + "?>");
			}
			resultString.append(indent + "<?" + pi.getTarget() + " " + pi.getData() + "?>\n");
			break;
		}
		case Node.ENTITY_REFERENCE_NODE:  
			if (out != null) {
				out.println(indent + "&" + node.getNodeName() + ";");
			}
			resultString.append(indent + "&" + node.getNodeName() + ";\n");
			break;
		case Node.CDATA_SECTION_NODE: 
		{
			CDATASection cdata = (CDATASection) node;
			// Careful! Don't put a CDATA section in the program itself!
			if (out != null) {
				out.println(indent + "<" + "![CDATA[" + cdata.getData() + "]]" + ">");
			}
			resultString.append(indent + "<![CDATA[" + cdata.getData() + "]]>\n");
			break;
		}
		case Node.COMMENT_NODE: 
		{ 
			Comment c = (Comment) node;
			if (out != null) {
				out.println(indent + "<!--" + c.getData() + "-->");
			}
			resultString.append(indent + "<!--" + c.getData() + "-->\n");
			break;
		}
		default: // Hopefully, this won't happen too much!
			System.err.println("Ignoring node: " + node.getClass().getName());
			break;
		}
		return removeEmptyLines(resultString.toString());
	}
	
	private String removeEmptyLines(String lines) {
		StringTokenizer tok = new StringTokenizer(lines, "\n");
		StringBuffer result = new StringBuffer();
		while (tok.hasMoreTokens()) {
			String x = tok.nextToken();
			if (x.trim().length() != 0) {
				result.append(x + "\n");
			}
		}
		return result.toString();
	}

	// This method replaces reserved characters with entities.
	String fixup(String s) 
	{
		StringBuffer sb = new StringBuffer();
		int len = s.length();
		
		for (int i = 0; i < len; i++) 
		{
			char c = s.charAt(i);
			switch (c) 
			{
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&apos;");
				break;
			default:
				sb.append(c);
				break;
			}
    	}

    	return sb.toString();
  	}
}
