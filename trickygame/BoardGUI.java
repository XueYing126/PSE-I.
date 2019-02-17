// Xue Ying
//
// DPOYL8
//
// 4. Tricky five-in-a-row
//
// 2018/11/20 13:03:07
//
// This solution was submitted and prepared by Xue Ying, DPOYL8 for the
// 4. Tricky five-in-a-row assignment of the Practical software engineering I. course.
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
package trickygame;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author DPOYL8
 */
public class BoardGUI {

    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private JLabel TurnsLable;
    private ArrayList<Point> points;

    private Random random = new Random();
    private int clickNum = 0;



    public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
        points = new ArrayList<>();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(80, 80));
                buttons[i][j] = button;
                boardPanel.add(button);
                points.add(new Point(i, j));               
            }
        }
        Collections.shuffle(points);

        TurnsLable = new JLabel(" ");
        TurnsLable.setHorizontalAlignment(JLabel.RIGHT);
         TurnsLable.setText("X" + " turns");
 
    }

   
/**
 * check when you add a new sign, if there is any five in a row or if there are 3 or 4
 * terminate the game when a player won the game.
 * @param x row position
 * @param y column position
 */
    public void refresh(int x, int y) {
        JButton button = buttons[x][y];
        Field field = board.get(x, y);
       
        if (field.getNumber() != -1) {
           if (field.getNumber()%2==1){
            button.setText("X");
            //First we check if there is five in a row
                //row
            int U=0;
            while((y-U-1)>-1 && board.get(x, (y-U-1)).getNumber()!=-1 && board.get(x, (y-U-1)).getNumber()%2==1){
                U++;
            }
            int D=0;
            while((y+D+1)<board.getBoardSize()&& board.get(x, (y+D+1)).getNumber()!=-1 && board.get(x, (y+D+1)).getNumber()%2==1){
                D++;
            }                      
                //column
            int L=0;    
            while((x-L-1)>-1 && board.get((x-L-1), y).getNumber() != -1 && board.get((x-L-1), y).getNumber()%2==1){
                L++;
            }
            int R=0;
            while((x+R+1)<board.getBoardSize() && board.get((x+R+1), y).getNumber() != -1 && board.get((x+R+1), y).getNumber()%2==1){
                R++;
            } 
                // diagonal           
            int T=0;    
            while((y-T-1)>-1 &&(x-T-1)>-1&& board.get((x-T-1), (y-T-1)).getNumber()!=-1 && board.get((x-T-1), (y-T-1)).getNumber()%2==1){
                T++;
            }
            int F=0;
            while((x+F+1)<board.getBoardSize() &&(y+F+1)<board.getBoardSize() && board.get((x+F+1),(y+F+1)).getNumber() != -1 && board.get((x+F+1), (y+F+1)).getNumber()%2==1){
                F++;
            } 
            int N=0;    
            while((y-N-1)>-1&&(x+N+1)<board.getBoardSize() && board.get((x+N+1), (y-N-1)).getNumber()!=-1 && board.get((x+N+1), (y-N-1)).getNumber()%2==1){
                N++;
            }
            int H=0;
            while((x-H-1)>-1&&(y+H+1)<board.getBoardSize() && board.get((x-H-1), (y+H+1)).getNumber()!=-1 && board.get((x-H-1),(y+H+1)).getNumber()%2==1){
                H++;
            }
            
            //Check if there are five adjacent signs in a row, column or diagonal,
            //if there are, we  show in a message box which player won.then  automatically begin a new game.;
             if(T+F>3||U+D>3||L+R>3||N+H>3){
                 JOptionPane.showMessageDialog(boardPanel, " X have won  the game. ", "Congrats!",
                               JOptionPane.PLAIN_MESSAGE);
                
                TrickygameGUI game = new TrickygameGUI();
            }
               
                //if not , we check if there are four or three in a row, column or diagonal
               
            if(T+F==3||U+D==3||L+R==3||N+H==3){
                //t if a player makes 4 adjacent signs (in a row, column or diagonal), then
               //two of his signs is removed randomly (not necessary from this 3 signs).
             
             for (int t = 0;t < 2;) {
                  ArrayList<Point> point1 = new ArrayList<>();
               for (int i = 0; i < board.getBoardSize(); ++i) {
                    for (int j = 0; j < board.getBoardSize(); ++j) {
                    point1.add(new Point(i, j));               
                  }
               }
                    Collections.shuffle(point1);
                    Point point = point1.remove(point1.size() - 1);
                //two of his signs is removed randomly (not necessary from this 3 signs)
                    if (board.get(point).getNumber()!=-1 &&board.get(point).getNumber()%2==1) {
                        board.get(point).setNumber(-1);
                        refresh(point.x, point.y);
                        t++;
                    }
                }
             
             
               
            } else  if(T+F==2||U+D==2||L+R==2||N+H==2){
                //If there is no four in a row , we check if there are three in a row
                // if a player makes 3 adjacent signs (in a row, column or diagonal), then
               //one of his signs is removed randomly (not necessary from this 3 signs).
               
               for (int t = 0; t < 1;) {
                   ArrayList<Point> point1 = new ArrayList<>();
               for (int i = 0; i < board.getBoardSize(); ++i) {
                    for (int j = 0; j < board.getBoardSize(); ++j) {
                    point1.add(new Point(i, j));               
                  }
               }
                    Collections.shuffle(point1);
                    Point point = point1.get(point1.size() - 1);
                    //one of his signs is removed randomly (not necessary from this 3 signs).
                    if (board.get(point).getNumber()!=-1 &&board.get(point).getNumber()%2==1) {
                        board.get(point).setNumber(-1);
                        refresh(point.x, point.y);
                        t++;
                    }
                }
             
            }
   
       }
            else {
               //O turns
               
                //First we check if there is five in a row
                
            button.setText("O");
            
                //row
            int U=0;
            while((y-U-1)>-1 && board.get(x, (y-U-1)).getNumber()!=-1 && board.get(x, (y-U-1)).getNumber()%2==0){
                U++;
            }
            int D=0;
            while((y+D+1)<board.getBoardSize()&& board.get(x, (y+D+1)).getNumber()!=-1 && board.get(x, (y+D+1)).getNumber()%2==0){
                D++;
            }                      
             //column
            int L=0;    
            while((x-L-1)>-1 && board.get((x-L-1), y).getNumber() != -1 && board.get((x-L-1), y).getNumber()%2==0){
                L++;
            }
            int R=0;
            while((x+R+1)<board.getBoardSize() && board.get((x+R+1), y).getNumber() != -1 && board.get((x+R+1), y).getNumber()%2==0){
                R++;
            } 
                // diagonal           
            int T=0;    
            while((y-T-1)>-1&&(x-T-1)>-1 && board.get((x-T-1), (y-T-1)).getNumber()!=-1 && board.get((x-T-1), (y-T-1)).getNumber()%2==0){
                T++;
            }
            int F=0;
            while((x+F+1)<board.getBoardSize() &&(y+F+1)<board.getBoardSize() && board.get((x+F+1),(y+F+1)).getNumber() != -1 && board.get((x+F+1), (y+F+1)).getNumber()%2==0){
                F++;
            }
            int N=0;    
            while((y-N-1)>-1&&(x+N+1)<board.getBoardSize() && board.get((x+N+1), (y-N-1)).getNumber()!=-1 && board.get((x+N+1), (y-N-1)).getNumber()%2==0){
                N++;
            }
            int H=0;
            while((x-H-1)>-1&&(y+H+1)<board.getBoardSize() && board.get((x-H-1), (y+H+1)).getNumber()!=-1 && board.get((x-H-1),(y+H+1)).getNumber()%2==0){
                H++;
            }
               //Check if there are five adjacent signs in a row, column or diagonal,
                //if there are, we  show in a message box which player won. then  automatically begin a new game.;
             if(T+F>3||U+D>3||L+R>3||N+H>3){
             JOptionPane.showMessageDialog(boardPanel, " O have won  the game. ", "Congrats!",
                            JOptionPane.PLAIN_MESSAGE);
              TrickygameGUI game = new TrickygameGUI();
            }
             
             //if not , we check if there are four or three in a row, column or diagonal
            if(T+F==3||U+D==3||L+R==3||N+H==3){
                //t if a player makes 4 adjacent signs (in a row, column or diagonal), then
               //two of his signs is removed randomly (not necessary from this 3 signs).
       
              for (int t = 0;t < 2;) {
                     ArrayList<Point> point1 = new ArrayList<>();
                 for (int i = 0; i < board.getBoardSize(); ++i) {
                    for (int j = 0; j < board.getBoardSize(); ++j) {
                    point1.add(new Point(i, j));               
                  }
                 }
                    Collections.shuffle(point1);
                    //two of his signs is removed randomly (not necessary from this 3 signs).
                    Point point = point1.remove(point1.size() - 1);
                    if (board.get(point).getNumber()!=-1 &&board.get(point).getNumber()%2==0) {
                        board.get(point).setNumber(-1);
                        refresh(point.x, point.y);
                        t++;
                    }
                }

               
            }else  if(T+F==2||U+D==2||L+R==2||N+H==2){
                 //If there is no four in a row , we check if there are three in a row
                //t if a player makes 3 adjacent signs (in a row, column or diagonal), then
               //one of his signs is removed randomly (not necessary from this 3 signs).
               
               for (int t = 0; t < 1;) {
                   ArrayList<Point> point1 = new ArrayList<>();
               for (int i = 0; i < board.getBoardSize(); ++i) {
                    for (int j = 0; j < board.getBoardSize(); ++j) {
                    point1.add(new Point(i, j));               
                  }
               }
                    Collections.shuffle(point1);
                    Point point = point1.get(point1.size() - 1);
                    //one of his signs is removed randomly (not necessary from this 3 signs).
                    if (board.get(point).getNumber()!=-1 &&board.get(point).getNumber()%2==0) {
                        board.get(point).setNumber(-1);
                        refresh(point.x, point.y);
                        t++;
                    }
                }
             
            }
            
           

        } 
    }
    else {
            button.setText("");
        }
   
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board.get(x, y).getNumber() == -1) {
                
                board.get(x, y).setNumber(++clickNum);
                
                // The program should show during the game who turns.
                
                if(board.get(x, y).getNumber()%2==0){
                  TurnsLable.setText("X truns");
                }else{
                  TurnsLable.setText("O truns");
                }
                
                refresh(x, y);
      
                
                if (board.isOver()) {    

                  TrickygameGUI game = new TrickygameGUI();
                  // automatically begin a new game.
                }

            }
        }

       
    }

   public JLabel getTurnsLable() {
        return TurnsLable;
    }


}

