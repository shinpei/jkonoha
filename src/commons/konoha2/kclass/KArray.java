package kclass;

import java.util.*;
import commons.*;

public final class KArray<T> {
	
	public KObjectHeader h;
	private ArrayList<T> list;
	
	KArray() {
		this.list = new ArrayList<T>();
	}
	
	public final int size() {
		return list.size();
	}
	
	public final boolean add(T data) {
		return list.add(data);
	}
	
	public final void insert(int index, T data) {
		list.add(index, data);
	}
	
	public final void clear() {
		list.clear();
	}
}