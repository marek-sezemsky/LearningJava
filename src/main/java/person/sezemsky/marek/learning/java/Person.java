package person.sezemsky.marek.learning.java;

import java.io.Serializable;

public class Person implements Serializable{

    private final String firstName;
    private final String lastName;
    private final Integer age;

    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" + firstName + " " + lastName + " (" + age + ")}";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); 
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
