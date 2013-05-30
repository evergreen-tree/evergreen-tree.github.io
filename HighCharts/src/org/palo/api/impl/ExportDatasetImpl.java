/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api.impl;

import org.palo.api.Cell;
import org.palo.api.Cube;
import org.palo.api.Element;
import org.palo.api.ExportDataset;
import org.palo.api.PaloAPIException;

import com.tensegrity.palojava.CellInfo;
import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.PaloException;

/**
 * {@<describe>}
 * <p>
 * <code>ExportDataset</code> interface inplementation
 * </p>
 * {@</describe>}
 *
 * @author Axel Kiselev
 * @author ArndHouben
 * @version $Id: ExportDatasetImpl.java,v 1.5 2008/04/15 08:20:57 PhilippBouillon Exp $
 */
class ExportDatasetImpl implements ExportDataset {

    private final ExportContextImpl context;
    private final Cube cube;
    private final CubeInfo cubeInfo;
    private int index;
    private CellInfo[] cells;

    ExportDatasetImpl(Cube cube) {
		this.cube = cube;
		this.cubeInfo = ((CubeImpl)cube).getInfo();
		this.context = (ExportContextImpl)cube.getExportContext();
	}
    
    final synchronized void start() {
    	cells = getDataExportInternal();
		index = 0;
	}

    public final synchronized Cell getNextCell() {
    	CellImpl cell = null;
		if (index < cells.length) {
			cell = new CellImpl(cube,cells[index++]);
		} else {
			cells = getDataExportInternal();
			/*
			 * Sad fact, but 1.5 and 1.0c treats lastElements differently. 1.0c
			 * one thinks that this is start point for export and lastElements
			 * appeared to be first element of the next chunk, 1.5 treats
			 * lastElements as exported already, and starts export with NEXT
			 * line.
			 */
			if (!cube.getDatabase().getConnection().isLegacy()) // getServerInfo().startsWith("1.5"))
				index = 0;
			else
				index = 1;// we're going with 1.0 version
			if (index < cells.length) {
				cell = new CellImpl(cube,cells[index++]);
			}
		}
		return cell;
	}

    public final synchronized boolean hasNextCell()
    {
        if (context==null || cells==null || cells.length==0) // || data[0].length==0)
            return false;
        if (index==cells.length && context.getProgress()>=1)
            return false;
        else
            return true;
    }

    
    //--------------------------------------------------------------------------
    // PRIVATE
    //
    private final CellInfo[] getDataExportInternal() {
		CellInfo[] cells;
		try {
			ConnectionImpl cimpl = 
				(ConnectionImpl) cube.getDatabase().getConnection();
			cells = cimpl.getConnectionInternal().getDataExport(cubeInfo,
					context.getInfo());
			// Default modification of last coordinates set, i.e. take last
			// cell path and set it as new export after path:
			if (cells != null && cells.length>0){
				int lastCell = cells.length-1;
				String[] pathIds = cells[lastCell].getCoordinate();
				if(pathIds != null) {
					Element[] newExportAfterPath = context.getExportAfter();
					if(newExportAfterPath == null)
						newExportAfterPath = new Element[pathIds.length];
					//determine corresponding elements
					for(int i=0;i<pathIds.length;++i) {
						newExportAfterPath[i] = cube.getDimensionAt(i).
						getDefaultHierarchy().getElementById(pathIds[i]);
					}
					context.setExportAfter(newExportAfterPath);
				}
			}
		} catch (PaloException pex) {
			throw new PaloAPIException(pex);			
		} catch (RuntimeException e) {
			throw new PaloAPIException(e.getMessage(), e);
		}
		return cells;
	}
}
