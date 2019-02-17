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
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class MySnake {

    private int velx;
    private int vely;
    private double x;
    private double y;
    private Image snakeImage = new ImageIcon("data/images/snake.jpg").getImage();
    ;
     private ArrayList<Sprite> snake;

    public MySnake() {
        snake = new ArrayList<Sprite>();
        Sprite head = new Sprite(400, 300, 20, 20, snakeImage);
        Sprite head2 = new Sprite(420, 300, 20, 20, snakeImage);
        snake.add(head);
        snake.add(head2);    //our snake is initially two units long
    }

    /**
     * check if the snake head move to next position is also food there.
     *
     * @param food
     * @return true if snake head position equal food.
     */
    public boolean eat(Sprite food) {
        //head of snake:
        int firstX = snake.get(0).getX() + velx;
        int firstY = snake.get(0).getY() + vely;

        //The origin position of the tail of the snake:
        int tempx = snake.get(snake.size() - 1).getX();
        int tempy = snake.get(snake.size() - 1).getY();

        //every node of the snake equal previous node position:
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
        }
        snake.get(0).plusX(velx);
        snake.get(0).plusY(vely);

        //If the snake eats a food piece, then its length grow by one unit.
        //if collide food ,add new tail of snake equal to old one
        if (firstX == food.getX() && firstY == food.getY()) {
            snake.add(new Sprite(tempx, tempy, 20, 20, snakeImage));
            return true;
        }
        //If the snake doesn't eats a food piece, then its length not change.
        return false;
    }

    /**
     * to check if the snake goes into itself, or into the boundary of the game
     * level.
     *
     * @return true if it not collides anything.
     */
    public boolean move() {
        //head of snake:
        int firstX = snake.get(0).getX();
        int firstY = snake.get(0).getY();

        for (int i = snake.size() - 1; i > 0; i--) {
            if (snake.get(i).getX() == firstX && snake.get(i).getY() == firstY) {
                return false; //snake goes into itself
            }
        }
        if (firstX < 0 || firstX + 20 > 800 || firstY < 0 || firstY + 20 > 600) {
            return false;//collide the boundary of the game level.
        }
        return true;
    }

    public double getVelx() {
        return velx;
    }

    public void setVelx(int velx) {
        this.velx = velx;
    }

    public double getVely() {
        return vely;
    }

    public void setVely(int vely) {
        this.vely = vely;
    }

    public Sprite getFirst() {
        return snake.get(0);
    }

    /**
     * draw every snake node.
     *
     * @param g
     */
    public void draw(Graphics g) {
        for (Sprite s : snake) {
            s.draw(g);
        }
    }

    /**
     * check if any node of snake collides the field
     *
     * @param field
     * @return
     */
    public boolean collideSnake(Sprite field) {
        for (Sprite s : snake) {
            if (field.getX() == s.getX() && field.getY() == s.getY()) {
                return true;
            }
        }
        return false;
    }
}
