// Xue Ying
//
// DPOYL8
//
// Third Assignment - Snake
//
// 2019/01/12 22:52:26
//
// This solution was submitted and prepared by Xue Ying, DPOYL8 for the
// Third Assignment - Snake assignment of the Practical software engineering I. course.
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
package snakegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Admin
 */
public class SnakeGameGUI {

    private final JFrame frame;
    private JFrame Tableframe;
    private Scoretable tablePanel;
    private GameEngine gameArea;
    private HighScores highscores;

    public SnakeGameGUI() throws SQLException {
        
         try {
            highscores = new HighScores(10);
        } catch (SQLException ex) {
            Logger.getLogger(SnakeGameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

         
         //create high scores table
        Tableframe = new JFrame("HighScores");
        tablePanel = new Scoretable();
        tablePanel.setHighScore(highscores.getHighScores());
        Tableframe.getContentPane().add(tablePanel,BorderLayout.CENTER);
        Tableframe.setPreferredSize(new Dimension(300, 400));
        Tableframe.setResizable(false);
        Tableframe.pack();
        Tableframe.setVisible(false);
        
        
       //game field:
        frame = new JFrame("Snakegame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameArea = new GameEngine();
        gameArea.setDatabase(highscores);
        frame.getContentPane().add(gameArea);

        frame.setPreferredSize(new Dimension(805, 660));
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Menu");
        menuBar.add(gameMenu);

        //Create a menu item, which displays a highscore table of the players for the 10 best scores
        JMenuItem highScores = new JMenuItem("10-highscores");
        gameMenu.add(highScores);
        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    tablePanel.setHighScore(highscores.getHighScores());
                } catch (SQLException ex) {
                    Logger.getLogger(SnakeGameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                Tableframe.setVisible(true);
            }
        });
        
        // create a menuitem which restarts the game.
        JMenuItem restartmenu = new JMenuItem("Restart");
        gameMenu.add(restartmenu);
        restartmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameArea.restart();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

}
