package commons.konoha2.kclass;

import java.util.*;

/**
 * _kArray in original konoha2 
 * 
 * @author okachin
 *
 * @param <T>
 */

public final class KArray<T> extends KObject {
	
	private ArrayList<T> list;
	
	public KArray() {
		this.list = new ArrayList<T>();
	}
	
	/**
	 * getter
	 * @param int i
	 * @return T
	 */
	
	public final T get(int i) {
		return this.list.get(i);
	}
	
	/**
	 * get
	 * @return int
	 */
	
	public final int size() {
		return this.list.size();
	}
	
	/**
	 * add
	 * @param T data
	 * @return boolean
	 */
	
	public final boolean add(T data) {
		return this.list.add(data);
	}
	
	/**
	 * insert 
	 * @param int index
	 * @param T data
	 */
	
	public final void insert(int index, T data) {
		this.list.add(index, data);
	}
	
	/**
	 * clear 
	 */
	
	public final void clear() {
		this.list.clear();
	}
}