package org.palo.api.impl;

import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Element;
import org.palo.api.Hierarchy;
import org.palo.api.exceptions.PaloIOException;

import com.efs.log.ConsoleLog;
import com.tensegrity.palojava.PaloFactory;
import com.tensegrity.palojava.PaloServer;

public class GetDataFromDatabase {
	private static final String DATABASE_IP = "42.121.129.238";
	private static final String DATABASE_PORT = "7777";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	private static Database[] cachedDbs;

	public static final Database[] Fetch() {
		if (cachedDbs == null) {
			synchronized (DATABASE_IP) {
				if (cachedDbs == null) {
					initCache();
				}
			}
		}
		ConsoleLog.info(cachedDbs);
		return cachedDbs;
	}

	public static void initCache() {
		PaloServer srv = PaloFactory.getInstance().createServerConnection(DATABASE_IP, DATABASE_PORT, 2, 2000);
		ConnectionImpl conn = new ConnectionImpl(srv);
		conn.login(USERNAME, PASSWORD);
		ConsoleLog.info(" db-info : { ip : " + DATABASE_IP + ", port : " + DATABASE_PORT + " , userName : " + USERNAME
				+ " , password : " + PASSWORD + "}");
		cachedDbs = conn.getDatabases();
	}

	private static Element eleUser = null;

	public static void main(String[] args) throws PaloIOException {
		Database[] databases = GetDataFromDatabase.Fetch();
		for (Database db : databases) {
			System.out.println("database : " + db.getName());

			for (Cube cube : db.getCubes()) {
				if (db.getName().equals("System")) {
					System.out.println("cube : " + cube.getName());
					Dimension[] dimensions = cube.getDimensions();
					for (Dimension dimen : dimensions) {
						System.out.println(dimen.getName());
						Element[] eles = dimen.getElements();
						for (Element ele : eles) {
							System.out.println("ele : " + ele.getId() + " " + ele.getName());
							if (ele.getName().equals("admin")) {
								eleUser = ele;
							}
						}
					}
				}
				if (cube.getCubeViewCount() > 0) {
					for (String id : cube.getCubeViewIds()) {
						checkCubeView(cube.getCubeView(id), cube);
					}
				}
			}
		}
	}

	private static void checkCubeView(CubeView cubeview, Cube cube) {
		if (!cubeview.getName().equals("LineTest")) {
			return;
		}

		System.out.println(cubeview.getRawDefinition());
		checkDemension(cube);
		Element[][] eles = new Element[12][];
		for (Dimension dimension : cube.getDimensions()) {
			if (dimension.getId().equals("14") || dimension.getId().equals("18")) {

			}
		}

		Dimension dimensionx = cube.getDimensionById("14");
		Dimension dimensiony = cube.getDimensionById("18");
		Dimension dimensionz = cube.getDimensionById("12");
		for (Element ele : dimensionz.getElements()) {
			if (ele.getDepth() == 1) {
				System.out.println("missed something");
			}
		}

		int y = 0;
		for (Element ele : dimensionx.getElements()) {
			if (ele.getDepth() == 1) {
				for (Element ele2 : dimensiony.getElements()) {
					if (ele2.getDepth() == 1) {
						Element[] tmp = new Element[cube.getDimensionCount()];
						int i = 0;
						for (Dimension dimens : cube.getDimensions()) {
							if (ele.getDimension().equals(dimens)) {
								tmp[i] = ele;
							} else if (ele2.getDimension().equals(dimens)) {
								tmp[i] = ele2;
							} else {
								tmp[i] = dimens.getElementAt(0);
							}
							i++;
						}
						eles[y] = tmp;
						y++;
					}
				}
			}
		}
		for (int i = 0; i < eles.length; i++) {
			System.out.println(cube.getData(eles[i]));
		}
	}

	private static void checkDemension(Cube cube) {
		for (Dimension dimension : cube.getDimensions()) {
			System.out.println();
			System.out.println("HierarchyCount : " + dimension.getHierarchyCount());
			System.out.println("defaultHierarchy : " + dimension.getDefaultHierarchy());
			System.out.println("dimension : " + dimension.getId() + " " + dimension.getName());
			if (dimension.getId().equals("14") || dimension.getId().equals("12") || dimension.getId().equals("18")) {
				Hierarchy hierarchy = dimension.getDefaultHierarchy();
				System.out.println("hierarchy :" + hierarchy);
				for (Element ele : hierarchy.getElements()) {
					System.out.println(ele + " depth : " + ele.getDepth() + " level : " + ele.getLevel());
				}

			}
			System.out.println();
		}
	}
}
