/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.Random;

/**
 * RandomPersonGenerator processes input stream line by line and attempt to
 * parse CSV.
 *
 * @author cn880
 */
public class RandomPersonGenerator {

    private static final String[] MALE_NAMES_FIRST = {
        "Jiří", "Jan", "Petr", "Josef", "Pavel", "Martin",
        "Jaroslav", "Tomáš", "Miroslav", "Zdeněk",};

    private static final String[] MALE_NAMES_LAST = {
        "Novák", "Svoboda", "Novotný", "Dvořák", "Černý",
        "Procházka", "Kučera", "Veselý", "Horák", "Němec",};

    private static final String[] FEMALE_NAMES_FIRST = {
        "Nováková", "Svobodová", "Novotná", "Dvořáková", "Černá",
        "Procházková", "Kučerová", "Veselá", "Horáková", "Němcová",};

    private static final String[] FEMALE_NAMES_LAST = {
        "Marie", "Jana", "Eva", "Hana", "Anna",
        "Lenka", "Kateřina", "Věra", "Lucie", "Alena",};

    private static final Integer MAX_AGE = 110;
    
    private Random random = null;

    public RandomPersonGenerator() {
        random = new Random();
    }
    
    public RandomPersonGenerator(long seed) {
        random = new Random(seed);
    }
    
    private String getRandomName(String[] array) {
        return array[random.nextInt(array.length)];
    }
    
    public Person getRandomPerson() {
        // determine gender: 0=male, 1=female
        int gender = random.nextInt(2);
        
        // return new person
        return new Person(
                gender > 0 ? getRandomName(FEMALE_NAMES_FIRST) : getRandomName(MALE_NAMES_FIRST),
                gender > 0 ? getRandomName(FEMALE_NAMES_LAST) : getRandomName(MALE_NAMES_LAST),
                random.nextInt(MAX_AGE)
        );
    }

}
