package org.example.sezemsky;

import java.time.LocalDate;
import org.example.sezemsky.Person.Gender;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test "John Doe" person, aged 42.
 */
public class PersonTest {

    private Person p;

    public PersonTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.p = new Person(Gender.GENDER_X, "John", "Doe", 42);
    }

    @After
    public void tearDown() {
        this.p = null;
    }

    /**
     * Test of gender methods of class Person.
     */
    @Test
    public void testGenders() {
        assertEquals(Gender.GENDER_X, p.getGender());
        assertEquals(Gender.GENDER_F, p.gender(Gender.GENDER_F).getGender());
        assertEquals(Gender.GENDER_M, p.gender(Gender.GENDER_M).getGender());
    }

    /**
     * Test of birthday methods of class Person.
     */
    @Test
    public void testBirthDay() {
        String F = "firstName";
        String L = "lastName";
        
        Person tb = new Person().birthDate(LocalDate.now().minusYears(42));
        assertEquals(42, (long) tb.getAge());  // @Deprecated
    }

    /**
     * Test of getFirstName method, of class Person.
     */
    @Test
    public void testGetFirstName() {
        assertEquals("John", p.getFirstName());
    }

    /**
     * Test of getLastName method, of class Person.
     */
    @Test
    public void testGetLastName() {
        assertEquals("Doe", p.getLastName());
    }

    /**
     * Test of getAge method, of class Person.
     */
    @Test
    public void testGetAge() {
        assertEquals("42", p.getAge().toString());
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        assertEquals("Person{John Doe (42)}", p.toString());
    }

    @Test
    public void testEquals() {
        Person p1 = new Person("First name", "Last name", 42);
        Person p2 = new Person("First name", "Last name", 42);
        assertTrue(p1.equals(p2));
    }
}
