package Task1;

import java.util.Iterator;
import java.util.TreeSet;

public class TaskTreeSet {
	public static void main(String args[]) {
		System.out.println("\n- Cara 1"); //TreeSet Cara 1
        TreeSet<String> tset = new TreeSet<String>();
        tset.add("ABC");
        tset.add("String");
        tset.add("Test");
        tset.add("Pen");
        tset.add("Ink");
        tset.add("Jack");
        System.out.println(tset);

        TreeSet<Integer> tset2 = new TreeSet<Integer>();
        tset2.add(88);
        tset2.add(7);
        tset2.add(101);
        tset2.add(0);
        tset2.add(3);
        tset2.add(222);
        System.out.println(tset2); 
        
        
        System.out.println("\n- Cara 2"); //TreeSet Cara 2
        TreeSet<String> al=new TreeSet<String>();  
        al.add("Ravi");  
        al.add("Vijay");  
        al.add("Ravi");  
        al.add("Ajay");  
        Iterator<String> itr=al.iterator();  
        while(itr.hasNext()){  
         System.out.println(itr.next());  
        }  
         
   }
}
