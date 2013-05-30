/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package com.tensegrity.palobrowser.preferences;

/**
 * <code>PreferenceConstants</code>
 * 
 * @author Stepan Rutz
 * @version $ID$
 */
public class PreferenceConstants
{
    public static final String
        PREF_CONNECTIONS = "connections_v1", //$NON-NLS-1$
        PREF_LAST_ACTIVE_CONNECTION = "lastActiveConnection",
        PREF_LOGIN_CANCELLED = "loginCancelled", 	//has no editor, just for internal usage!! //$NON-NLS-1$
        PREF_CONNECT_ON_STARTUP = "connectOnStartup", //$NON-NLS-1$
        PREF_DO_LOAD_ON_DEMAND = "doLoadOnDemand", //$NON-NLS-1$
        PREF_SHOW_LOCAL_TOOLBAR_IN_DBEXPLORER = "showLocalToolbarInDbExplorer", //$NON-NLS-1$
        PREF_SHOW_HIERARCHICAL_ELEMENTS_IN_DBEXPLORER = "showHierarchicalElementsInDbExplorer", //$NON-NLS-1$
        PREF_SHOW_SYSTEM_CUBES_AND_DIMENSIONS = "showSystemCubesAndDimensions", //$NON-NLS-1$
        PREF_SHOW_WARNING_BEFORE_N_CUBE_CELLS = "showWarningBeforeNCubeCells", //$NON-NLS-1$
        PREF_CONFIRM_LARGE_MEMORY_USAGE = "confirmLargeMemoryUsage", //$NON-NLS-1$
        PREF_CONFIRM_MANY_ELEMENTS_LOAD = "confirmManyElementsLoad", //$NON-NLS-1$
        PREF_SHOW_COORDINATES_IN_TOOLTIPS = "showCoordinatesInTooltips", //$NON-NLS-1$
        PREF_LOAD_CUBE_DATA_ON_DEMAND = "loadCubeDataOnDemand_v1", //$NON-NLS-1$
	
        PREF_FG_CELL_COLOR = "fgCellColor", //$NON-NLS-1$
        PREF_BG_CELL_COLOR = "bgCellColor", //$NON-NLS-1$
        PREF_NON_EDITABLE_FG_CELL_COLOR = "nonEditableCellFgColor", //$NON-NLS-1$
        PREF_NON_EDITABLE_BG_CELL_COLOR = "nonEditableCellBgColor", //$NON-NLS-1$
        PREF_OPEN_CUBES_COLLAPSED = "initiallyOpenCubesCollapsed_v1", //$NON-NLS-1$
        PREF_OPEN_DND_DIMENSIONS_WITH_INITIAL_LEVEL = "openDnDimensionsWithInitialLevel", //$NON-NLS-1$
        PREF_OPEN_DROPDOWNS_COLLAPSED = "initiallyOpenDropDownsCollapsed", //$NON-NLS-1$
        PREF_SHOW_WARNING_BEFORE_EDIT_CONSOLIDATED = "showWarningBeforeEditConsolidated", //$NON-NLS-1$
        
        PREF_CUBE_FONT_REGION = "cubeFontRegion", //$NON-NLS-1$
        PREF_CUBE_FONT_CONTENT = "cubeFontContent", //$NON-NLS-1$
        PREF_CUBE_FONT_HEADER = "cubeFontHeader", //$NON-NLS-1$
    
    	PREF_PROMPT_FOR_SAVE_AFTER_CUBE_BROWSING = "promptForSaveAfterCubeBrowsing", //$NON-NLS-1$
    
    	PREF_NOTIFY_ON_EXTERNAL_CHANGES = "notifyOnExternalChanges", //$NON-NLS-1$
    	PREF_NOTIFY_ON_SERVER_DOWN = "notifyOnServerDown", //$NON-NLS-1$
    	
    	PREF_PROMPT_FOR_SUBSET_CONVERT_ON_STARTUP="promptForLegacySubsetConvertOnStartUp";//$NON-NLS-1$
}
