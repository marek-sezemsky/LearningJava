package person.sezemsky.marek.learning.java;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

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
        if (this == obj) {
            return true;
        }
        if (obj instanceof Person ) {
            Boolean equalFirst = this.getFirstName().equals(((Person) obj).getFirstName());
            Boolean equalLast = this.getLastName().equals(((Person) obj).getLastName());
            Boolean equalAge = this.getAge().equals(((Person) obj).getAge());
            
            return (equalFirst && equalLast && equalAge);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
