package Quizz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.System;

/**
 *
 * @author manh.phanduc
 */
public class MainProgram {
    public static void main(String[] args) {
        ImplFile file = new ImplFile("E:\\Java\\Traning_VietIS\\Bai_1\\src\\Quizz\\quizz.txt");
        file.readFile();
    }
}
