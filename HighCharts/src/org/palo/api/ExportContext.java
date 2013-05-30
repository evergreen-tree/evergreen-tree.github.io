/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api;

/**
 * The interface <code>ExportContext</code> defines the export scope and 
 * filtering used during the data export process.
 *
 * <p>
 * To get an instance of an <code>ExportContext</code> use {@link Cube#getExportContext()}
 * or {@link Cube#getExportContext(Element[][])} respectively. A typical 
 * scenario for tweaking the export context is to influence the elements to be
 * exported and/or to set the filter condition to use. After setting the context
 * a  {@link Cube#getDataExport()} will export all effected cells.
 * </p>
 *
 * <p>
 * Here is an example code snippet for receiving all non-consolidated elements' 
 * combinations data, where value is bigger than 1000.0
 * <p><code>
 *       ...
 *       String elements[][] = new String[cube.getDimensionCount()][];
 *       for (int i=0; i<cube.getDimensionCount(); i++)
 *       {
 *           Dimension dim = cube.getDimensionAt(i);
 *           Element elems[] = dim.getElements();
 *           Vector nonCons = new Vector();
 *           for (int j=0; j<elems.length; j++)
 *               if (elems[j].getType()!=Element.ELEMENTTYPE_CONSOLIDATED)
 *                   nonCons.add(elems[j].getName());
 *           elements[i] = new String[nonCons.size()];
 *           for (int j=0; j<nonCons.size(); j++)
 *               elements[i][j]=(String)nonCons.elementAt(j);
 *       }
 *
 *		ExportContext context = cube.getExportContext(elements);
 *		Condition con1 = context.createCondition(Condition.GT,1000.0);
 *		context.setCondition(con1);
 *      ExportDataset dataset= cube.getDataExport();
 *      ...
 * </code></p>
 *</p>
 *
 * @author Axel Kiselev
 * @author ArndHouben
 * @version $Id: ExportContext.java,v 1.2 2007/08/27 12:56:32 ArndHouben Exp $
 */
public interface ExportContext {

	/** constant for compare operator OR used for combined condition */
	public static final String OR = "or";
	/** constant for compare operator XOR used for combined condition */
	public static final String XOR = "xor";
	/** constant for compare operator AND used for combined condition */
	public static final String AND = "and";
	
	
	/**
	 * Sets the maxmimal number of cells to export within one block. The default
	 * value is 1000.
	 * @param blocksize 
	 */
	void setBlocksize(int blocksize);
	/**
	 * Returns the current number of cells which are exported within one block.
	 * @return
	 */
	int getBlocksize();
	
	/**
	 * Filter flag to export base cells only. If set to <code>true</code> only
	 * base cells are exported, use <code>false</code> to include all cells. 
	 * @param baseCellsOnly
	 */
	void setBaseCellsOnly(boolean baseCellsOnly);
	/**
	 * Return the base cells only flag. 
	 * @return <code>true</code> if only base cells should be expported, 
	 * <code>false</code> otherwise
	 */
	boolean isBaseCellsOnly();
	
	/**
	 * Filter flag to ignore empty cells. If set to true empty cells are not 
	 * exported. 
	 * @param ignoreEmptyCells set <code>true</code> to exclude empty cells from 
	 * export, use <code>false</code> to include 
	 */
	void setIgnoreEmptyCells(boolean ignoreEmptyCells);
	/**
	 * Return the ignore empty cells flag. 
	 * @return <code>true</code> if empty cells are excluded from export, 
	 * <code>false</code> otherwise
	 */
	boolean ignoreEmptyCells();
	
	/**
	 * Sets the cell area which is effected by the export. 
	 * @param area 
	 */
	void setCellsArea(Element[][] area);
	/**
	 * Returns the effected cell area
	 * @return 
	 */
	Element[][] getCellsArea();
	
	/**
	 * Sets the element path after which the export starts. Specifying 
	 * <code>null</code> is allowed and has the effect on a reset. 
	 * @param path the element path or <code>null</code> to reset
	 */
	void setExportAfter(Element[] path);
	
	/**
	 * Returns the element path after which the export starts
	 * <b>NOTE:</b> can be null if no path has be set!!
	 * @return
	 */
	Element[] getExportAfter();
	
	//condition handling...
	/**
	 * Creates a new {@link Condition} used for filtering export data.
	 * To use this <code>Condition</code> set it via {@link #setCondition(Condition)}
	 * @param condition one of the defined condition constants. Please refer to {@link Condition}
	 * @param value the condition value 
	 */
	Condition createCondition(String condition, double value);
	/**
	 * Creates a new {@link Condition} used for filtering export data.
	 * To use this <code>Condition</code> set it via {@link #setCondition(Condition)}
	 * @param condition one of the defined condition constants. Please refer to {@link Condition}
	 * @param value the condition value 
	 */
	Condition createCondition(String condition, String value);
	/**
	 * Sets the condition to use for filtering the data to export.
	 * @param condition the filter <code>Condition</code>
	 */
	void setCondition(Condition condition);
	/**
	 * Sets a combined filter condition which consists of two 
	 * <code>Condition</code>s and one of the defined boolean operator, namely 
	 * {@link ExportContext#OR}, {@link ExportContext#XOR} or 
	 * {@link ExportContext#AND}
	 * @param firstCondition  the first filter <code>Condition</code>
	 * @param secondCondition the second filter <code>Condition</code>
	 * @param operator one of the defined boolean operator constant
	 */
	void setCombinedCondition(Condition firstCondition, Condition secondCondition, String operator);
	/**
	 * Returns the internal condition representation
	 * <b>NOTE:</b> for internal usage only
	 * @return
	 */
	String getConditionRepresentation();
	
	/**
	 * Resets this export context, i.e. its complete state is set back to 
	 * default value 
	 */
	void reset();
	
	/**
	 * Returns the current progress of the data export as a <code>double</code> 
	 * value, with range between 0.0 to 1.0
	 * @return a double which represents current export progress.
	 */
	double getProgress();
	
	/**
	 * <b>NOTE:</b> for internal usage only
	 * @param progress
	 */
	void setProgress(double progress);
}
