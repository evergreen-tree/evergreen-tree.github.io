/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api.impl.xml;


/**
 * <code>EndHandler</code>
 *
 * @author Stepan Rutz
 * @version $ID$
 */
public interface EndHandler
{
    void endElement (String uri, String localName, String qName);
}
