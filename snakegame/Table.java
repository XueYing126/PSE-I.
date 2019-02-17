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

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */
public class Table extends AbstractTableModel {

    private ArrayList<HighScore> highScores;
    private String[] col = {"Rank", "Name", "Score"};

    public Table() {
        super();
    }

    public void getScores(ArrayList<HighScore> highScores) {
        this.highScores = highScores;
    }

    @Override
    public int getRowCount() {
        return highScores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HighScore highScore = highScores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return highScore.getName();
            //break;
            case 2:
                return highScore.getScore();
            // break;      
        }
        return null;
    }

    @Override
    public String getColumnName(int num) {
        return col[num];
    }

}
