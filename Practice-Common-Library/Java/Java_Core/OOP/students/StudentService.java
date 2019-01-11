package bai_2;

import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manh.phanduc
 */
public class StudentService {
    private Student[] students_;
    
    public StudentService(Student[] students) {
        students_ = students;
    }
    
    // count the students that have the average score are greater than 7
    public int countGood() {
        int num_student = students_.length;
        int counter = 0;
        
        for (int i = 0; i < num_student; ++i) {
            if (students_[i].getAverageScore() > 7) {
                ++counter;
            }
        }
        
        return counter;
    }
    
    // with decreasing the average score
    public void sort() {
        Arrays.sort(students_, new SortByDescende());
    }
}
