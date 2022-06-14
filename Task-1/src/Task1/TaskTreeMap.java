package Task1;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TaskTreeMap {
	
	@SuppressWarnings("rawtypes")
	public static void main(String args[]) {
	      System.out.println("\n- Cara 1"); //TreeMap Cara 1
	      TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
	      tmap.put(7, "Helia");
	      tmap.put(9, "Daniel");
	      tmap.put(4, "Dika");
	      tmap.put(2, "Dea");
	      tmap.put(5, "Adi");

	      Set set = tmap.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("Angka Favorite " + mentry.getValue() + " adalah "+ mentry.getKey() +"\n");
	      }
	      
	       
	      System.out.println("\n- Cara 2"); //TreeMap Cara 2
	      TreeMap<Integer,String> map=new TreeMap<Integer,String>();    
	      map.put(100,"Amit");    
	      map.put(102,"Ravi");    
	      map.put(101,"Vijay");    
	      map.put(103,"Rahul");    
	        
	      for(Map.Entry m:map.entrySet()){    
	       System.out.println(m.getKey()+" "+m.getValue());    
	      }    
	}
}
