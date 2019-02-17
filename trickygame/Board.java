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

import java.awt.Point;

/**
 *
 * @author DPOYL8
 */
public class Board {

    private Field[][] board;
    private final int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
            }
        }
    }
    
    public boolean isOver() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j].getNumber() == -1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Field get(int x, int y) {
        return board[x][y];
    }
    
    public Field get(Point point) {
        int x = (int)point.getX();
        int y = (int)point.getY();
        return get(x, y);
    }

    public int getBoardSize() {
        return boardSize;
    }

}
