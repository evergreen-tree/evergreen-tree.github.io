package org.palo.api.impl;

import org.palo.api.PaloObject;

import com.tensegrity.palojava.PaloConstants;
import com.tensegrity.palojava.PaloInfo;

abstract class AbstractPaloObject implements PaloObject {

	protected final int getType(PaloInfo info) {
		int type = info.getType();
		switch (info.getType()) {
		case PaloConstants.TYPE_ATTRIBUTE:
			return TYPE_ATTRIBUTE;
		case PaloConstants.TYPE_INFO:
			return TYPE_USER_INFO;
		case PaloConstants.TYPE_NORMAL:
			return TYPE_NORMAL;
		case PaloConstants.TYPE_SYSTEM:
			return TYPE_SYSTEM;
		}
		return -1;
	}
	
	protected final int getInfoType(int type) {
		switch(type) {
		case TYPE_ATTRIBUTE:
			return PaloConstants.TYPE_ATTRIBUTE;
		case TYPE_NORMAL:
			return PaloConstants.TYPE_NORMAL;
		case TYPE_SYSTEM:
			return PaloConstants.TYPE_SYSTEM;
		case TYPE_USER_INFO:
			return PaloConstants.TYPE_INFO;
		}
		return -1;
	}
}
