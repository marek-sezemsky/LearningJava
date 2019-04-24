package org.example.sezemsky;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

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

    private final Random random;

    public RandomPersonGenerator(Random random) {
        this.random = random;
    }

    public RandomPersonGenerator() {
        this.random = new Random();
    }

    private String getRandomName(String[] array) {
        return array[random.nextInt(array.length)];
    }

    public Person genTrulyRandomPerson() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Person getRandomPerson() {
        // semi-random approach
        Person p = null;
        int gender = random.nextInt(2);

        switch (gender) {  // 0=male, 1=female
            case 0:
                p = new Person(
                        Person.Gender.GENDER_M,
                        LocalDate.now().minusYears(random.nextInt(MAX_AGE)),
                        getRandomName(MALE_NAMES_FIRST),
                        getRandomName(MALE_NAMES_LAST));

            case 1:
                p = new Person(
                        Person.Gender.GENDER_F,
                        getRandomName(FEMALE_NAMES_FIRST),
                        getRandomName(FEMALE_NAMES_LAST),
                        random.nextInt(MAX_AGE)); // deprecated 'age' interface

            default:
                // no X's!
        }
        
        return p;
    }

    public List<Person> getBulk(long count) {
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(getRandomPerson());
        }
        return list;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
