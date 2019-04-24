package org.example.sezemsky;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Person implements Serializable, Cloneable, Comparable {

    private static final char GENDER_M = 'M';
    private static final char GENDER_F = 'F';
    private static final char GENDER_X = 'X';

    public enum Gender {
        GENDER_M, GENDER_F, GENDER_X
    };

    private Gender gender;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(Gender gender, LocalDate birthDate, String firstName, String lastName) {
        this.gender = gender;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(Gender gender, String firstName, String lastName, Integer age) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = LocalDate.now().minusYears((long) age); // this.age = age;
    }

    public Person(String firstName, String lastName, Integer age) {
        this(Gender.GENDER_X, firstName, lastName, age);
    }

    public Person gender(Gender g) {
        this.gender = g;
        return this;
    }

    @Deprecated
    public Person age(Integer age) {
        this.birthDate = LocalDate.MIN; // This is intentional error
        return this;
    }

    public Person birthDate(LocalDate b) {
        birthDate = b;
        return this;
    }

    public Person firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Person lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    @Deprecated
    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Person{%s %s (%d)}", firstName, lastName, getAge());
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
