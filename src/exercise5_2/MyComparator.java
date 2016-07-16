/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise5_2;

import java.util.Comparator;

/**
 *
 * @author 52582
 */
public class MyComparator implements Comparator <State> {

    @Override
    public int compare(State o1, State o2) {
       return (o1.heuristic()+o1.getCost())-(o2.heuristic()+o2.getCost());
    }
}
