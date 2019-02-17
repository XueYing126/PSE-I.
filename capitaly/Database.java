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



import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.util.ArrayList;

import java.util.Scanner;



/**

 *

 * @author DPOYL8

 */

public class Database {

    

    private final ArrayList<Field> fields;

    private final ArrayList<Player> players;

    private final ArrayList<Integer> dices;

    //private final ArrayList<Player> loseplayers;

    private int numFilelds ;

    private int numPlayers;

     

    public Database() {

        fields = new ArrayList<>();

        players = new ArrayList<>();

        dices = new ArrayList<>();

    }

    

    public void read(String filename) throws FileNotFoundException, InvalidInputException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));

        numFilelds = sc.nextInt();//the number of the fields

        for(int i=0;i<numFilelds;i++) {

            Field field;

            switch (sc.next()) {

                case "property":

                    field = new  Property();

                    break;

                case "luckyfield":

                    field = new Luckyfield(sc.nextInt());

                    break;

                case "service":

                    field = new Service(sc.nextInt());

                    break;  

                default:

                    throw new InvalidInputException();

            }

            

            fields.add(field);

        }

        numPlayers = sc.nextInt();//the number of players

            for (int i = 0; i < numPlayers; i++) {              

                String name = sc.next();

                Player player;

                switch (sc.next()) {

                case "Tactical":

                    player = new TacticalPlayer(name);

                    break;

                case "Greedy":

                    player = new GreedyPlayer(name);

                    break;

                case "Careful":

                    player = new CarefulPlayer(name);

                    break;  

                default:

                    throw new InvalidInputException();

            }

                players.add(player);

            }

          //read roll dices   

        while (sc.hasNextInt()) {     

            dices.add(sc.nextInt());

        }

            

    }

      //now we start the game

      public void GameStart() {

       int dice_Index =0 ;

       while(dice_Index  < dices.size() && numAlive()> 1){   //if more than one person alive, the game consinue         
 //int palyer_Index
        for(Player p : players){  

            int steps = dices.get(dice_Index );  //the number of steps he need go

            if (! p.isAlive()){    

                //if he losts, his properties are lost, and become free to buy.

                for(Field f:p.ownedProperty){

                    f.loserproperty();
                }   
            }

            else {

              p.addStep(steps,numFilelds);

            Field field = fields.get(p.getPosition());

            

            if (field.getType().equals("Luckyfield")){   //if you step on luck field

              p.Rewards(field.getReward());

            }

            if (field.getType().equals("Service")){     //if you step on service

              p.Penalty(field.getPenalty());     

            }

            if (field.getType().equals("Property")){    //if you step on a property

            

                if(!field.owned()){

                  p.BuyIt(field);       //if no own own the property

                }else{

                  if(field.getOwner().equals(p)){  //if you own the property  :

                      if(!field.hashouse()){

                         p.makeahouse(field);    //if you own the property and there is no house on it

                       }                    

                    }

                  else   //if  others owns the property  (if you don't have enough money you may dead)

                  {

                     if(!field.hashouse()){   //if others own the property and there is no house on it, you need to pay 500 to the owner

                         if(p.getBalance()>=500){   //if he has enough money to pay
                           p.payothers(500);
                           field.getOwner().getpaid(500);
                         
                        }
                          
                         else{
                                
                         if(p.getBalance()>=0){
                          field.getOwner().getpaid(p.getBalance()); 
                         }  //the owner get his remaining money
                          p.payothers(500);      //if he doesn;t have enough money to pay, his balance become negative
                         }
                       } 

                     else          //if others own the property and there is a house on it, you need to pay 2000 to the owner

                     {                        
                          if(p.getBalance()>=2000){ //if he has enough money to pay
                           p.payothers(2000);
                           field.getOwner().getpaid(2000);
                           }
                         else{
                           
                          if(p.getBalance()>=0){    
                            field.getOwner().getpaid(p.getBalance());
                          }    //the owner get his remaining money
                           p.payothers(2000);         //if he doesn;t have enough money to pay, his balance become negative 
                          }
                     }

                  }                

                  }                                                          

                }

                 dice_Index ++; 

            }

           

        }

    }

        //in the end , there is only one player alive:

       for (Player p:players){

           if(p.isAlive()){ System.out.println(" The winner is: " + p);}

       }

   }

      

      /**

       * 

       * @return the number of active players

       */

      public int numAlive(){

          int num = 0;

          for (Player p:players){

              if(p.isAlive()){num++;}

            }

          return num;

        }

 

    public void clear(){

        fields.clear();

        players.clear();

        dices.clear();

        }

    

}





