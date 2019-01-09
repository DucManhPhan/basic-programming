package Quizz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author manh.phanduc
 */
public class ImplFile {
    private String strPathFile;
    
    public ImplFile() {
        strPathFile = "";
    }
    
    public ImplFile(String pathFile) {
        strPathFile = pathFile;
    }
    
    public void readFile() {
        if (strPathFile.equals("")) {
            return;
        }
        
        try {
            FileInputStream fileStream = new FileInputStream(strPathFile);
            Scanner scanner = new Scanner(fileStream);
            
            char solution = 'E';
            char answer = 'F';
            String line = null;
            
            while (scanner.hasNextLine() || line != "") {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                } else {
                    line = "";  // at the end of file
                }                      
                
                // get solution for this question
                if (line.contains("Answer - ")) {
                    Pattern pattern = Pattern.compile("\\s*([A-D])$");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        solution = matcher.group(1).charAt(0);
                    }                                      
                    
                    continue;
                }
                
                // check user's answer at the end of question
                if (line.compareTo("") == 0) {
                    do {
                        System.out.println("Input your answer for question: ");                    
                        Scanner reader = new Scanner(System.in);
                        answer = reader.next(".").charAt(0);                                           
                    } while (answer != solution);                                       
                }                
                
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }        
    }
}
