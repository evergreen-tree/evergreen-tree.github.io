/* (c) 2007 Tensegrity Software */
package com.tensegrity.palo.xmla.builders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Empty class to load the resources.
 * @author AndreasEbbert
 * @version $Id: Resource.java,v 1.1 2007/09/06 09:13:13 PhilippBouillon Exp $
 */
public class Resource {
  private static String baseFunctions = null;
  private Resource() {}
  
  public final static String getBaseFunctions() {
	  if (baseFunctions == null) {
		  baseFunctions = getXMLString("baseFunctions.xml");
	  }
	  return baseFunctions;
  }

  final static String getXMLString(String res) {
    InputStream in = Resource.class.getResourceAsStream(res);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    StringBuffer sb = new StringBuffer();
    try {
      String line = null;
      while ((line = br.readLine()) != null)
        sb.append(line);
    }
    catch (IOException e1) {
      e1.printStackTrace();
    }
    finally {
      try {
        in.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }
}
