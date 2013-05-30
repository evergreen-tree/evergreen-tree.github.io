/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api;

/**
 * <code>ExportDataset</code>
 *
 * Allows to get access to data export results
 *
 * <p>
 * Instances of that class are received by 
 * {@link org.palo.api.Cube#getDataExport()} calls.
 * </p>
 *
 * <p>
 * Here is an example code snippet for walking through data export results
 * <pre>
 *      ...
 *     ExportDataset dataset= cube.getDataExport();
 *     while(dataset.hasNextCell())
 *     {
 *         Cell cell = dataset.getNextRow();
 *         ...
 *     }
 *      ...
 *</pre>
 *</p>
 *
 * <p>
 * See also {@link org.palo.api.ExportContext} and {@link Cell}.
 * </p>
 *
 * @author Axel Kiselev
 * @author ArndHouben
 * @version $Id: ExportDataset.java,v 1.1 2007/03/12 16:51:53 ArndHouben Exp $
 */
public interface ExportDataset {
	
//	/**
//	 * Gets next row from the dataset
//	 * @return next row. Objects in that row represent path (element names, {@link java.lang.String})
//	 * and the value ({@link java.lang.String} or ({@link java.lang.Double})
//	 */
//	Object[] getNextRow();
//
//	/**
//	 * Checks whether there are at least one more row
//	 * @return <code>true</code> if dataset contains at least one more row, <code>false</code> otherwise
//	 */
//	boolean hasNextRow();
	
	/**
	 * Gets the next cell from dataset
	 * @return next cell
	 */
	Cell getNextCell();

	/**
	 * Checks whether there is at least one more cell.
	 * @return <code>true</code> if dataset contains at least one more cell, 
	 * <code>false</code> otherwise
	 */
	boolean hasNextCell();

}
