package person.sezemsky.marek.learning.java;

import java.io.Serializable;

public class Person implements Serializable, Cloneable, Comparable {

    private String firstName;
    private String lastName;
    private Integer age;      // TODO replace with birthDate

    public Person() {
    }

    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Person lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    // TODO make age @Deprecated
    public Person age(Integer age) {
        this.age = age;
        return this;
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
        return String.format("Person{%s %s (%d)}",
                this.firstName, this.lastName, this.age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Person) {
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

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
