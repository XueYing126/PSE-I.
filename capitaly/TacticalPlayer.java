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
public class TacticalPlayer  extends Player{
    private int a=1;
    //constructor
    public TacticalPlayer(String name){
        super(name,"TacticalPlayer");
    }
     
  
/**
 *  he skips each second chance when he could buy.
 * @param f :the property which he is staying
 */
    @Override
     public void BuyIt(Field f){
         if (this.balance >= 1000 && a%2==1){
         this.balance -= 1000;
         f.setOwner(this);
         ownedProperty.add(f);
         a+=1;
         }
    }
     /**
      *  he skips each second chance when he could buy.
      * @param f :the property which he is staying
      */
     @Override
     public void makeahouse(Field f){
        if (this.balance >= 4000 && a%2==1){
            this.balance -= 4000;
           f.makehouse();
           a+=1;
        }
    }
}
