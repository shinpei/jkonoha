package commons.konoha2.kclass;

import java.util.*;

public final class KArray<T> extends KObject {
	
	private ArrayList<T> list;
	
	KArray() {
		this.list = new ArrayList<T>();
	}
	
	public final T get(int i) {
		return this.list.get(i);
	}
	
	public final int size() {
		return this.list.size();
	}
	
	public final boolean add(T data) {
		return this.list.add(data);
	}
	
	public final void insert(int index, T data) {
		this.list.add(index, data);
	}
	
	public final void clear() {
		this.list.clear();
	}
}