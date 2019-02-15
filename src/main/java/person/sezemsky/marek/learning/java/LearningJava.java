package person.sezemsky.marek.learning.java;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class LearningJava {

    public static void main(String[] args) throws UnsupportedEncodingException {
        RandomPersonGenerator persons = new RandomPersonGenerator();

        // TODO use value of property "file.encoding"
        System.setOut(new PrintStream(System.out, true, "UTF8"));

        int i;
        for (i = 0; i < 10; i++) {
            System.out.println(persons.getRandomPerson().toString());
        }

    }

}
