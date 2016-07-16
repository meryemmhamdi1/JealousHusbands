/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise5_2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 52582
 */
public class Person implements Comparable <Person>{
    private int gender;
    private int couple;
    
    public Person(int gender, int couple){
        this.gender = gender;
        this.couple = couple;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * @return the couple
     */
    public int getCouple() {
        return couple;
    }

    /**
     * @param couple the couple to set
     */
    public void setCouple(int couple) {
        this.couple = couple;
    }
    
    @Override
    public String toString(){
        return String.valueOf(this.getGender())+String.valueOf(this.getCouple());
    }
    @Override
    public int compareTo(Person p){
        if(gender == p.getGender() && couple == p.getCouple()) return 0;
        else return 1;
    }
}
