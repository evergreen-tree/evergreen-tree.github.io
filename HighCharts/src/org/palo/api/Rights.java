package org.palo.api;

/**
 *
 * @author PhilippBouillon
 * @deprecated Do not use, subject to change.
 */
public interface Rights {
	boolean maySplash(PaloObject object);	
	boolean mayDelete(PaloObject object);
	boolean mayWrite(PaloObject object);
	boolean mayRead(PaloObject object);
	
	boolean maySplash(Class <? extends PaloObject> object);
	boolean mayDelete(Class <? extends PaloObject> object);
	boolean mayWrite(Class <? extends PaloObject> object);
	boolean mayRead(Class <? extends PaloObject> object);
	
	void allowSplash(String group, PaloObject object);
	void allowDelete(String group, PaloObject object);
	void allowWrite(String group, PaloObject object);
	void allowRead(String group, PaloObject object);
	void preventAccess(String group, PaloObject object);
	
	void allowSplash(String role, Class <? extends PaloObject> object);
	void allowDelete(String role, Class <? extends PaloObject> object);
	void allowWrite(String role, Class <? extends PaloObject> object);
	void allowRead(String role, Class <? extends PaloObject> object);
	void preventAccess(String role, Class <? extends PaloObject> object);	
}
