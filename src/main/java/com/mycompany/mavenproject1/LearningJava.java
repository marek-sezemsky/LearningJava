/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Learning Java
 *
 * @author cn880
 */
public class LearningJava {

    
    public static void main(String[] args) throws UnsupportedEncodingException {
        RandomPersonGenerator persons = new RandomPersonGenerator();
    
        System.setOut(new PrintStream(System.out, true, "UTF8"));
        
        int i;
        for ( i = 0; i < 10; i++ ) {
            System.out.println(persons.getRandomPerson().toString());
        }

    }

}
