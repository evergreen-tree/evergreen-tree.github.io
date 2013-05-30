/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package com.tensegrity.palobrowser.table;

/**
 * <code>TableCell</code>, specifies a table-cell.
 *
 * @author Stepan Rutz
 * @version $ID$
 */
public class TableCell
{
    private final int rowIndex, columnIndex;
    
    public TableCell(int rowIndex, int columnIndex)
    {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex()
    {
        return columnIndex;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }
    
    public String toString()
    {
        return "TableCell row=" + rowIndex + " col=" + columnIndex; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
}
