/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.ui.table.impl;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.palo.api.ext.ui.table.TableFormat;
import org.palo.api.impl.xml.BaseXMLHandler;
import org.palo.api.impl.xml.StartHandler;
import org.xml.sax.Attributes;


/**
 * <code>TableFormatReader</code>
 * Reads in and creates a new <code>{@link TableFormat}</code> instance from
 * an xml definition.
 *
 * @author ArndHouben
 * @version $Id: TableFormatReader.java,v 1.1 2007/06/29 15:22:33 ArndHouben Exp $
 **/
class TableFormatReader {
	
	//--------------------------------------------------------------------------
	// FACTORY 
	//
	private static final TableFormatReader instance = new TableFormatReader();
	static final TableFormatReader getInstance() {
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private TableFormatReader() {
	}
	
	/**
	 * Creates a new <code>{@link TableFormat}</code> instance from specified
	 * xml definition.
	 * @param xmlStr xml definition of a valid <code>{@link TableFormat}</code>
	 * @return new <code>{@link TableFormat}</code> instance
	 */
	final TableFormat fromXML(String xmlStr) {
		if(xmlStr == null)
			return null;
		FormatXMLHandler defaultHandler = new FormatXMLHandler();
		SAXParserFactory sF = SAXParserFactory.newInstance();
//		sF.setValidating(true);
		SAXParser parser = null;
		
		try {
			ByteArrayInputStream bin = new ByteArrayInputStream(xmlStr.getBytes("UTF-8")); //$NON-NLS-1$
			parser = sF.newSAXParser();
			parser.parse(bin, defaultHandler);
			return defaultHandler.getFormatter();
		} catch (Exception e) {
//			System.err.println("failed to parse tableformatter xml string");
		}

		return null;
	}

	class FormatXMLHandler extends BaseXMLHandler {
		private final FormatBuilder cellFmtBuilder;
		private final FormatBuilder headerFmtBuilder;
		
		FormatXMLHandler() {
			cellFmtBuilder = new FormatBuilder();
			headerFmtBuilder = new FormatBuilder();
			
			putStartHandler("format/priority", new StartHandler() {
				public void startElement(String uri, String localName,
						String qName, Attributes attributes) {
					//TODO we have to move priority to cell/header format!!					
					String prio = attributes.getValue("level");
//					prio = (_prio != null && _prio.length() > 0) ? Integer
//							.parseInt(_prio) : 1;
					cellFmtBuilder.setPriority(prio);
					headerFmtBuilder.setPriority(prio);
				}
			});
			putStartHandler("format/formatcells/numberformat",
					new StartHandler() {
						public void startElement(String uri, String localName,
								String qName, Attributes attributes) {
							String str = attributes.getValue("template");
							cellFmtBuilder.setNumberFormatTemplate(str);
						}
					});
			putStartHandler("format/formatcells/background",
					new StartHandler() {
						public void startElement(String uri, String localName,
								String qName, Attributes attributes) {
							setBackground(cellFmtBuilder, attributes);
						}
					});
			putStartHandler("format/formatheader/background",
					new StartHandler() {
						public void startElement(String uri, String localName,
								String qName, Attributes attributes) {
							setBackground(headerFmtBuilder, attributes);
						}
					});
			putStartHandler("format/formatcells/font", new StartHandler() {
				public void startElement(String uri, String localName,
						String qName, Attributes attributes) {
					setFont(cellFmtBuilder, attributes);
				}
			});
			putStartHandler("format/formatheader/font", new StartHandler() {
				public void startElement(String uri, String localName,
						String qName, Attributes attributes) {
					setFont(headerFmtBuilder, attributes);
				}
			});
		}

		private void setBackground(FormatBuilder fmtBuilder,
				Attributes attributes) {
			fmtBuilder.setBackGroundColor(attributes.getValue("r"), attributes
					.getValue("g"), attributes.getValue("b"));
		}

		private void setFont(FormatBuilder fmtBuilder, Attributes attributes) {
			fmtBuilder.setFontName(attributes.getValue("name"));
			fmtBuilder.setFontSize(attributes.getValue("size"));
			fmtBuilder.setBold(attributes.getValue("bold"));
			fmtBuilder.setItalic(attributes.getValue("italic"));
			fmtBuilder.setUnderlined(attributes.getValue("underline"));
			fmtBuilder.setFontColor(attributes.getValue("r"), attributes
					.getValue("g"), attributes.getValue("b"));
		}
		
		private final TableFormat getFormatter() {
			DefaultTableFormat tblFormatter = new DefaultTableFormat();
//			tblFormatter.setPriority(prio);
			tblFormatter.setCellFormat(cellFmtBuilder.create());
			tblFormatter.setHeaderFormat(headerFmtBuilder.create());
			return tblFormatter;
		}
	}

}
