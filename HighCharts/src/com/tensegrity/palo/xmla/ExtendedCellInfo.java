package com.tensegrity.palo.xmla;

import com.tensegrity.palojava.CellInfo;

public class ExtendedCellInfo {
	public CellInfo []   cellInfo;
	public String   [][] coordinates;
	
	public ExtendedCellInfo(CellInfo [] cellInfo, String [][] coordinates) {
		this.cellInfo = cellInfo;
		this.coordinates = coordinates;
	}
}
