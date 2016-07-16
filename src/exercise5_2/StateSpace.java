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
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author 52582
 */
public class StateSpace {
   StateList children = new StateList();
  
    public StateSpace (){
        
    }
    // Operators functions:
        //the first one moves one person across the river
       // the second moves two people across the river
    public void Operator1(State st, HashMap <State, StateList> graph){
        //List all possible children
        //If the boatLocation = 0, the boat is the left side so people will move 
        //from the left side to the right side
        if (st.getBoatLocation() == 0){
            for (int i=0; i<st.getLeftSide().size(); i++){
                    List <Person> newRightSide = new ArrayList();
                    Person p = st.getLeftSide().get(i);
                    newRightSide.add(p);
                    List <Person> newLeftSide = new ArrayList();
                    ListIterator<Person> iter = st.getRightSide().listIterator();
                    while(iter.hasNext()) {
                        newRightSide.add(iter.next());
                    }
                    ListIterator<Person> iter1 = st.getLeftSide().listIterator();
                    while(iter1.hasNext()) {
                        newLeftSide.add(iter1.next());
                    }
                    newLeftSide.remove(p);
                    State newState = new State (newLeftSide,newRightSide, 1);
                    children.add(newState);
            }
        }
        //Else people will move from the right side to the left side
        else if(st.getBoatLocation() == 1) {
              for (int i=0; i<st.getRightSide().size(); i++){
                    List <Person> newLeftSide = new ArrayList();
                    Person p = st.getRightSide().get(i);
                    newLeftSide.add(p);
                    List <Person> newRightSide = new ArrayList();
                    ListIterator<Person> iter = st.getRightSide().listIterator();
                    while(iter.hasNext()) {
                        newRightSide.add(iter.next());
                    }
                    ListIterator<Person> iter1 = st.getLeftSide().listIterator();
                    while(iter1.hasNext()) {
                        newLeftSide.add(iter1.next());
                    }
                    newRightSide.remove(p);
                    State newState = new State (newLeftSide,newRightSide, 0);
                    children.add(newState);
            }
        }
    }
    public void Operator2(State st, HashMap <State, StateList> graph){
        //List all possible children
        //If the boatLocation = 0, the boat is the left side so people will move 
        //from the left side to the right side
        if (st.getBoatLocation() == 0){
            for (int i=0; i<st.getLeftSide().size(); i++){
                for (int j=i+1; j<st.getLeftSide().size(); j++){
                    List <Person> newRightSide = new ArrayList();
                    Person p1 = st.getLeftSide().get(i);
                    newRightSide.add(p1);
                    Person p2 = st.getLeftSide().get(j);
                    newRightSide.add(p2);
                    List <Person> newLeftSide = new ArrayList();
                    ListIterator<Person> iter = st.getRightSide().listIterator();
                    while(iter.hasNext()) {
                        newRightSide.add(iter.next());
                    }
                    ListIterator<Person> iter1 = st.getLeftSide().listIterator();
                    while(iter1.hasNext()) {
                        newLeftSide.add(iter1.next());
                    }
                    newLeftSide.remove(p1);
                    newLeftSide.remove(p2);
                    State newState = new State (newLeftSide,newRightSide, 1);
                    children.add(newState);
                }
            }
        }
        //Else people will move from the right side to the left side
        else if(st.getBoatLocation() == 1) {
              for (int i=0; i<st.getRightSide().size(); i++){
                for (int j=i+1; j<st.getRightSide().size(); j++){
                    
                    List <Person> newLeftSide = new ArrayList();
                    Person p1 = st.getRightSide().get(i);
                    newLeftSide.add(p1);
                    Person p2 = st.getRightSide().get(j);
                    newLeftSide.add(p2);
                    List <Person> newRightSide = new ArrayList();
                    
                    ListIterator<Person> iter = st.getRightSide().listIterator();
                    while(iter.hasNext()) {
                        newRightSide.add(iter.next());
                    }
                    ListIterator<Person> iter1 = st.getLeftSide().listIterator();
                    while(iter1.hasNext()) {
                        newLeftSide.add(iter1.next());
                    }
                    newRightSide.remove(p1);
                    newRightSide.remove(p2);
                    State newState = new State (newLeftSide,newRightSide, 0);
                    children.add(newState);
                }
            }
        }
    }
   
    //successor function applies the different operators to the state space 
    //and removes indesirable states
    public void successorFunction(State st, HashMap <State, StateList> graph){
        Operator1 (st, graph);
        Operator2 (st, graph);
       StateList undesirableStates = new StateList();
       
       for(int k=0;k<children.size();k++){
           removeImpossibleChildren(children.get(k),undesirableStates);
       }

           for(int j=0;j<undesirableStates.size();j++){
                for(int i=0;i<children.size();i++){
                  if(undesirableStates.get(j).compareTo(children.get(i))==0){
                     
                      children.remove(undesirableStates.get(j));
                     
                  }
                  
           }
       }
            
        graph.put(st, children);
    }
    public boolean isthereman(List <Person> side){
        for (int i=0;i<side.size();i++){
            if(side.get(i).getGender()==1) return true;
        }
        return false;
    }
    public boolean istherewoman(List <Person> side){
        for (int i=0;i<side.size();i++){
            if(side.get(i).getGender()==2) return true;
        }
        return false;
    }
    public boolean searchHusband (List <Person> side, Person wife){
        for (int i=0;i<side.size();i++){
            if(side.get(i).getCouple()==wife.getCouple()&& side.get(i).getGender()==1) return true;
        }
        return false;
    }
    //Remove impossible children
    public void removeImpossibleChildren (State st,StateList us){  
        
          
          //Check left side
            if(isthereman(st.getLeftSide()) && istherewoman(st.getLeftSide())){
               for(int i=0;i<st.getLeftSide().size();i++){
                   if(st.getLeftSide().get(i).getGender()==2){
                       if(!searchHusband(st.getLeftSide(),st.getLeftSide().get(i))){
                           us.add(st);
                           return;
                       }
                   }
                }
            }
            //Check Right Side
             if(isthereman(st.getRightSide()) && istherewoman(st.getRightSide())){
               for(int i=0;i<st.getRightSide().size();i++){
                   if(st.getRightSide().get(i).getGender()==2){
                       if(!searchHusband(st.getRightSide(),st.getRightSide().get(i))){
                           
                           us.add(st);
                           return;
                       }
                   }
                }
            }
    }
   
}
