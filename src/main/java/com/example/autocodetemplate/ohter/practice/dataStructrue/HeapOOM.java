package com.example.autocodetemplate.ohter.practice.dataStructrue;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
	static class oomObject{
		
	}
	/**
	 * verbose:gc -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
	 * @param args
	 */
	public static void main(String[] args) {
		List<oomObject> list = new ArrayList<oomObject>();
		
		while(true){
			list.add(new oomObject());
		}
	}
	
	/**
	 * reference test
	 */
	public void reference(){
		SoftReference<oomObject> soft = new SoftReference<oomObject>(new oomObject());
		
	}
}
