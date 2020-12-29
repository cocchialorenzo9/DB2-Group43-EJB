package group43.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListCaster {
	
	public static <T> List<T> castList(Class<? extends T> CasterClass, Collection<?> collection) throws ClassCastException {
	    List<T> list = new ArrayList<T>(collection.size());
	    for(Object o: collection) {
	    	try {
	    		CasterClass.cast(o);
	    	} catch (ClassCastException e) {
	    		throw new ClassCastException();
	    	}
	    	list.add(CasterClass.cast(o));
	    }
	      

	    return list;
	}

}
