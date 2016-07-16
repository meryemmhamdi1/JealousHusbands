/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise5_2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author 52582
 */
public class StateList {
     private List <State> children = new ArrayList ();
     public StateList (){
         
     }
    /**
     * @return the children
     */
    public List <State> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List <State> children) {
        this.children = children;
    }
    public void add(State st){
        children.add(st);
    }
    public void remove(State st){
        children.remove(st);
    }
    public State get(int index){
        return children.get(index);
    }
    public int size(){
        return children.size();
    }
     @Override
    public String toString(){
         String string = "";
         ListIterator<State> iter = children.listIterator();
        while(iter.hasNext()) {
            String str = iter.next().toString();
            string = string + str+"\n";
        }
        return string;
    }
   public boolean contains(State st){
       ListIterator<State> iter = children.listIterator();
        while(iter.hasNext()) { 
            if (iter.next().compareTo(st)==0) return true;
        }
          
     return false;
   }
}

