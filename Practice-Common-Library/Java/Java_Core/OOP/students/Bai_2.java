/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai_2;

/**
 *
 * @author manh.phanduc
 */
public class Bai_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Student student1 = new Student("Obama", 23, 6, 7, 9);
        Student student2 = new Student("Bill", 30, 8, 8, 8);
        Student student3 = new Student("Gate", 25, 10, 8, 9);
        
        Student[] students = {
            student1, student2, student3
        };
        
        StudentService service = new StudentService(students);
        service.sort();
        
        // print contents
        for (int i = 0; i< students.length; ++i) {
            System.out.println(students[i]);
        }
    }
    
}
