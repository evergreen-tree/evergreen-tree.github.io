/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package com.tensegrity.palobrowser.table;


/**
 * <code>TableModelEvent</code> as fired by
 * {@link com.tensegrity.palobrowser.table.TableModel}.
 * 
 * @author Stepan Rutz
 * @version $ID$
 */
public class TableModelEvent
{
    public static final int
    	MODELEVENT_CELLSCHANGED = 0,
        MODELEVENT_ALLCELLSCHANGED = 1;
    
    private final TableModel source;
    private final int type;
    private final TableCell tableCells[];
    
    public TableModelEvent(TableModel source, int type, TableCell tableCells[])
    {
        this.source = source;
        this.type = type;
        this.tableCells = (TableCell[]) tableCells.clone();
    }
    
    public TableModel getSource()
    {
        return source;
    }
    
    public int getType()
    {
        return type;
    }
    
    public TableCell[] getTableCells()
    {
        return tableCells;
    }
    
}

