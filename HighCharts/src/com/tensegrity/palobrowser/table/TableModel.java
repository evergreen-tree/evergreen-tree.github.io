/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package com.tensegrity.palobrowser.table;


/**
 * <code>TableModel</code> is the datamodel used by
 * {@link com.tensegrity.palobrowser.cube.PaloTable}.
 * 
 * @author Stepan Rutz
 * @version $ID$
 */
public interface TableModel
{
    /**
     * Adds a {@link TableModelListener} to this model if it
     * wasn't previously added.
     * @param listener the {@link TableModelListener} to add.
     */
    void addListener(TableModelListener listener);
    
    /**
     * Removes a {@link TableModelListener} from this model if it
     * was previously added.
     * @param listener the {@link TableModelListener} to remove.
     */
    void removeListener(TableModelListener listener);
    
    /**
     * Returns the number of row-levels.
     * @return the number of row-levels.
     */
    int getRowLevelCount();
    
    /**
     * Returns the number of column-levels.
     * @return the number of column-levels.
     */
    int getColumnLevelCount();
    
    /**
     * Returns the root {@link AxisModel}s at the given row-index.
     * @param levelIndex the row levelindex to return.
     * @return the root {@link AxisModel}s at the given row-index.
     */
    AxisModel[] getRows(int levelIndex);

    /**
     * Returns the root {@link AxisModel}s at the given column-index.
     * @param levelIndex the column levelindex to return.
     * @return the root {@link AxisModel}s at the given column-index.
     */
    AxisModel[] getColumns(int levelIndex);
    
    /**
     * Returns the number of rows (flat).
     * @return the number of rows (flat).
     */
    int getRowCount();
    
    /**
     * Returns the number of columns (flat).
     * @return the number of columns (flat).
     */
    int getColumnCount();
    
    /**
     * Returns the value at the given position (flat):
     * @param row the row (flat).
     * @param column the row (flat).
     * @return the value at the given position (flat):
     */
    Object getValue(int row, int column);
    
    /**
     * Set the value at the given position (flat):
     * @param row the row (flat).
     * @param column the row (flat).
     * @param value the value to set at the given position (flat):
     */
    Object setValue(int row, int column, Object value);
    
    /**
     * Returns <code>true</code> if the given cell is editable.
     * @param row the row (flat).
     * @param column the row (flat).
     * @return <code>true</code> if the given cell is editable.
     */
    boolean isEditable(int row, int column);
    
    /**
     * Returns <code>true</code> if the given cell is a leaf cell.
     * @param row the row (flat).
     * @param column the row (flat).
     * @return <code>true</code> if the given cell is a leaf cell.
     */
    boolean isLeaf(int row, int column);
    
}

