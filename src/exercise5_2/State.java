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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author 52582
 */
public class State implements Comparable <State>{
   private List <Person> LeftSide = new ArrayList ();
   private List <Person> RightSide = new ArrayList ();
   private int boatLocation;
   private int cost;
   private int heuritistic=this.heuristic();
   
   public State (List <Person> LeftSide, List<Person> RightSide, int boatLocation){
       this.LeftSide = LeftSide;
       this.RightSide = RightSide;
       this.boatLocation = boatLocation;
   }

    /**
     * @return the LeftSide
     */
    public List <Person> getLeftSide() {
        return LeftSide;
    }

    /**
     * @param LeftSide the LeftSide to set
     */
    public void setLeftSide(List <Person> LeftSide) {
        this.LeftSide = LeftSide;
    }

    /**
     * @return the RightSide
     */
    public List <Person> getRightSide() {
        return RightSide;
    }

    /**
     * @param RightSide the RightSide to set
     */
    public void setRightSide(List <Person> RightSide) {
        this.RightSide = RightSide;
    }

    /**
     * @return the boatLocation
     */
    public int getBoatLocation() {
        return boatLocation;
    }

    /**
     * @param boatLocation the boatLocation to set
     */
    public void setBoatLocation(int boatLocation) {
        this.boatLocation = boatLocation;
    }

   @Override
    public String toString (){
        String string = new String();
        string = "Left side has: ";
        Iterator left = this.getLeftSide().iterator();
        while(left.hasNext()) {
            String str = left.next().toString();
            string = string + " "+ str;
        }
        string = string + " Right side has: ";
        Iterator right = this.getRightSide().iterator();
        while (right.hasNext()){
            String str = right.next().toString();
            string = string +" "+str;
        }
        string = string + " Boat Location: "+this.getBoatLocation();
        return string;
    }
    public boolean search(Person p, List <Person> side){
        for(int j=0;j<side.size();j++){
            if (p.compareTo(side.get(j))==0) return true;
        }
        return false;
    }
    @Override
    public int compareTo(State st) {
        //check left side
        for (int i=0;i<LeftSide.size();i++){
            if(!search(LeftSide.get(i),st.getLeftSide())) return 1;
        }
        if(LeftSide.size()==0){
            //check right side
            for (int i=0;i<RightSide.size();i++){
                 if(!search(RightSide.get(i),st.getRightSide())) return 1;
            }
        }
        if ( boatLocation != st.boatLocation) return 1;
        return 0;
    }
    //this function returns the heuristic value of the state
    public int heuristic (){
        int h;
        h= this.getLeftSide().size();
        return (h/2);
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
    public boolean equals(State other){
    boolean result;
    if((other == null) || (getClass() != other.getClass())){
        result = false;
    } // end if
    else{
        State otherPeople = (State)other;
        if(this.compareTo(other)==0) result = true;
        else result =false;
    } // end else

    return result;
} // end equals
}
