package jkonoha;
import java.util.ArrayList;


public class KObject {
	// KObjectHeader h;
	private ArrayList<KObject>	fields;
	private ArrayList<Integer>		ndata;
	
	KObject ()
	{
		
	}
	
	public final ArrayList<KObject> getFields() {
		if (fields == null) {
			return new ArrayList<KObject>(4);
		}
		return fields;
	}
	
	public final ArrayList<Integer> getndata()
	{
		if (ndata == null){
			return new ArrayList<Integer>(4);
		}
		return ndata;
	}
}
