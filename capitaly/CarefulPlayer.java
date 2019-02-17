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
 *
 * @author Admin
 */
public class CarefulPlayer extends Player{
    //constructor
     public CarefulPlayer(String name){
        super(name,"CarefulPlayer");
    }

    /**
     *he buys in a round only for at most half the amount of his money. 
     * A property can be bought for 1000, so he need at least 2000 to buy it
     * @param f :the property which he is staying
     */
    @Override
     public void BuyIt(Field f){
         if (this.balance >= 2000){
         this.balance -= 1000;
         f.setOwner(this);
         ownedProperty.add(f);
         }
    }
     /**
      * he buys in a round only for at most half the amount of his money.
      * player can build a house on it for 4000,so he need at least 8000 to built it
      * @param f :the property which he is staying
      */
     @Override
     public void makeahouse(Field f){
        if (this.balance >= 8000){
            this.balance -= 4000;
           f.makehouse();
        }
    }
}
