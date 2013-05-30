/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api.impl.xml;

import org.xml.sax.Attributes;

/**
 * <code>StartHandler</code>
 *
 * @author Stepan Rutz
 * @version $ID$
 */
public interface StartHandler
{
    void startElement (
        String uri,
        String localName,
        String qName,
        Attributes attributes);
}
