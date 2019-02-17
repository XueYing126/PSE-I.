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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author DPOYL8
 */

public class TrickygameGUI {

    private JFrame frame;
    private BoardGUI boardGUI;

    private final int INITIAL_BOARD_SIZE = 6;

    public TrickygameGUI() {
        frame = new JFrame("Tricky five-in-a-row");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardGUI = new BoardGUI(INITIAL_BOARD_SIZE);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        frame.getContentPane().add(boardGUI.getTurnsLable(), BorderLayout.SOUTH);
 
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenu newMenu = new JMenu("New");
        gameMenu.add(newMenu);
        int[] boardSizes = new int[]{6, 10, 14};
        for (int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().remove(boardGUI.getBoardPanel()); 
                    frame.getContentPane().remove(boardGUI.getTurnsLable());
                    boardGUI = new BoardGUI(boardSize);
                    frame.getContentPane().add(boardGUI.getBoardPanel(),
                            BorderLayout.CENTER);   
                    frame.getContentPane().add(boardGUI.getTurnsLable(), BorderLayout.SOUTH);
                    frame.pack();
                }
            });
        }
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

}
