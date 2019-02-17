/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person.sezemsky.marek.learning.java;

import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class RandomPersonGeneratorTest {
    
    private RandomPersonGenerator rpg;
    
    public RandomPersonGeneratorTest() {
        this.rpg = new RandomPersonGenerator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testNotNull() {
        assertNotNull(new RandomPersonGenerator());
    }
    
    @Test
    public void testNotNullWithRandom() {
        assertNotNull(new RandomPersonGenerator(new Random()));
    }

    @Test
    public void testGetRandomPerson() {
        assertNotNull(rpg.getRandomPerson());
    }

    @Test
    public void testGetBulk() {
        List<Person> list = rpg.getBulk(10);
        assertEquals(10, list.size());
        for ( Person p : list ) {
            assertNotNull(p);
        }
    }
    
    
    
}
