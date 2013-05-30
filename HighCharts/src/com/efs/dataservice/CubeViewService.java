package com.efs.dataservice;

import org.palo.api.CubeView;
import org.palo.api.Dimension;

public interface CubeViewService {
	Dimension getAxisYDimension(CubeView cubeView);
}
