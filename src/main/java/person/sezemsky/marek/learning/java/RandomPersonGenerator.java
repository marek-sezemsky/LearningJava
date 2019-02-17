package person.sezemsky.marek.learning.java;

import java.util.Random;

/**
 * Runnable RandomPersonGenerator processes.
 * 
 */
public class RandomPersonGenerator implements Runnable {

    private static final String[] MALE_NAMES_FIRST = {
        "Jiří", "Jan", "Petr", "Josef", "Pavel", "Martin",
        "Jaroslav", "Tomáš", "Miroslav", "Zdeněk",};

    private static final String[] MALE_NAMES_LAST = {
        "Novák", "Svoboda", "Novotný", "Dvořák", "Černý",
        "Procházka", "Kučera", "Veselý", "Horák", "Němec",};

    private static final String[] FEMALE_NAMES_FIRST = {
        "Marie", "Jana", "Eva", "Hana", "Anna",
        "Lenka", "Kateřina", "Věra", "Lucie", "Alena",};

    private static final String[] FEMALE_NAMES_LAST = {
        "Nováková", "Svobodová", "Novotná", "Dvořáková", "Černá",
        "Procházková", "Kučerová", "Veselá", "Horáková", "Němcová",};

    private static final Integer MAX_AGE = 110;

    private Random random;
    private long counter;

    public RandomPersonGenerator(Random random) {
        this.random = random;
        this.counter = 0L;
    }

    public RandomPersonGenerator() {
        this.random = new Random();
        this.counter = 0L;
    }

    private String getRandomName(String[] array) {
        return array[random.nextInt(array.length)];
    }

    public Person getRandomPerson() {
        int gender = random.nextInt(2); // genders: 0=male, 1=female
        return new Person(
                gender > 0
                        ? getRandomName(FEMALE_NAMES_FIRST)
                        : getRandomName(MALE_NAMES_FIRST),
                gender > 0
                        ? getRandomName(FEMALE_NAMES_LAST)
                        : getRandomName(MALE_NAMES_LAST),
                random.nextInt(MAX_AGE)
        );
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
