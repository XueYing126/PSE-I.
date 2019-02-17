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

import java.awt.Point;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class Level {

    // each brick is 20x20, so there can be at most 40*30 bricks side by side
    private final int BRICK_WIDTH = 20;
    private final int BRICK_HEIGHT = 20;
    private Sprite[][] board;

    public Level() throws IOException {
        loadLevel();
    }

    public void loadLevel() throws IOException {
        board = new Sprite[40][30];
        for (int i = 0; i < 40; ++i) {
            for (int j = 0; j < 30; ++j) {
                board[i][j] = new Sprite(i * BRICK_WIDTH, j * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, null);
            }
        }

    }

    public Sprite get(int x, int y) {
        return board[x][y];
    }

    /**
     * use point to access board,make it easy to get random field.
     *
     * @param point
     * @return
     */
    public Sprite get(Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        return get(x, y);
    }

}
