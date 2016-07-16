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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import javax.xml.soap.Node;

/**
 *
 * @author 52582
 */
public class Exercise5_2 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //create the graph
        HashMap <State, StateList> graph = new HashMap <State, StateList> ();
        // test values
        Person husband1 = new Person (1,1);
        Person wife1 = new Person (2,1);
        Person husband2 = new Person (1,2);
        Person wife2 = new Person (2,2);
        int boatLocation = 0; //Left Side
        //create initial state
        List <Person> initialLeftSide = new ArrayList ();
        List <Person> initialRightSide = new ArrayList ();
        initialLeftSide.add(husband1);
        initialLeftSide.add(wife1);
        initialLeftSide.add(husband2);
        initialLeftSide.add(wife2);
        State initialState = new State(initialLeftSide, initialRightSide, boatLocation);
        //create final state (goal)
        List <Person> finalLeftSide = new ArrayList ();
        List <Person> finalRightSide = new ArrayList ();
        finalRightSide.add(husband1);
        finalRightSide.add(wife1);
        finalRightSide.add(husband2);
        finalRightSide.add(wife2);
        boatLocation = 1; //Right Side
        State finalState = new State (finalLeftSide, finalRightSide, boatLocation);
        
        StateSpace children = new StateSpace();
        children.successorFunction(initialState, graph);
        //search for solution using bfs
        System.out.println("Breadth First Search:");
        bfs(initialState, finalState, graph);
        System.out.println("Depth First Search:");
        dfs (initialState, finalState, graph);
        System.out.println("A*:");
        //algorithmA (initialState, finalState, graph);
            
        
  
    }
    //solution finder
    public static void solutionFinder (State currentState, SearchAgenda searchAgenda, HashMap <State, StateList> graph,StateList repeatedStates){
        //remove current state from agenda
            searchAgenda.remove(currentState);
            //generate its children
            StateSpace successors = new StateSpace ();
            successors.successorFunction(currentState, graph);
            //add them to search agenda
            for (int i=0; i< successors.children.size(); i++){ 
                if(!repeatedStates.contains(successors.children.get(i))) {
                    searchAgenda.add(successors.children.get(i));
                    repeatedStates.add(successors.children.get(i));
                }         
            }
    }
    //breath first search
    public static void bfs(State currentState, State finalState, HashMap <State, StateList> graph){
        //create a search agenda
        SearchAgenda searchAgenda = new SearchAgenda ();
        //add initial state to the search agenda
        searchAgenda.add(currentState);
        StateList repeatedStates = new StateList();
        repeatedStates.add(currentState);
        while (!searchAgenda.isEmpty() ){ 
            //while agenda not empty
            //if goal not reached yet, call solutionFinder  
            if (finalState.compareTo(searchAgenda.peek())==1){ 
                solutionFinder ((State)searchAgenda.peek(), searchAgenda, graph,repeatedStates);
             }
            // else print solution
            else if (finalState.compareTo(searchAgenda.peek())==0){
                System.out.println("solution found: "+ searchAgenda.peek().toString());
                break;
            }
        }
    }
  
    
    public static void dfs (State currentState, State finalState, HashMap <State, StateList> graph){
        // create search stack
        Stack <State> searchStack = new Stack <State> ();
        //add initial state
        searchStack.push(currentState);
        StateList repeatedStates = new StateList();
        repeatedStates.add(currentState);
        
        while (!searchStack.empty()){
            if (finalState.compareTo((State)searchStack.peek())==1){
                searchStack.pop();
                StateSpace successors = new StateSpace ();
                successors.successorFunction(currentState, graph);
                //add them to search Stack
                for (int i=0; i< successors.children.size(); i++){ 
                    if(!repeatedStates.contains(successors.children.get(i))) {
                        searchStack.push(successors.children.get(i));
                        repeatedStates.add(successors.children.get(i));
                    }         
                }
                
            }
            else if (finalState.compareTo((State)searchStack.peek())==0){
                System.out.println("solution found: "+ searchStack.peek().toString());
                break;
            }
        }

        
    }
  
    public static void algorithmA(State currentState, State finalState, HashMap <State, StateList> graph){
       MyComparator cp = new MyComparator();
       SortedSet<State> openList = new TreeSet(cp);
       List <State> closedList = new ArrayList ();
       int cost = 0;
       currentState.setCost(cost);
       openList.add(currentState);
       
       while(!openList.isEmpty()){
           //if goal is found
           cost++;
           if(finalState.compareTo(openList.last())==0){
               System.out.println("solution found by A*: "+ openList.last().toString());
                break;
           }
            closedList.add(openList.last());
            StateSpace successors = new StateSpace ();
            successors.successorFunction(openList.last(), graph);
            for(int k=0;k<successors.children.size();k++){
               System.out.println(successors.children.get(k));
            }
            for(int i=0;i<successors.children.size();i++){
                
                successors.children.get(i).setCost(cost);
                boolean s=openList.add(successors.children.get(0));
            }
            Iterator<State> iter= openList.iterator();
            while(iter.hasNext()){
                System.out.println(iter.next());
            }
       }
    }
   
}
