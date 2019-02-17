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



/**

 *inherited from Field

 * @author DPOYL8

 */

public class Property extends Field {

    private Player owner;

    private int house;

    

    //constructor

    public Property (){

        super ("Property");

        this.owner = null;

        this.house=0;

    }

    //getters and setters:

    @Override

    public void setOwner(Player p){

       this.owner = p;

    }

    @Override

    public Player getOwner(){

       return this.owner;

    }

   //other methods:

    /**

     * check if the house has a owner

     * @return true if the house has a owner

     */

    @Override

    public boolean owned(){

        return this.owner != null;

    }

    /**

     * check if there is a house in this property

     * @return  true if there is a house in this property

     */

    @Override

    public boolean hashouse(){

        return this.house == 1;

    }

    /**

     * make a house in this property

     */

    @Override

    public void makehouse(){

    this.house = 1;

    }

    /**

     * if the owner of the property loses, his properties are lost, and become free to buy.

     */

    @Override

    public void loserproperty(){

    this.house = 0;

    this.owner = null;

    }

    @Override
    public int getPenalty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getReward() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

