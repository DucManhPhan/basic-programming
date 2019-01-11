/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai_2;

import java.util.Comparator;

/**
 *
 * @author manh.phanduc
 */
public class SortByDescende implements Comparator<Student> {

    @Override
    public int compare(Student obj1, Student obj2) {
        return obj2.getAverageScore() - obj1.getAverageScore();
    }
    
}
