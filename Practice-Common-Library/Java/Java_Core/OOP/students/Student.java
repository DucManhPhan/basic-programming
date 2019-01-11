package bai_2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manh.phanduc
 */
public class Student {
    private String    name_;
    private int       age_;
    private int       math_score_;
    private int       physic_score_;
    private int       chemitry_score_;
    private int       avg_scores_;
    
    public Student(String name, int age, int math_score, int physic_score, int chemitry_score) {
        name_ = name; 
        age_ = age;
        math_score_ = math_score;
        physic_score_ = physic_score;
        chemitry_score_ = chemitry_score;
        avg_scores_ = (math_score + physic_score + chemitry_score) / 3;
    }
    
    // properties
    public String getName() {
        return name_;
    }
    
    public void setName(String name) {
        name_ = name;
    }
    
    public int getAge() {
        return age_;        
    }
    
    public void setAge(int age) {
        age_ = age;
    }
    
    public int getMathScore() {
        return math_score_;
    }
    
    public void setMathScore(int math_score) {
        math_score_ = math_score;
    }
    
    public int getPhysicScore() {
        return physic_score_;
    }
    
    public void setPhysicScore(int physic_score) {
        physic_score_ = physic_score;
    }
    
    public int getChemitryScore() {
        return chemitry_score_;
    }
    
    public void setChemitryScore(int chemitry_score) {
        chemitry_score_ = chemitry_score;
    }
    
    public int getAverageScore() {
        return avg_scores_;
    }
    
    public void setAverageScore(int avg_score) {
        avg_scores_ = avg_score;
    }
    
    public String toString() {
        return this.name_ + " " + this.avg_scores_;
    }
}
