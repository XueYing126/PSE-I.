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

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author bli
 */
public class GameEngine extends JPanel {

    private final int FPS = 240;
    private final JPanel boardPanel;
    private final int SNAKE_MOVEMENT = 20;

    private ArrayList<Point> points;
    private ArrayList<Rock> rocks;
    private int eatenFood = 0;
    private boolean paused = false;
    private final Image background;
    private final Image foodImage = new ImageIcon("data/images/food.jpg").getImage();
    private final Image rockImage = new ImageIcon("data/images/rock.jpg").getImage();
    private int levelNum = 5;
    private Level level;
    //private Ball ball;
    private MySnake mySnake;
    private Food food;
    private final Timer newFrameTimer;
    private JLabel timeLabel;
    private String playername;

    private final long startTime;
    private final Timer timer;
    private HighScores highScores;

    public GameEngine() {
        super();

        //................
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(400, 300));

        background = new ImageIcon("data/images/background.jpg").getImage();

        //The player can control the movement of the snakeâ€™s head with WASD keyboard buttons.
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mySnake.setVelx(-SNAKE_MOVEMENT);
                mySnake.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mySnake.setVelx(SNAKE_MOVEMENT);
                mySnake.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mySnake.setVely(-SNAKE_MOVEMENT);
                mySnake.setVelx(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mySnake.setVely(SNAKE_MOVEMENT);
                mySnake.setVelx(0);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        restart();
        newFrameTimer = new Timer(35000 / FPS, new NewFrameListener());
        newFrameTimer.start();

        timeLabel = new JLabel(" ");
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(elapsedTime() + " ms");
            }
        });
        startTime = System.currentTimeMillis();
        timer.start();
    }

    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * If restart the game, we have new level, new snake,new food and rocks;
     */
    public void restart() {
        try {
            level = new Level();
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        mySnake = new MySnake();

        //The snake starts off from the center of the level in a random direction.
        boolean b1 = Math.random() < 0.5;
        boolean b2 = Math.random() < 0.5;
        //random direction of the snake
        if (b1 && b2) {
            mySnake.setVelx(-SNAKE_MOVEMENT);
            mySnake.setVely(0);
        } else if (!b1 & b2) {
            mySnake.setVelx(0);
            mySnake.setVely(-SNAKE_MOVEMENT);
        } else if (b1 && !b2) {
            mySnake.setVelx(0);
            mySnake.setVely(SNAKE_MOVEMENT);
        } else {
            mySnake.setVelx(SNAKE_MOVEMENT);
            mySnake.setVely(0);
        }

        if (createFoodAndRock()) {
            levelNum = levelNum;
        }

    }

    /**
     * If the snake collides with a rock, then the game ends
     *
     * @return true if the head of snake collides rock
     */
    public boolean collideRocks() {
        for (Rock r : rocks) {
            if (mySnake.getFirst().getX() == r.getX() && mySnake.getFirst().getY() == r.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if we can create new food and rock.
     *
     * @return false if the snake is too long and no place to create food or
     * rocks. then game go to next level
     */
    public boolean createFoodAndRock() {

        points = new ArrayList<Point>();
        for (int i = 0; i < 40; ++i) {
            for (int j = 0; j < 30; ++j) {
                points.add(new Point(i, j));
            }
        }
        Collections.shuffle(points);

        //Only one food piece is placed randomly at a time on the level 
        //(on a field, where there is no snake). 
        for (int i = 0; i < 1;) {
            Point point = points.remove(points.size() - 1);
            if (!mySnake.collideSnake(level.get(point))) {
                food = new Food((int) (20 * point.getX()), (int) (20 * point.getY()), 20, 20, foodImage);
                i++;
            }
        }

        //create random rocks
        //(on a field, where there is no snake and food).
        rocks = new ArrayList<Rock>();
        for (int i = 0; i < levelNum;) {
            Point point = points.remove(points.size() - 1);
            if (!mySnake.collideSnake(level.get(point))) {
                Rock rock = new Rock((int) (20 * point.getX()), (int) (20 * point.getY()), 20, 20, rockImage);
                rocks.add(rock);
                i++;
            }
        }

        //which means the snake is too long and game end.
        if (points.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * paint the snake, rocks, and food
     *
     * @param grphcs
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 600, null);
        mySnake.draw(grphcs);
        food.draw(grphcs);
        for (Rock rock : rocks) {
            rock.draw(grphcs);
        }
    }

    /**
     * reference the highscores to here to add data.
     *
     * @param highscores
     */
    void setDatabase(HighScores highscores) {
        this.highScores = highscores;
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                //First, we check next step if our snake eat food, 
                if (mySnake.eat(food)) {  //if ture, we need to create new food and rock
                    eatenFood++;
                    if (!createFoodAndRock()) {   //to create new food and rock
                        // if the board is full, you need to get to next level  

                        playername = JOptionPane.showInputDialog(null, "You won this level! You had " + eatenFood
                                + " foods in " + elapsedTime() + " ms.\nPlease, enter your name: ", "Congrats!", JOptionPane.PLAIN_MESSAGE);

                        try {
                            highScores.putHighScore(playername, eatenFood);
                        } catch (SQLException ex) {
                            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        restart();
                    }
                } //if snake doesn't eat food, we need to check if the snake goes into itself, 
                //or into the boundary of the game level.
                if (!mySnake.move()) {
                    // not movable, means the snake collodes itself or the wall, game over.
                    playername = JOptionPane.showInputDialog(null, "Game Over ! You had " + eatenFood
                            + " foods in " + elapsedTime() + " ms.\nPlease, enter your name: ", "Game_Over!", JOptionPane.PLAIN_MESSAGE);

                    try {
                        highScores.putHighScore(playername, eatenFood);
                    } catch (SQLException ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    levelNum = 5;
                    eatenFood = 0;
                    restart();
                } else if (collideRocks()) {  //  if the snake collides rocks , game over

                    playername = JOptionPane.showInputDialog(null, "Game Over ! You had " + eatenFood
                            + " foods in " + elapsedTime() + " ms.\nPlease, enter your name: ", "Game_Over!", JOptionPane.PLAIN_MESSAGE);

                    try {
                        highScores.putHighScore(playername, eatenFood);
                    } catch (SQLException ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    levelNum = 5;
                    eatenFood = 0;
                    restart();
                }

            }

            repaint();
        }

    }
}
