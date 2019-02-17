// Xue Ying
//
// DPOYL8
//
// First Assignment
//
// 2018/10/15 21:52:05
//
// This solution was submitted and prepared by Xue Ying, DPOYL8 for the
// First Assignment assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.

/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package capitaly;



import java.util.ArrayList;



/**

 *

 * @author DPOYL8

 */

public class Player {

    String name;

    String strategy;

    int balance;

    ArrayList<Field> ownedProperty;

    int position;    //the index of the filed which he steps on

    

    //constructor

    public Player( String name,String strategy){

         this.name = name;

         this.balance = 10000;

         this.strategy = strategy;

         ownedProperty = new ArrayList<>();

         position = -1;

    }

    

    //setters and getters

    public String getName(){

       return this.name;

    }

    

    public void setBalance(int balance) {

        this.balance = balance;

    }

    public int getBalance() {

        return balance;

    }

    

    

    public void setStrategy(String strategy){

       this.strategy = strategy;

    }

    public String getStrategy(){

       return this.strategy;

    }

    

    public void setPosition(int pos){

       this.position = pos;

    }

   

    public int getPosition(){

       return this.position;

    }

    

    //other methods

    /**

     * If a player steps on a property field which is owned by somebody else, 

     * the player should pay to the owner 500, if there is no house on the field, 

     *or 2000, if there is a house on it.

     * @param payamount :500 or 2000

     */

    public void payothers(int payamount) {

        this.balance -= payamount;

    }

    /**

     * If other player steps on a property field which is owned by you, 

     * the player should pay to you 500, if there is no house on the field, 

     *or 2000, if there is a house on it. 

     * @param amount :500 or 2000

     */

    public void getpaid(int amount){

         this.balance += amount;

    }

    /**

     * he need to go forward 'step' steps and if he finish the round, he continue from the begin

     * @param step :the dice number he rolled

     * @param numf :the number of the fields

     */

    public void addStep(int step, int numf){

       this.position =(this.position + step)%numf;

    }

    /**

     * if he runs out of money, he loses

     * @return 

     */

    public boolean isAlive(){

          return this.balance >= 0 ;

    }

    /**

     * Stepping on a lucky field, the player gets some money 

     * @param amount :(the amount is defined as a parameter of the field).

     */

    public void Rewards(int amount){

        this.balance+=amount;

    }

    /**

     * Stepping on a service field, the player should pay to the bank 

     * @param amount :(the amount of money is a parameter of the field). 

     */

    public void Penalty(int amount){

        this.balance-=amount;

    }

    

    

    public  void BuyIt(Field f){}

    

    public void makeahouse(Field f){}

    

    /**

     * Print out the player , and how rich he is (balance, owned properties)

     * @return 

     */

    @Override

    public String toString() {      

        int i = 0;

        for(Field f:ownedProperty){ i++;}      

        return name +" who is a "+strategy +" , he/she have " + balance +" money left and owned "+ i + "  properties";

        

    }

    

}

